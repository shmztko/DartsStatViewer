package controllers

import play.api._
import play.api.mvc._
import models.Record
import java.util.Date
import java.util.Calendar
import org.joda.time.DateTime

object Application extends Controller {

  def index = Action {
    Redirect(routes.Records.list)
  }
}