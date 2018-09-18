/* Generated File */
package models.table.media

import services.database.SlickQueryService.imports._

object AlbumTable {
  val query = TableQuery[AlbumTable]

  def getByPrimaryKey(albumId: Long) = query.filter(_.albumId === albumId).result.headOption
  def getByPrimaryKeySeq(albumIdSeq: Seq[Long]) = query.filter(_.albumId.inSet(albumIdSeq)).result

  def getByArtistId(artistId: Long) = query.filter(_.artistId === artistId).result
  def getByArtistIdSeq(artistIdSeq: Seq[Long]) = query.filter(_.artistId.inSet(artistIdSeq)).result
}

class AlbumTable(tag: Tag) extends Table[models.media.Album](tag, "Album") {
  val albumId = column[Long]("AlbumId")
  val title = column[String]("Title")
  val artistId = column[Long]("ArtistId")

  val modelPrimaryKey = primaryKey("pk_Album", albumId)

  override val * = (albumId, title, artistId) <> (
    (models.media.Album.apply _).tupled,
    models.media.Album.unapply
  )
}

