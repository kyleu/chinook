/* Generated File */
package models.media

import models.result.data.{DataField, DataFieldModel, DataSummary}
import util.JsonSerializers._

object Artist {
  implicit val jsonEncoder: Encoder[Artist] = deriveEncoder
  implicit val jsonDecoder: Decoder[Artist] = deriveDecoder

  def empty(artistId: Long = 0L, name: Option[String] = None) = {
    Artist(artistId, name)
  }
}

final case class Artist(
    artistId: Long,
    name: Option[String]
) extends DataFieldModel {
  override def toDataFields = Seq(
    DataField("artistId", Some(artistId.toString)),
    DataField("name", name)
  )

  def toSummary = DataSummary(model = "artist", pk = Seq(artistId.toString), title = s"$name ($artistId)")
}
