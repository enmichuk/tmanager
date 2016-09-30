package ru.tmanager.web.transaction

import akka.actor.ActorRef
import akka.pattern.ask
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import ru.tmanager.services.transaction.TransactionManager._
import ru.tmanager.web.TManagerRoute
import TransactionJsonProtocol._

trait TransactionRoute extends TManagerRoute{

  def transactionManager: ActorRef

  val transactionRoute = {
    path("transaction")
        post {
          entity(as[TransactionRequest]) { request =>
            onSuccess(transactionManager ? request) {
              case response: TransactionResponse => complete(response)
            }
          }
        }
      }
}
