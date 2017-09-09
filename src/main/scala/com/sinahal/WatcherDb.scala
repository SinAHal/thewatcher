package com.sinahal

import akka.actor.Actor
import akka.event.Logging
import com.sinahal.messages._
import scala.collection.mutable

class WatcherDb extends Actor {
  val map = new mutable.HashMap[String, List[Object]]
  val log = Logging(context.system, this)
  override def receive = {
    case SetRequest(key, value) => {
      log.info("received SetRequest - key: {} value: {}", key, value)
      map.put(key, value :: map.getOrElse(key, List.empty))
    }
    case SetSizeRequest(size) => ???
    case o => log.info("received unknown message: {}", o);
  }
}