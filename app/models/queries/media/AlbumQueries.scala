/* Generated File */
package models.queries.media

import models.database.{DatabaseField, Row}
import models.database.DatabaseFieldType._
import models.media.Album
import models.queries.BaseQueries
import models.result.ResultFieldHelper
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy

object AlbumQueries extends BaseQueries[Album]("album", "Album") {
  override val fields = Seq(
    DatabaseField(title = "Album Id", prop = "albumId", col = "AlbumId", typ = LongType),
    DatabaseField(title = "Title", prop = "title", col = "Title", typ = StringType),
    DatabaseField(title = "Artist Id", prop = "artistId", col = "ArtistId", typ = LongType)
  )
  override protected val pkColumns = Seq("AlbumId")
  override protected val searchColumns = Seq("AlbumId", "Title", "ArtistId")

  def countAll(filters: Seq[Filter] = Nil) = onCountAll(filters)
  def getAll(filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new GetAll(filters, orderBys, limit, offset)
  }

  def search(q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new Search(q, filters, orderBys, limit, offset)
  }
  def searchCount(q: Option[String], filters: Seq[Filter] = Nil) = new SearchCount(q, filters)
  def searchExact(q: String, orderBys: Seq[OrderBy], limit: Option[Int], offset: Option[Int]) = new SearchExact(q, orderBys, limit, offset)

  def getByPrimaryKey(albumId: Long) = new GetByPrimaryKey(Seq(albumId))
  def getByPrimaryKeySeq(albumIdSeq: Seq[Long]) = new ColSeqQuery(column = "AlbumId", values = albumIdSeq)

  final case class CountByAlbumId(albumId: Long) extends ColCount(column = "AlbumId", values = Seq(albumId))
  final case class GetByAlbumId(albumId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("AlbumId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(albumId)
  )
  final case class GetByAlbumIdSeq(albumIdSeq: Seq[Long]) extends ColSeqQuery(column = "AlbumId", values = albumIdSeq)

  final case class CountByArtistId(artistId: Long) extends ColCount(column = "ArtistId", values = Seq(artistId))
  final case class GetByArtistId(artistId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("ArtistId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(artistId)
  )
  final case class GetByArtistIdSeq(artistIdSeq: Seq[Long]) extends ColSeqQuery(column = "ArtistId", values = artistIdSeq)

  final case class CountByTitle(title: String) extends ColCount(column = "Title", values = Seq(title))
  final case class GetByTitle(title: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("Title") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(title)
  )
  final case class GetByTitleSeq(titleSeq: Seq[String]) extends ColSeqQuery(column = "Title", values = titleSeq)

  def insert(model: Album) = new Insert(model)
  def insertBatch(models: Seq[Album]) = new InsertBatch(models)
  def create(dataFields: Seq[DataField]) = new CreateFields(dataFields)

  def removeByPrimaryKey(albumId: Long) = new RemoveByPrimaryKey(Seq[Any](albumId))

  def update(albumId: Long, fields: Seq[DataField]) = new UpdateFields(Seq[Any](albumId), fields)

  override def fromRow(row: Row) = Album(
    albumId = LongType(row, "AlbumId"),
    title = StringType(row, "Title"),
    artistId = LongType(row, "ArtistId")
  )
}
