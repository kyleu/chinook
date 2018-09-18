/* Generated File */
package models.media

import models.result.data.{DataField, DataFieldModel, DataSummary}
import util.JsonSerializers._

object Playlist {
  implicit val jsonEncoder: Encoder[Playlist] = deriveEncoder
  implicit val jsonDecoder: Decoder[Playlist] = deriveDecoder

  def empty(playlistId: Long = 0L, name: Option[String] = None) = {
    Playlist(playlistId, name)
  }
}

final case class Playlist(
    playlistId: Long,
    name: Option[String]
) extends DataFieldModel {
  override def toDataFields = Seq(
    DataField("playlistId", Some(playlistId.toString)),
    DataField("name", name)
  )

  def toSummary = DataSummary(model = "playlist", pk = Seq(playlistId.toString), title = s"$name ($playlistId)")
}
