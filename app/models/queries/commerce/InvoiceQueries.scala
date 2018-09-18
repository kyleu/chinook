/* Generated File */
package models.queries.commerce

import java.time.LocalDateTime
import models.commerce.Invoice
import models.database.{DatabaseField, Row}
import models.database.DatabaseFieldType._
import models.queries.BaseQueries
import models.result.ResultFieldHelper
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy

object InvoiceQueries extends BaseQueries[Invoice]("invoice", "Invoice") {
  override val fields = Seq(
    DatabaseField(title = "Invoice Id", prop = "invoiceId", col = "InvoiceId", typ = LongType),
    DatabaseField(title = "Customer Id", prop = "customerId", col = "CustomerId", typ = LongType),
    DatabaseField(title = "Invoice Date", prop = "invoiceDate", col = "InvoiceDate", typ = TimestampType),
    DatabaseField(title = "Billing Address", prop = "billingAddress", col = "BillingAddress", typ = StringType),
    DatabaseField(title = "Billing City", prop = "billingCity", col = "BillingCity", typ = StringType),
    DatabaseField(title = "Billing State", prop = "billingState", col = "BillingState", typ = StringType),
    DatabaseField(title = "Billing Country", prop = "billingCountry", col = "BillingCountry", typ = StringType),
    DatabaseField(title = "Billing Postal Code", prop = "billingPostalCode", col = "BillingPostalCode", typ = StringType),
    DatabaseField(title = "Total", prop = "total", col = "Total", typ = BigDecimalType)
  )
  override protected val pkColumns = Seq("InvoiceId")
  override protected val searchColumns = Seq("InvoiceId", "CustomerId", "InvoiceDate", "Total")

  def countAll(filters: Seq[Filter] = Nil) = onCountAll(filters)
  def getAll(filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new GetAll(filters, orderBys, limit, offset)
  }

  def search(q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new Search(q, filters, orderBys, limit, offset)
  }
  def searchCount(q: Option[String], filters: Seq[Filter] = Nil) = new SearchCount(q, filters)
  def searchExact(q: String, orderBys: Seq[OrderBy], limit: Option[Int], offset: Option[Int]) = new SearchExact(q, orderBys, limit, offset)

  def getByPrimaryKey(invoiceId: Long) = new GetByPrimaryKey(Seq(invoiceId))
  def getByPrimaryKeySeq(invoiceIdSeq: Seq[Long]) = new ColSeqQuery(column = "InvoiceId", values = invoiceIdSeq)

  final case class CountByCustomerId(customerId: Long) extends ColCount(column = "CustomerId", values = Seq(customerId))
  final case class GetByCustomerId(customerId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("CustomerId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(customerId)
  )
  final case class GetByCustomerIdSeq(customerIdSeq: Seq[Long]) extends ColSeqQuery(column = "CustomerId", values = customerIdSeq)

  final case class CountByInvoiceDate(invoiceDate: LocalDateTime) extends ColCount(column = "InvoiceDate", values = Seq(invoiceDate))
  final case class GetByInvoiceDate(invoiceDate: LocalDateTime, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("InvoiceDate") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(invoiceDate)
  )
  final case class GetByInvoiceDateSeq(invoiceDateSeq: Seq[LocalDateTime]) extends ColSeqQuery(column = "InvoiceDate", values = invoiceDateSeq)

  final case class CountByInvoiceId(invoiceId: Long) extends ColCount(column = "InvoiceId", values = Seq(invoiceId))
  final case class GetByInvoiceId(invoiceId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("InvoiceId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(invoiceId)
  )
  final case class GetByInvoiceIdSeq(invoiceIdSeq: Seq[Long]) extends ColSeqQuery(column = "InvoiceId", values = invoiceIdSeq)

  final case class CountByTotal(total: BigDecimal) extends ColCount(column = "Total", values = Seq(total))
  final case class GetByTotal(total: BigDecimal, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("Total") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(total)
  )
  final case class GetByTotalSeq(totalSeq: Seq[BigDecimal]) extends ColSeqQuery(column = "Total", values = totalSeq)

  def insert(model: Invoice) = new Insert(model)
  def insertBatch(models: Seq[Invoice]) = new InsertBatch(models)
  def create(dataFields: Seq[DataField]) = new CreateFields(dataFields)

  def removeByPrimaryKey(invoiceId: Long) = new RemoveByPrimaryKey(Seq[Any](invoiceId))

  def update(invoiceId: Long, fields: Seq[DataField]) = new UpdateFields(Seq[Any](invoiceId), fields)

  override def fromRow(row: Row) = Invoice(
    invoiceId = LongType(row, "InvoiceId"),
    customerId = LongType(row, "CustomerId"),
    invoiceDate = TimestampType(row, "InvoiceDate"),
    billingAddress = StringType.opt(row, "BillingAddress"),
    billingCity = StringType.opt(row, "BillingCity"),
    billingState = StringType.opt(row, "BillingState"),
    billingCountry = StringType.opt(row, "BillingCountry"),
    billingPostalCode = StringType.opt(row, "BillingPostalCode"),
    total = BigDecimalType(row, "Total")
  )
}
