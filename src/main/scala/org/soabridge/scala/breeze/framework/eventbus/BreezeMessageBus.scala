/**
 * Copyright (C) 2016 SOABridge.org <http://www.soabridge.org>
 */
package org.soabridge.scala.breeze.framework.eventbus

import akka.actor.ActorRef
import org.soabridge.scala.breeze.messaging.{BreezeMessage, DefaultMessageCondition}

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class BreezeMessageBus(val name: String) extends BreezeBus {

  type Event = BreezeMessage

  def subscribe(actor: ActorRef, to: Classifier): Boolean = super.subscribe((actor, new DefaultMessageCondition), to)

}

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
object BreezeMessageBus {

  def apply(): BreezeMessageBus = new BreezeMessageBus("default")

  def apply(name: String): BreezeMessageBus = new BreezeMessageBus(name)

}