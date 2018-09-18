/* Generated File */
package models.commerce

import models.result.data.{DataField, DataFieldModel, DataSummary}
import util.JsonSerializers._

object Customer {
  implicit val jsonEncoder: Encoder[Customer] = deriveEncoder
  implicit val jsonDecoder: Decoder[Customer] = deriveDecoder

  def empty(customerId: Long = 0L, firstName: String = "", lastName: String = "", company: Option[String] = None, address: Option[String] = None, city: Option[String] = None, state: Option[String] = None, country: Option[String] = None, postalCode: Option[String] = None, phone: Option[String] = None, fax: Option[String] = None, email: String = "", supportRepId: Option[Long] = None) = {
    Customer(customerId, firstName, lastName, company, address, city, state, country, postalCode, phone, fax, email, supportRepId)
  }
}

final case class Customer(
    customerId: Long,
    firstName: String,
    lastName: String,
    company: Option[String],
    address: Option[String],
    city: Option[String],
    state: Option[String],
    country: Option[String],
    postalCode: Option[String],
    phone: Option[String],
    fax: Option[String],
    email: String,
    supportRepId: Option[Long]
) extends DataFieldModel {
  override def toDataFields = Seq(
    DataField("customerId", Some(customerId.toString)),
    DataField("firstName", Some(firstName)),
    DataField("lastName", Some(lastName)),
    DataField("company", company),
    DataField("address", address),
    DataField("city", city),
    DataField("state", state),
    DataField("country", country),
    DataField("postalCode", postalCode),
    DataField("phone", phone),
    DataField("fax", fax),
    DataField("email", Some(email)),
    DataField("supportRepId", supportRepId.map(_.toString))
  )

  def toSummary = DataSummary(model = "customer", pk = Seq(customerId.toString), title = s"$firstName / $lastName / $company / $email / $supportRepId ($customerId)")
}
