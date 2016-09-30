package ru.tmanager.services.transfer

import akka.actor.Actor
import ru.tmanager.dao.transfer.TransferDao
import ru.tmanager.model.Transfer
import ru.tmanager.services.ActorDao

object TransferService {

  object TransfersRequest

  case class TransfersResponse(transfers: List[Transfer])

}

class TransferService extends Actor with TransferDao with ActorDao{

  import TransferService._

  override def receive = {

    case TransfersRequest => sender ! TransfersResponse(getAllTransfers)

  }
}
