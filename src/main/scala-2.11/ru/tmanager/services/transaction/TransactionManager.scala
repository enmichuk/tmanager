package ru.tmanager.services.transaction

import akka.actor.Actor
import akka.actor.Actor.Receive
import ru.tmanager.model.Transfer

object TransactionManager {

  case class InvokeTransferRequest(transfer: Transfer)

  case class InvokeTransferResponse(transfer: Transfer)

}

class TransactionManager extends Actor {

  def receive: Receive = {
    case _ =>
  }

}
