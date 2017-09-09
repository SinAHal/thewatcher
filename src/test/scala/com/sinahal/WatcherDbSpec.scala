package com.akkademy

import org.scalatest.{BeforeAndAfterEach, FunSpecLike, Matchers}
import akka.actor.ActorSystem
import com.sinahal.messages.SetRequest
import akka.testkit.TestActorRef
import com.sinahal.WatcherDb

class WatcherDbSpec extends FunSpecLike with Matchers with BeforeAndAfterEach {
  implicit val system = ActorSystem()
  describe("watcherDb") {
    describe("given SetRequest"){
      it("should place key/value into map"){
        val actorRef = TestActorRef(new WatcherDb)
        actorRef ! SetRequest("key", "value")
        val watcherDb = actorRef.underlyingActor
        watcherDb.map.get("key") should equal(Some(List("value")))
      }
    }
  }
}