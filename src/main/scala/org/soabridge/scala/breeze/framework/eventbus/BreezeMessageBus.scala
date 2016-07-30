/**
 * Copyright (C) 2016 SOABridge.org <http://www.soabridge.org>
 */
package org.soabridge.scala.breeze.framework.eventbus

import akka.actor.ActorRef
import akka.event.{EventBus, SubchannelClassification}
import akka.util.Subclassification
import org.soabridge.scala.breeze.messaging.BreezeMessage

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object BreezeMessageBus {

  def apply(): BreezeMessageBusImpl = new BreezeMessageBusImpl("default")

  def apply(name: String): BreezeMessageBusImpl = new BreezeMessageBusImpl(name)

  /**
   *
   */
  class BreezeMessageBusImpl(val name: String) extends EventBus with SubchannelClassification {
    type Event = BreezeMessage
    type Classifier = Class[_ <: Event]
    type Subscriber = (ActorRef, BreezeMessageCondition)

    protected implicit def subclassification = new Subclassification[Class[_ <: BreezeMessage]] {
      def isEqual(x: Class[_ <: BreezeMessage], y: Class[_ <: BreezeMessage]): Boolean = x == y
      def isSubclass(x: Class[_ <: BreezeMessage], y: Class[_ <: BreezeMessage]): Boolean = y isAssignableFrom x
    }

    protected def classify(event: BreezeMessage): Class[_ <: BreezeMessage] = event.getClass

    protected def publish(event: BreezeMessage, subscriber: (ActorRef, BreezeMessageCondition)): Unit = {
      val (actor, condition) = subscriber
      if(condition isMetBy event) actor ! event
    }
  }

}