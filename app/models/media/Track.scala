/* Generated File */
package models.media

import models.result.data.{DataField, DataFieldModel, DataSummary}
import util.JsonSerializers._

object Track {
  implicit val jsonEncoder: Encoder[Track] = deriveEncoder
  implicit val jsonDecoder: Decoder[Track] = deriveDecoder

  def empty(trackId: Long = 0L, name: String = "", albumId: Option[Long] = None, mediaTypeId: Long = 0L, genreId: Option[Long] = None, composer: Option[String] = None, milliseconds: Long = 0L, bytes: Option[Long] = None, unitPrice: BigDecimal = BigDecimal(0)) = {
    Track(trackId, name, albumId, mediaTypeId, genreId, composer, milliseconds, bytes, unitPrice)
  }
}

final case class Track(
    trackId: Long,
    name: String,
    albumId: Option[Long],
    mediaTypeId: Long,
    genreId: Option[Long],
    composer: Option[String],
    milliseconds: Long,
    bytes: Option[Long],
    unitPrice: BigDecimal
) extends DataFieldModel {
  override def toDataFields = Seq(
    DataField("trackId", Some(trackId.toString)),
    DataField("name", Some(name)),
    DataField("albumId", albumId.map(_.toString)),
    DataField("mediaTypeId", Some(mediaTypeId.toString)),
    DataField("genreId", genreId.map(_.toString)),
    DataField("composer", composer),
    DataField("milliseconds", Some(milliseconds.toString)),
    DataField("bytes", bytes.map(_.toString)),
    DataField("unitPrice", Some(unitPrice.toString))
  )

  def toSummary = DataSummary(model = "track", pk = Seq(trackId.toString), title = s"$name / $albumId / $mediaTypeId / $genreId ($trackId)")
}
