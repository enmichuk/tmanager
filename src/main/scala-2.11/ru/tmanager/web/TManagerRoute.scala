package ru.tmanager.web

import akka.actor.ActorRef
import akka.util.Timeout

trait TManagerRoute {

  implicit def timeout: Timeout

}
