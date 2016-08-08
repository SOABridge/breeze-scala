package org.soabridge.scala.breeze

import akka.actor.SupervisorStrategy.Resume
import akka.actor._
import org.soabridge.scala.breeze.modules.ModulesActor
import org.soabridge.scala.breeze.resources.ResourceActor

/**
 * Missing documentation. 
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
private[breeze] class VortexActor extends Actor {
  /* Importing all messages declared in companion object for processing */
  import VortexActor.Messages._

  /** Supervising actor for all resources in the hive */
  private var resActor: ActorRef = _
  /** Supervising actor for all configured module instances */
  private var modActor: ActorRef = _

  /** Supervisor strategy for the subordinate actors. */
  override def supervisorStrategy: SupervisorStrategy = OneForOneStrategy() {
    //TODO slk: implement supervisor strategy
    case _ => Resume
  }

  /** Message processing */
  def receive: Receive = initialize

  val initialize: Receive = {
    case Start =>
      // Initializing the subordinate actors
      resActor = context.watch(context.actorOf(ResourceActor.props, "resources"))
      modActor = context.watch(context.actorOf(ModulesActor.props, "modules"))
      // Switching context to 'processing'
      context become processing
    case Status =>
      //TODO slk: implement Status behavior
  }

  val processing: Receive = {
    case Status =>
      //TODO slk: implement Status behavior
    case Stop =>
      //TODO slk: implement Stop behavior
    case Terminated =>
      //TODO slk: implement watchdog behavior
  }
}

/**
 * Missing documentation.
 *
 * @author <a href="steffen.krause@soabridge.com">Steffen Krause</a>
 * @since 1.0
 */
private[breeze] object VortexActor {
  /** Actor properties for HiveActor */
  val props: Props = Props[VortexActor]

  /** Accepted messages for HiveActor */
  object Messages {
    case object Start
    case object Status
    case object Stop
  }
}
