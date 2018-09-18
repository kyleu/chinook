/* Generated File */
package models.table.media

import services.database.SlickQueryService.imports._

object ArtistTable {
  val query = TableQuery[ArtistTable]

  def getByPrimaryKey(artistId: Long) = query.filter(_.artistId === artistId).result.headOption
  def getByPrimaryKeySeq(artistIdSeq: Seq[Long]) = query.filter(_.artistId.inSet(artistIdSeq)).result
}

class ArtistTable(tag: Tag) extends Table[models.media.Artist](tag, "Artist") {
  val artistId = column[Long]("ArtistId")
  val name = column[Option[String]]("Name")

  val modelPrimaryKey = primaryKey("pk_Artist", artistId)

  override val * = (artistId, name) <> (
    (models.media.Artist.apply _).tupled,
    models.media.Artist.unapply
  )
}

