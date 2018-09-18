/* Generated File */
package models.commerce

import models.result.data.{DataField, DataFieldModel, DataSummary}
import util.JsonSerializers._

object InvoiceLine {
  implicit val jsonEncoder: Encoder[InvoiceLine] = deriveEncoder
  implicit val jsonDecoder: Decoder[InvoiceLine] = deriveDecoder

  def empty(invoiceLineId: Long = 0L, invoiceId: Long = 0L, trackId: Long = 0L, unitPrice: BigDecimal = BigDecimal(0), quantity: Long = 0L) = {
    InvoiceLine(invoiceLineId, invoiceId, trackId, unitPrice, quantity)
  }
}

final case class InvoiceLine(
    invoiceLineId: Long,
    invoiceId: Long,
    trackId: Long,
    unitPrice: BigDecimal,
    quantity: Long
) extends DataFieldModel {
  override def toDataFields = Seq(
    DataField("invoiceLineId", Some(invoiceLineId.toString)),
    DataField("invoiceId", Some(invoiceId.toString)),
    DataField("trackId", Some(trackId.toString)),
    DataField("unitPrice", Some(unitPrice.toString)),
    DataField("quantity", Some(quantity.toString))
  )

  def toSummary = DataSummary(model = "invoiceLine", pk = Seq(invoiceLineId.toString), title = s"$invoiceId / $trackId / $unitPrice / $quantity ($invoiceLineId)")
}
