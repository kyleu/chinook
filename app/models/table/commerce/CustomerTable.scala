/* Generated File */
package models.table.commerce

import services.database.SlickQueryService.imports._

object CustomerTable {
  val query = TableQuery[CustomerTable]

  def getByPrimaryKey(customerId: Long) = query.filter(_.customerId === customerId).result.headOption
  def getByPrimaryKeySeq(customerIdSeq: Seq[Long]) = query.filter(_.customerId.inSet(customerIdSeq)).result

  def getBySupportRepId(supportRepId: Long) = query.filter(_.supportRepId === supportRepId).result
  def getBySupportRepIdSeq(supportRepIdSeq: Seq[Long]) = query.filter(_.supportRepId.inSet(supportRepIdSeq)).result
}

class CustomerTable(tag: Tag) extends Table[models.commerce.Customer](tag, "Customer") {
  val customerId = column[Long]("CustomerId")
  val firstName = column[String]("FirstName")
  val lastName = column[String]("LastName")
  val company = column[Option[String]]("Company")
  val address = column[Option[String]]("Address")
  val city = column[Option[String]]("City")
  val state = column[Option[String]]("State")
  val country = column[Option[String]]("Country")
  val postalCode = column[Option[String]]("PostalCode")
  val phone = column[Option[String]]("Phone")
  val fax = column[Option[String]]("Fax")
  val email = column[String]("Email")
  val supportRepId = column[Option[Long]]("SupportRepId")

  val modelPrimaryKey = primaryKey("pk_Customer", customerId)

  override val * = (customerId, firstName, lastName, company, address, city, state, country, postalCode, phone, fax, email, supportRepId) <> (
    (models.commerce.Customer.apply _).tupled,
    models.commerce.Customer.unapply
  )
}

