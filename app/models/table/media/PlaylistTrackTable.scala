/* Generated File */
package models.table.media

import services.database.SlickQueryService.imports._

object PlaylistTrackTable {
  val query = TableQuery[PlaylistTrackTable]

  def getByPrimaryKey(playlistId: Long, trackId: Long) = query.filter(o => o.playlistId === playlistId && o.trackId === trackId).result.headOption

  def getByTrackId(trackId: Long) = query.filter(_.trackId === trackId).result
  def getByTrackIdSeq(trackIdSeq: Seq[Long]) = query.filter(_.trackId.inSet(trackIdSeq)).result

  def getByPlaylistId(playlistId: Long) = query.filter(_.playlistId === playlistId).result
  def getByPlaylistIdSeq(playlistIdSeq: Seq[Long]) = query.filter(_.playlistId.inSet(playlistIdSeq)).result
}

class PlaylistTrackTable(tag: Tag) extends Table[models.media.PlaylistTrack](tag, "PlaylistTrack") {
  val playlistId = column[Long]("PlaylistId")
  val trackId = column[Long]("TrackId")

  val modelPrimaryKey = primaryKey("pk_PlaylistTrack", (playlistId, trackId))

  override val * = (playlistId, trackId) <> (
    (models.media.PlaylistTrack.apply _).tupled,
    models.media.PlaylistTrack.unapply
  )
}

