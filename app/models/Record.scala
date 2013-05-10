package models

import java.util.{Date}

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._

case class Record(id:Pk[Long], played_at:Option[Date])

object Record {

    // Parser
    val records = {
    get[Pk[Long]]("records.id") ~
    get[Option[Date]]("records.played_at") map {
      case id~played_at => Record(
        id, played_at
      )
    }
  }

  // Queries
  def all():List[Record] = {
    DB.withConnection {implicit connection =>
      SQL("select * from records").as(records *)
    }
  }

  def findByDate(from:Date, to:Date):List[Record] = {
    DB.withConnection {implicit connection =>
      SQL(
        """
          select * from records
          where records.played_at between {from} and {to}
        """
      ).on(
          'from -> from, 'to -> to
      ).as(records.*)
    }
  }
}