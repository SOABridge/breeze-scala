/**
 * Copyright (C) 2016 SOABridge.org <http://www.soabridge.org>
 */
package org.soabridge.scala.breeze.messaging

import org.soabridge.scala.breeze.framework.eventing.EventCondition

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
trait MessageCondition extends EventCondition[BreezeMessage] {
}

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class DefaultMessageCondition extends MessageCondition {

  def isMetBy(e: BreezeMessage): Boolean = true
}