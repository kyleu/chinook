/* Generated File */
package models.queries.commerce

import models.commerce.InvoiceLine
import models.database.{DatabaseField, Row}
import models.database.DatabaseFieldType._
import models.queries.BaseQueries
import models.result.ResultFieldHelper
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy

object InvoiceLineQueries extends BaseQueries[InvoiceLine]("invoiceLine", "InvoiceLine") {
  override val fields = Seq(
    DatabaseField(title = "Invoice Line Id", prop = "invoiceLineId", col = "InvoiceLineId", typ = LongType),
    DatabaseField(title = "Invoice Id", prop = "invoiceId", col = "InvoiceId", typ = LongType),
    DatabaseField(title = "Track Id", prop = "trackId", col = "TrackId", typ = LongType),
    DatabaseField(title = "Unit Price", prop = "unitPrice", col = "UnitPrice", typ = BigDecimalType),
    DatabaseField(title = "Quantity", prop = "quantity", col = "Quantity", typ = LongType)
  )
  override protected val pkColumns = Seq("InvoiceLineId")
  override protected val searchColumns = Seq("InvoiceLineId", "InvoiceId", "TrackId", "UnitPrice", "Quantity")

  def countAll(filters: Seq[Filter] = Nil) = onCountAll(filters)
  def getAll(filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new GetAll(filters, orderBys, limit, offset)
  }

  def search(q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new Search(q, filters, orderBys, limit, offset)
  }
  def searchCount(q: Option[String], filters: Seq[Filter] = Nil) = new SearchCount(q, filters)
  def searchExact(q: String, orderBys: Seq[OrderBy], limit: Option[Int], offset: Option[Int]) = new SearchExact(q, orderBys, limit, offset)

  def getByPrimaryKey(invoiceLineId: Long) = new GetByPrimaryKey(Seq(invoiceLineId))
  def getByPrimaryKeySeq(invoiceLineIdSeq: Seq[Long]) = new ColSeqQuery(column = "InvoiceLineId", values = invoiceLineIdSeq)

  final case class CountByInvoiceId(invoiceId: Long) extends ColCount(column = "InvoiceId", values = Seq(invoiceId))
  final case class GetByInvoiceId(invoiceId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("InvoiceId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(invoiceId)
  )
  final case class GetByInvoiceIdSeq(invoiceIdSeq: Seq[Long]) extends ColSeqQuery(column = "InvoiceId", values = invoiceIdSeq)

  final case class CountByInvoiceLineId(invoiceLineId: Long) extends ColCount(column = "InvoiceLineId", values = Seq(invoiceLineId))
  final case class GetByInvoiceLineId(invoiceLineId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("InvoiceLineId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(invoiceLineId)
  )
  final case class GetByInvoiceLineIdSeq(invoiceLineIdSeq: Seq[Long]) extends ColSeqQuery(column = "InvoiceLineId", values = invoiceLineIdSeq)

  final case class CountByQuantity(quantity: Long) extends ColCount(column = "Quantity", values = Seq(quantity))
  final case class GetByQuantity(quantity: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("Quantity") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(quantity)
  )
  final case class GetByQuantitySeq(quantitySeq: Seq[Long]) extends ColSeqQuery(column = "Quantity", values = quantitySeq)

  final case class CountByTrackId(trackId: Long) extends ColCount(column = "TrackId", values = Seq(trackId))
  final case class GetByTrackId(trackId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("TrackId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(trackId)
  )
  final case class GetByTrackIdSeq(trackIdSeq: Seq[Long]) extends ColSeqQuery(column = "TrackId", values = trackIdSeq)

  final case class CountByUnitPrice(unitPrice: BigDecimal) extends ColCount(column = "UnitPrice", values = Seq(unitPrice))
  final case class GetByUnitPrice(unitPrice: BigDecimal, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("UnitPrice") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(unitPrice)
  )
  final case class GetByUnitPriceSeq(unitPriceSeq: Seq[BigDecimal]) extends ColSeqQuery(column = "UnitPrice", values = unitPriceSeq)

  def insert(model: InvoiceLine) = new Insert(model)
  def insertBatch(models: Seq[InvoiceLine]) = new InsertBatch(models)
  def create(dataFields: Seq[DataField]) = new CreateFields(dataFields)

  def removeByPrimaryKey(invoiceLineId: Long) = new RemoveByPrimaryKey(Seq[Any](invoiceLineId))

  def update(invoiceLineId: Long, fields: Seq[DataField]) = new UpdateFields(Seq[Any](invoiceLineId), fields)

  override def fromRow(row: Row) = InvoiceLine(
    invoiceLineId = LongType(row, "InvoiceLineId"),
    invoiceId = LongType(row, "InvoiceId"),
    trackId = LongType(row, "TrackId"),
    unitPrice = BigDecimalType(row, "UnitPrice"),
    quantity = LongType(row, "Quantity")
  )
}
