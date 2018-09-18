/* Generated File */
package models.table.media

import services.database.SlickQueryService.imports._

object MediaTypeTable {
  val query = TableQuery[MediaTypeTable]

  def getByPrimaryKey(mediaTypeId: Long) = query.filter(_.mediaTypeId === mediaTypeId).result.headOption
  def getByPrimaryKeySeq(mediaTypeIdSeq: Seq[Long]) = query.filter(_.mediaTypeId.inSet(mediaTypeIdSeq)).result
}

class MediaTypeTable(tag: Tag) extends Table[models.media.MediaType](tag, "MediaType") {
  val mediaTypeId = column[Long]("MediaTypeId")
  val name = column[Option[String]]("Name")

  val modelPrimaryKey = primaryKey("pk_MediaType", mediaTypeId)

  override val * = (mediaTypeId, name) <> (
    (models.media.MediaType.apply _).tupled,
    models.media.MediaType.unapply
  )
}

