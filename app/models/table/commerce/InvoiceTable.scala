/* Generated File */
package models.table.commerce

import java.time.LocalDateTime
import services.database.SlickQueryService.imports._

object InvoiceTable {
  val query = TableQuery[InvoiceTable]

  def getByPrimaryKey(invoiceId: Long) = query.filter(_.invoiceId === invoiceId).result.headOption
  def getByPrimaryKeySeq(invoiceIdSeq: Seq[Long]) = query.filter(_.invoiceId.inSet(invoiceIdSeq)).result

  def getByCustomerId(customerId: Long) = query.filter(_.customerId === customerId).result
  def getByCustomerIdSeq(customerIdSeq: Seq[Long]) = query.filter(_.customerId.inSet(customerIdSeq)).result
}

class InvoiceTable(tag: Tag) extends Table[models.commerce.Invoice](tag, "Invoice") {
  val invoiceId = column[Long]("InvoiceId")
  val customerId = column[Long]("CustomerId")
  val invoiceDate = column[LocalDateTime]("InvoiceDate")
  val billingAddress = column[Option[String]]("BillingAddress")
  val billingCity = column[Option[String]]("BillingCity")
  val billingState = column[Option[String]]("BillingState")
  val billingCountry = column[Option[String]]("BillingCountry")
  val billingPostalCode = column[Option[String]]("BillingPostalCode")
  val total = column[BigDecimal]("Total")

  val modelPrimaryKey = primaryKey("pk_Invoice", invoiceId)

  override val * = (invoiceId, customerId, invoiceDate, billingAddress, billingCity, billingState, billingCountry, billingPostalCode, total) <> (
    (models.commerce.Invoice.apply _).tupled,
    models.commerce.Invoice.unapply
  )
}

