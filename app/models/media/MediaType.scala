/* Generated File */
package models.media

import models.result.data.{DataField, DataFieldModel, DataSummary}
import util.JsonSerializers._

object MediaType {
  implicit val jsonEncoder: Encoder[MediaType] = deriveEncoder
  implicit val jsonDecoder: Decoder[MediaType] = deriveDecoder

  def empty(mediaTypeId: Long = 0L, name: Option[String] = None) = {
    MediaType(mediaTypeId, name)
  }
}

final case class MediaType(
    mediaTypeId: Long,
    name: Option[String]
) extends DataFieldModel {
  override def toDataFields = Seq(
    DataField("mediaTypeId", Some(mediaTypeId.toString)),
    DataField("name", name)
  )

  def toSummary = DataSummary(model = "mediaType", pk = Seq(mediaTypeId.toString), title = s"$name ($mediaTypeId)")
}
