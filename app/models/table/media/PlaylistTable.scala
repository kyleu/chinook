/* Generated File */
package models.table.media

import services.database.SlickQueryService.imports._

object PlaylistTable {
  val query = TableQuery[PlaylistTable]

  def getByPrimaryKey(playlistId: Long) = query.filter(_.playlistId === playlistId).result.headOption
  def getByPrimaryKeySeq(playlistIdSeq: Seq[Long]) = query.filter(_.playlistId.inSet(playlistIdSeq)).result
}

class PlaylistTable(tag: Tag) extends Table[models.media.Playlist](tag, "Playlist") {
  val playlistId = column[Long]("PlaylistId")
  val name = column[Option[String]]("Name")

  val modelPrimaryKey = primaryKey("pk_Playlist", playlistId)

  override val * = (playlistId, name) <> (
    (models.media.Playlist.apply _).tupled,
    models.media.Playlist.unapply
  )
}

