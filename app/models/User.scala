package models

import play.api.db._
import play.api.Play.current

import anorm._
import anorm.SqlParser._


case class User(id:Pk[Long], login_url:Option[String], email:Option[String])

object User {

  // Parsers
  val simple =
    get[Pk[Long]]("users.id") ~
    get[Option[String]]("users.login_url") ~
    get[Option[String]]("users.email") map {
      case id~login_url~email => User(
        id, login_url, email
      )
    }

  // Queries
  def all():List[User] = {
    DB.withConnection {implicit connection =>
      SQL("select * from users").as(simple *)
    }
  }

}