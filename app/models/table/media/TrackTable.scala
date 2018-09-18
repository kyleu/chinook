/* Generated File */
package models.table.media

import services.database.SlickQueryService.imports._

object TrackTable {
  val query = TableQuery[TrackTable]

  def getByPrimaryKey(trackId: Long) = query.filter(_.trackId === trackId).result.headOption
  def getByPrimaryKeySeq(trackIdSeq: Seq[Long]) = query.filter(_.trackId.inSet(trackIdSeq)).result

  def getByAlbumId(albumId: Long) = query.filter(_.albumId === albumId).result
  def getByAlbumIdSeq(albumIdSeq: Seq[Long]) = query.filter(_.albumId.inSet(albumIdSeq)).result

  def getByMediaTypeId(mediaTypeId: Long) = query.filter(_.mediaTypeId === mediaTypeId).result
  def getByMediaTypeIdSeq(mediaTypeIdSeq: Seq[Long]) = query.filter(_.mediaTypeId.inSet(mediaTypeIdSeq)).result

  def getByGenreId(genreId: Long) = query.filter(_.genreId === genreId).result
  def getByGenreIdSeq(genreIdSeq: Seq[Long]) = query.filter(_.genreId.inSet(genreIdSeq)).result
}

class TrackTable(tag: Tag) extends Table[models.media.Track](tag, "Track") {
  val trackId = column[Long]("TrackId")
  val name = column[String]("Name")
  val albumId = column[Option[Long]]("AlbumId")
  val mediaTypeId = column[Long]("MediaTypeId")
  val genreId = column[Option[Long]]("GenreId")
  val composer = column[Option[String]]("Composer")
  val milliseconds = column[Long]("Milliseconds")
  val bytes = column[Option[Long]]("Bytes")
  val unitPrice = column[BigDecimal]("UnitPrice")

  val modelPrimaryKey = primaryKey("pk_Track", trackId)

  override val * = (trackId, name, albumId, mediaTypeId, genreId, composer, milliseconds, bytes, unitPrice) <> (
    (models.media.Track.apply _).tupled,
    models.media.Track.unapply
  )
}

