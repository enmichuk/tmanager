package ru.tmanager

import akka.actor.{ActorSystem, Props}
import ru.tmanager.services.account.AccountService
import ru.tmanager.services.transfer.TransferService
import ru.tmanager.web.WebEndpoint

import scala.concurrent.Future
import scala.io.StdIn

object TManager extends App{

  implicit val actorSystem = ActorSystem("TManager")
  implicit val ec = actorSystem.dispatcher

  val accountService = actorSystem.actorOf(Props[AccountService], "accountService")

  val transferService = actorSystem.actorOf(Props[TransferService], "transferService")

//  val transactionManager = actorSystem.actorOf(Props(classOf[TransactionManager], accountManager), "transaction-manager")

  val endpoint = new WebEndpoint("localhost", 8080, accountService, transferService/*, transactionManager*/)

  Future {
    println("Press enter to stop server")
    StdIn.readLine()
    endpoint.stopEndpoint.foreach(_ => actorSystem.terminate().foreach(println))
  }

}
