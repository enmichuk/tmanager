package ru.tmanager.dao.transfer

import ru.tmanager.dao.{Dao, FakeDB}
import ru.tmanager.model.Transfer

import scala.concurrent.ExecutionContext
import collection.breakOut

trait TransferDao extends Dao {

  implicit def executionContext: ExecutionContext

  def getAllTransfers: List[Transfer] = FakeDB.transferTable.map{case (id, account) => account}(breakOut)

}
