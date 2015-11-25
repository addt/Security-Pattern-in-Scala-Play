package controllers

import scala.concurrent.Future
import play.api.mvc._
import play.api._
import play.api.mvc._
import play.api.Play.current
import play.api.libs.ws._
import play.api.libs.ws.ning.NingAsyncHttpClientConfigBuilder
import scala.concurrent.Future

import scala.concurrent._

import scala.util.{Success, Failure}



class AccessController extends Controller {


  def checkAccess = Action.async { request =>
    val holder: WSRequest = WS.url("URl here")

    val complexHolder: WSRequest =
      holder.withHeaders("ContentType" -> "application/json", "Authorization" -> "Basic Auth")

    complexHolder.get() map {
      case response => Ok(response.body)
    }
  }
}