/**
 * Copyright (C) 2016 SOABridge.org <http://www.soabridge.org>
 */
package org.soabridge.scala.breeze.messaging

import akka.actor.ActorRef
import org.soabridge.scala.breeze.framework.eventing.AbstractEventBus

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class MessageBus(val name: String) extends AbstractEventBus {

  type Event = BreezeMessage

  def subscribe(actor: ActorRef, to: Classifier): Boolean = super.subscribe((actor, new DefaultMessageCondition), to)

}

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object MessageBus {

  def apply(): MessageBus = new MessageBus("default")

  def apply(name: String): MessageBus = new MessageBus(name)

}