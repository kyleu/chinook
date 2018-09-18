/* Generated File */
package models.media

import models.result.data.{DataField, DataFieldModel, DataSummary}
import util.JsonSerializers._

object PlaylistTrack {
  implicit val jsonEncoder: Encoder[PlaylistTrack] = deriveEncoder
  implicit val jsonDecoder: Decoder[PlaylistTrack] = deriveDecoder

  def empty(playlistId: Long = 0L, trackId: Long = 0L) = {
    PlaylistTrack(playlistId, trackId)
  }
}

final case class PlaylistTrack(
    playlistId: Long,
    trackId: Long
) extends DataFieldModel {
  override def toDataFields = Seq(
    DataField("playlistId", Some(playlistId.toString)),
    DataField("trackId", Some(trackId.toString))
  )

  def toSummary = DataSummary(model = "playlistTrack", pk = Seq(playlistId.toString, trackId.toString), title = s"$playlistId, $trackId")
}
