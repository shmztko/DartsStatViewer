package controllers

import play.api._
import play.api.mvc._
import models.Record
import java.util.Date
import java.util.Calendar
import org.joda.time.DateTime

object Records extends Controller {

  def list = Action {
    Ok(views.html.index(Record.all()))
  }

  def show(year:Int, month:Int) = Action {
    var fromDateTime = new DateTime(year, month, 1, 0, 0, 0)
    var toDateTime = new DateTime(year, month, 1, 23, 59, 59)

    val from = fromDateTime.withDayOfMonth(fromDateTime.dayOfMonth().getMinimumValue()).toDate()
    val to = toDateTime.withDayOfMonth(toDateTime.dayOfMonth().getMaximumValue()).toDate()

    val list = Record.findByDate(from, to)
    Ok(views.html.index(list))
  }

}