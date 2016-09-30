package ru.tmanager.web.transaction

import ru.tmanager.services.transaction.TransactionManager.{TransactionRequest, TransactionResponse}
import spray.json.DefaultJsonProtocol
import ru.tmanager.web.transfer.TransferJsonProtocol._

object TransactionJsonProtocol extends DefaultJsonProtocol{

  implicit val transactionRequestFormat = jsonFormat1(TransactionRequest)
  implicit val transactionResponseFormat = jsonFormat1(TransactionResponse)

}
