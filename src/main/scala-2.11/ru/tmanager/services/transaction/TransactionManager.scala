package ru.tmanager.services.transaction

import akka.actor.Actor
import akka.actor.Actor.Receive
import ru.tmanager.dao.account.AccountDao
import ru.tmanager.dao.transfer.TransferDao
import ru.tmanager.model.{Transfer, TransferDetail}
import ru.tmanager.model.TransferStatus._
import ru.tmanager.services.ActorDao
import ru.tmanager.services.transaction.TransactionManager.{TransactionRequest, TransactionResponse}

object TransactionManager {

  case class TransactionRequest(transfer: TransferDetail)

  case class TransactionResponse(transfer: Transfer)

}

class TransactionManager extends Actor with AccountDao with TransferDao with ActorDao {

  def receive: Receive = {
    case TransactionRequest(transferDetail) =>
      val transfer = Transfer(detail = transferDetail)
      val resultTransfer = if(transfer.detail.amount > 0) {
        getAccountById(transfer.detail.from) match {
          case Some(fromAccount) =>
            getAccountById(transfer.detail.to) match {
              case Some(toAccount) =>
                if (transfer.detail.amount > fromAccount.balance) {
                  transfer.copy(status = Unsuccessful, reason = Some("Source account have no enough balance"))
                } else{
                  createOrUpdate(fromAccount.copy(balance = fromAccount.balance - transfer.detail.amount))
                  createOrUpdate(toAccount.copy(balance = toAccount.balance + transfer.detail.amount))
                  transfer.copy(status = Successful)
                }
              case None => transfer.copy(status = Unsuccessful, reason = Some("Destination account not found"))
            }
          case None => transfer.copy(status = Unsuccessful, reason = Some("Source account not found"))
        }
      } else{
        transfer.copy(status = Unsuccessful, reason = Some("Transaction amount should greater than 0"))
      }
      create(resultTransfer)
      sender ! TransactionResponse(resultTransfer)

  }

}
