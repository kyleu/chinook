/* Generated File */
package models.media

import models.result.data.{DataField, DataFieldModel, DataSummary}
import util.JsonSerializers._

object Album {
  implicit val jsonEncoder: Encoder[Album] = deriveEncoder
  implicit val jsonDecoder: Decoder[Album] = deriveDecoder

  def empty(albumId: Long = 0L, title: String = "", artistId: Long = 0L) = {
    Album(albumId, title, artistId)
  }
}

final case class Album(
    albumId: Long,
    title: String,
    artistId: Long
) extends DataFieldModel {
  override def toDataFields = Seq(
    DataField("albumId", Some(albumId.toString)),
    DataField("title", Some(title)),
    DataField("artistId", Some(artistId.toString))
  )

  def toSummary = DataSummary(model = "album", pk = Seq(albumId.toString), title = s"$title / $artistId ($albumId)")
}
