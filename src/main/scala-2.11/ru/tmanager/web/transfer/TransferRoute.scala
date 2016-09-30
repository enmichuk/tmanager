package ru.tmanager.web.transfer

import akka.http.scaladsl.server.Directives._
import ru.tmanager.services.transfer.TransferService.{TransfersRequest, TransfersResponse}
import ru.tmanager.web.TManagerRoute
import akka.actor.ActorRef
import akka.pattern.ask
import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.util.Timeout
import TransferJsonProtocol._
import spray.json._

trait TransferRoute extends TManagerRoute{

  def transferService: ActorRef

  val transferRoute =
    pathPrefix("transfers") {
      get {
        onSuccess(transferService ? TransfersRequest) {
          case TransfersResponse(transfers) => complete(transfers)
        }
      }
    }

}
