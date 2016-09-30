package ru.tmanager.dao

import ru.tmanager.model.{Account, Transfer}

import scala.collection.concurrent.TrieMap


/**
  * Эмуляция БД. Все запросы можно направлять к БД через dataBase из Dao
  */
object FakeDB {

  val accountTable = TrieMap[String, Account]()

  val transferTable = TrieMap[String, Transfer]()

}
