/**
 * Copyright (C) 2016 SOABridge.org <http://www.soabridge.org>
 */
package org.soabridge.scala.breeze.framework.eventbus

import org.soabridge.scala.breeze.messaging.BreezeMessage

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
trait BreezeMessageCondition {

  def isMetBy(message: BreezeMessage): Boolean
}

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
class DefaultCondition extends BreezeMessageCondition {

  def isMetBy(message: BreezeMessage): Boolean = true
}