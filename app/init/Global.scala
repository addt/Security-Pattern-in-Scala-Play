package init

import actors.SchedulerActor
import play.api.{ Logger, Application, GlobalSettings }
import play.api.libs.concurrent.Akka
import scala.concurrent.duration.Duration
import java.util.concurrent.TimeUnit
import play.api.Play.current
import play.api.Play
import akka.actor.ActorSystem
import akka.actor.Props
import actors.SchedulerActor.ScheduleMessage

trait Global extends GlobalSettings {

  val schedulerDelay = Play.application.configuration.getString("scheduler.play.delay").getOrElse(throw new IllegalStateException("missing 'scheduler.play.delay' configuration parameter"))
  val schedulerFrequency = Play.application.configuration.getString("scheduler.play.frequency").getOrElse(throw new IllegalStateException("missing 'scheduler.play.frequency' configuration parameter"))


  override def beforeStart(application: Application): Unit = {
      }

  override def onStop(application: Application) {
      }

  override def onStart(application: Application): Unit = {
    Logger.debug("On Start of application");

    val system = ActorSystem("system")
    val schedulerActorSystem = system.actorOf(Props[SchedulerActor], "schedulerActorSystem")

    def performSomeAction = {
      //Perform some action
      //To be used when scheduling relatively small business logic - Sending mails, etc
    }

    Akka.system.scheduler.schedule(Duration.create(schedulerDelay.toInt, TimeUnit.MILLISECONDS),Duration.create(schedulerFrequency.toInt, TimeUnit.SECONDS))( performSomeAction) (Akka.system.dispatcher)
    Akka.system.scheduler.schedule(Duration.create(schedulerDelay.toInt, TimeUnit.MILLISECONDS),Duration.create(schedulerFrequency.toInt, TimeUnit.SECONDS),schedulerActorSystem, ScheduleMessage("Action"))(Akka.system.dispatcher)

  }
}
