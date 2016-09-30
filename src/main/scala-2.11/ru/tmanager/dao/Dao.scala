package ru.tmanager.dao

import akka.actor.Actor
import com.typesafe.config.Config
import slick.driver.H2Driver.api._

trait Dao{

  def config: Config

  def database: String

  lazy val dataBase = Database.forConfig(database, config)

}
