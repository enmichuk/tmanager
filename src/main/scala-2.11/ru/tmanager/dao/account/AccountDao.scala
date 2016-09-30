package ru.tmanager.dao.account

import ru.tmanager.dao.{Dao, FakeDB}
import ru.tmanager.model.Account
import slick.driver.H2Driver.api._

import scala.collection.mutable
import collection.breakOut
import scala.concurrent.{ExecutionContext, Future}

trait AccountDao extends Dao {

  implicit def executionContext: ExecutionContext

  def getAccountById(id: String): Option[Account] = FakeDB.accountTable.get(id)

  def getAllAccounts: List[Account] = FakeDB.accountTable.map{case (id, account) => account}(breakOut)

  def createOrUpdate(account: Account) = FakeDB.accountTable.put(account.id, account)

  def removeAccountById(id: String): Option[Account] = FakeDB.accountTable.remove(id)

}
