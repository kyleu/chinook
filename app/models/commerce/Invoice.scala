/* Generated File */
package models.commerce

import java.time.LocalDateTime
import models.result.data.{DataField, DataFieldModel, DataSummary}
import util.JsonSerializers._

object Invoice {
  implicit val jsonEncoder: Encoder[Invoice] = deriveEncoder
  implicit val jsonDecoder: Decoder[Invoice] = deriveDecoder

  def empty(invoiceId: Long = 0L, customerId: Long = 0L, invoiceDate: LocalDateTime = util.DateUtils.now, billingAddress: Option[String] = None, billingCity: Option[String] = None, billingState: Option[String] = None, billingCountry: Option[String] = None, billingPostalCode: Option[String] = None, total: BigDecimal = BigDecimal(0)) = {
    Invoice(invoiceId, customerId, invoiceDate, billingAddress, billingCity, billingState, billingCountry, billingPostalCode, total)
  }
}

final case class Invoice(
    invoiceId: Long,
    customerId: Long,
    invoiceDate: LocalDateTime,
    billingAddress: Option[String],
    billingCity: Option[String],
    billingState: Option[String],
    billingCountry: Option[String],
    billingPostalCode: Option[String],
    total: BigDecimal
) extends DataFieldModel {
  override def toDataFields = Seq(
    DataField("invoiceId", Some(invoiceId.toString)),
    DataField("customerId", Some(customerId.toString)),
    DataField("invoiceDate", Some(invoiceDate.toString)),
    DataField("billingAddress", billingAddress),
    DataField("billingCity", billingCity),
    DataField("billingState", billingState),
    DataField("billingCountry", billingCountry),
    DataField("billingPostalCode", billingPostalCode),
    DataField("total", Some(total.toString))
  )

  def toSummary = DataSummary(model = "invoice", pk = Seq(invoiceId.toString), title = s"$customerId / $invoiceDate / $total ($invoiceId)")
}
