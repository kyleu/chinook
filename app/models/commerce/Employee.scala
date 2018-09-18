/* Generated File */
package models.commerce

import java.time.LocalDateTime
import models.result.data.{DataField, DataFieldModel, DataSummary}
import util.JsonSerializers._

object Employee {
  implicit val jsonEncoder: Encoder[Employee] = deriveEncoder
  implicit val jsonDecoder: Decoder[Employee] = deriveDecoder

  def empty(employeeId: Long = 0L, lastName: String = "", firstName: String = "", title: Option[String] = None, reportsTo: Option[Long] = None, birthDate: Option[LocalDateTime] = None, hireDate: Option[LocalDateTime] = None, address: Option[String] = None, city: Option[String] = None, state: Option[String] = None, country: Option[String] = None, postalCode: Option[String] = None, phone: Option[String] = None, fax: Option[String] = None, email: Option[String] = None) = {
    Employee(employeeId, lastName, firstName, title, reportsTo, birthDate, hireDate, address, city, state, country, postalCode, phone, fax, email)
  }
}

final case class Employee(
    employeeId: Long,
    lastName: String,
    firstName: String,
    title: Option[String],
    reportsTo: Option[Long],
    birthDate: Option[LocalDateTime],
    hireDate: Option[LocalDateTime],
    address: Option[String],
    city: Option[String],
    state: Option[String],
    country: Option[String],
    postalCode: Option[String],
    phone: Option[String],
    fax: Option[String],
    email: Option[String]
) extends DataFieldModel {
  override def toDataFields = Seq(
    DataField("employeeId", Some(employeeId.toString)),
    DataField("lastName", Some(lastName)),
    DataField("firstName", Some(firstName)),
    DataField("title", title),
    DataField("reportsTo", reportsTo.map(_.toString)),
    DataField("birthDate", birthDate.map(_.toString)),
    DataField("hireDate", hireDate.map(_.toString)),
    DataField("address", address),
    DataField("city", city),
    DataField("state", state),
    DataField("country", country),
    DataField("postalCode", postalCode),
    DataField("phone", phone),
    DataField("fax", fax),
    DataField("email", email)
  )

  def toSummary = DataSummary(model = "employee", pk = Seq(employeeId.toString), title = s"$lastName / $firstName / $title / $reportsTo / $email ($employeeId)")
}
