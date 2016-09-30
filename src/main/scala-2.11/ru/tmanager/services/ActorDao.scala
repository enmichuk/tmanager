package ru.tmanager.services

import akka.actor.Actor
import ru.tmanager.dao.Dao

trait ActorDao extends Dao{
  this: Actor =>

  def config = context.system.settings.config

  def database = "tmanager-database"

  implicit def executionContext = context.dispatcher

}
