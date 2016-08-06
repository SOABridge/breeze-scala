/**
 * Copyright (C) 2016 SOABridge.org <http://www.soabridge.org>
 */
package org.soabridge.scala.breeze.framework.eventing

import akka.actor.ActorRef
import akka.event.{EventBus, SubchannelClassification}
import akka.util.Subclassification

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
abstract class AbstractEventBus extends EventBus with SubchannelClassification {
  type Classifier = Class[_ <: Event]
  type Subscriber = (ActorRef, EventCondition[Event])

  protected implicit def subclassification = new Subclassification[Class[_ <: Event]] {
    def isEqual(x: Class[_ <: Event], y: Class[_ <: Event]): Boolean = x == y
    def isSubclass(x: Class[_ <: Event], y: Class[_ <: Event]): Boolean = y isAssignableFrom x
  }

  protected def classify(event: Event): Class[_ <: Event] = event.getClass

  protected def publish(event: Event, subscriber: (ActorRef, EventCondition[Event])): Unit = {
    val (actor, condition) = subscriber
    if(condition isMetBy event) actor ! event
  }

  def subscribe(actor: ActorRef, classifier: Classifier): Boolean
}
