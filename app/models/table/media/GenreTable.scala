/* Generated File */
package models.table.media

import services.database.SlickQueryService.imports._

object GenreTable {
  val query = TableQuery[GenreTable]

  def getByPrimaryKey(genreId: Long) = query.filter(_.genreId === genreId).result.headOption
  def getByPrimaryKeySeq(genreIdSeq: Seq[Long]) = query.filter(_.genreId.inSet(genreIdSeq)).result
}

class GenreTable(tag: Tag) extends Table[models.media.Genre](tag, "Genre") {
  val genreId = column[Long]("GenreId")
  val name = column[Option[String]]("Name")

  val modelPrimaryKey = primaryKey("pk_Genre", genreId)

  override val * = (genreId, name) <> (
    (models.media.Genre.apply _).tupled,
    models.media.Genre.unapply
  )
}

