package actors

import actors.SchedulerActor.ScheduleMessage
import akka.actor.Actor

object SchedulerActor {
  case class ScheduleMessage(performAction: String)
}

class SchedulerActor extends Actor {

  def receive = {
    case s:ScheduleMessage =>
      //Perform Business Logic here
    // To be used for more extensive operations than can't be done in the global startup methods
  }

}
