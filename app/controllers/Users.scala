package controllers

import play.api._
import play.api.mvc._
import models.Record
import java.util.Date
import java.util.Calendar
import org.joda.time.DateTime
import models.User

object Users extends Controller {

  def list = Action {
    Ok(views.html.users(User.all()))
  }
}