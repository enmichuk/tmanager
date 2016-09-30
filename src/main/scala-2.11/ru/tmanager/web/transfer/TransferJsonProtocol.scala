package ru.tmanager.web.transfer

import ru.tmanager.model.{Transfer, TransferStatus}
import ru.tmanager.model.TransferStatus.TransferStatus
import ru.tmanager.services.transfer.TransferService._
import spray.json.{DefaultJsonProtocol, DeserializationException, JsString, JsValue, RootJsonFormat}

object TransferJsonProtocol extends DefaultJsonProtocol{

  implicit object SourceFormat extends RootJsonFormat[TransferStatus] {
    override def write(nodeType: TransferStatus): JsValue =
      JsString(nodeType.toString)

    override def read(json: JsValue): TransferStatus =
      json match {
        case JsString(nodeType) => TransferStatus.withName(nodeType)
        case _ => throw DeserializationException("ClassifierNodeType enum string expected")
      }
  }

  implicit val transferFormat = jsonFormat6(Transfer)
  implicit val transfersRequestFormat = jsonFormat0(() => TransfersRequest)
  implicit val transfersResponseFormat = jsonFormat1(TransfersResponse)

}
