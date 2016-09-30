package ru.tmanager.web

import akka.actor.{ActorRef, ActorSystem}
import akka.pattern.ask
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.marshallers.sprayjson.SprayJsonSupport._
import akka.http.scaladsl.model.StatusCodes
import akka.stream.ActorMaterializer
import akka.util.Timeout
import ru.tmanager.model.Account
import ru.tmanager.services.account.AccountService._
import ru.tmanager.services.transaction.TransactionManager._
import ru.tmanager.services.transfer.TransferService.{TransfersRequest, TransfersResponse}

import scala.concurrent.Future
import scala.concurrent.duration._
import ru.tmanager.web.account.AccountRoute
import ru.tmanager.web.transaction.TransactionRoute
import ru.tmanager.web.transfer.TransferRoute
import spray.json._

class WebEndpoint(
                   host: String,
                   port: Int,
                   val accountService: ActorRef ,
                   val transferService: ActorRef,
                   val transactionManager: ActorRef
                 )
                 (implicit actorSystem: ActorSystem) extends AccountRoute with TransferRoute with TransactionRoute{

  implicit val timeout = Timeout(5 seconds)
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = actorSystem.dispatcher

  private val binding: Future[ServerBinding] =
    Http().bindAndHandle(accountRoute ~ transferRoute ~ transactionRoute, host, port)

  def stopEndpoint: Future[Unit] = binding.flatMap(_.unbind())
}
