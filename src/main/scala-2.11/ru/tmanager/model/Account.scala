package ru.tmanager.model

import java.util.UUID

import ru.tmanager.model.TransferStatus.TransferStatus

case class Account(id: String, owner: String, balance: BigDecimal)

case class Transfer(id: String = UUID.randomUUID.toString, from: String, to: String, amount: BigDecimal,
                    status: TransferStatus = TransferStatus.Initiated, reason: Option[String] = None)

object TransferStatus extends Enumeration {
  type TransferStatus = Value
  val Initiated, Successful, Unsuccessful = Value
}
