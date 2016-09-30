package ru.tmanager.web.account

import akka.actor.ActorRef
import akka.pattern.ask
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.util.Timeout
import ru.tmanager.model.Account
import ru.tmanager.services.account.AccountService._
import AccountJsonProtocol._
import ru.tmanager.web.TManagerRoute
import spray.json._

trait AccountRoute extends TManagerRoute{

  def accountService: ActorRef

  val accountRoute =
    pathPrefix("account" / Segment) { accountId =>
      get {
        onSuccess(accountService ? AccountByIdRequest(accountId)) {
          case AccountByIdResponse(account) =>
            account.map(a => complete(a)).getOrElse(complete(StatusCodes.NotFound))
        }
      }
    } ~
      path("account") {
        post {
          entity(as[Account]) { clientAccount =>
            onSuccess(accountService ? CreateAccountRequest(clientAccount)) {
              case response: CreateAccountResponse =>
                response match {
                  case CreateAccountResponse(account, true) => complete(account)
                  case CreateAccountResponse(account, false) => complete(StatusCodes.InternalServerError)
                }
            }
          }
        }
      } ~
      path("accounts") {
        get {
          onSuccess(accountService ? AccountsRequest) {
            case AccountsResponse(accounts) => complete(accounts)
          }
        }
      }

}
