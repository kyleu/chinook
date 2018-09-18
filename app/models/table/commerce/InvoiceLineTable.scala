/* Generated File */
package models.table.commerce

import services.database.SlickQueryService.imports._

object InvoiceLineTable {
  val query = TableQuery[InvoiceLineTable]

  def getByPrimaryKey(invoiceLineId: Long) = query.filter(_.invoiceLineId === invoiceLineId).result.headOption
  def getByPrimaryKeySeq(invoiceLineIdSeq: Seq[Long]) = query.filter(_.invoiceLineId.inSet(invoiceLineIdSeq)).result

  def getByInvoiceId(invoiceId: Long) = query.filter(_.invoiceId === invoiceId).result
  def getByInvoiceIdSeq(invoiceIdSeq: Seq[Long]) = query.filter(_.invoiceId.inSet(invoiceIdSeq)).result

  def getByTrackId(trackId: Long) = query.filter(_.trackId === trackId).result
  def getByTrackIdSeq(trackIdSeq: Seq[Long]) = query.filter(_.trackId.inSet(trackIdSeq)).result
}

class InvoiceLineTable(tag: Tag) extends Table[models.commerce.InvoiceLine](tag, "InvoiceLine") {
  val invoiceLineId = column[Long]("InvoiceLineId")
  val invoiceId = column[Long]("InvoiceId")
  val trackId = column[Long]("TrackId")
  val unitPrice = column[BigDecimal]("UnitPrice")
  val quantity = column[Long]("Quantity")

  val modelPrimaryKey = primaryKey("pk_InvoiceLine", invoiceLineId)

  override val * = (invoiceLineId, invoiceId, trackId, unitPrice, quantity) <> (
    (models.commerce.InvoiceLine.apply _).tupled,
    models.commerce.InvoiceLine.unapply
  )
}

