/* Generated File */
package models.queries.media

import models.database.{DatabaseField, Row}
import models.database.DatabaseFieldType._
import models.media.Track
import models.queries.BaseQueries
import models.result.ResultFieldHelper
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy

object TrackQueries extends BaseQueries[Track]("track", "Track") {
  override val fields = Seq(
    DatabaseField(title = "Track Id", prop = "trackId", col = "TrackId", typ = LongType),
    DatabaseField(title = "Name", prop = "name", col = "Name", typ = StringType),
    DatabaseField(title = "Album Id", prop = "albumId", col = "AlbumId", typ = LongType),
    DatabaseField(title = "Media Type Id", prop = "mediaTypeId", col = "MediaTypeId", typ = LongType),
    DatabaseField(title = "Genre Id", prop = "genreId", col = "GenreId", typ = LongType),
    DatabaseField(title = "Composer", prop = "composer", col = "Composer", typ = StringType),
    DatabaseField(title = "Milliseconds", prop = "milliseconds", col = "Milliseconds", typ = LongType),
    DatabaseField(title = "Bytes", prop = "bytes", col = "Bytes", typ = LongType),
    DatabaseField(title = "Unit Price", prop = "unitPrice", col = "UnitPrice", typ = BigDecimalType)
  )
  override protected val pkColumns = Seq("TrackId")
  override protected val searchColumns = Seq("TrackId", "Name", "AlbumId", "MediaTypeId", "GenreId")

  def countAll(filters: Seq[Filter] = Nil) = onCountAll(filters)
  def getAll(filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new GetAll(filters, orderBys, limit, offset)
  }

  def search(q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new Search(q, filters, orderBys, limit, offset)
  }
  def searchCount(q: Option[String], filters: Seq[Filter] = Nil) = new SearchCount(q, filters)
  def searchExact(q: String, orderBys: Seq[OrderBy], limit: Option[Int], offset: Option[Int]) = new SearchExact(q, orderBys, limit, offset)

  def getByPrimaryKey(trackId: Long) = new GetByPrimaryKey(Seq(trackId))
  def getByPrimaryKeySeq(trackIdSeq: Seq[Long]) = new ColSeqQuery(column = "TrackId", values = trackIdSeq)

  final case class CountByAlbumId(albumId: Long) extends ColCount(column = "AlbumId", values = Seq(albumId))
  final case class GetByAlbumId(albumId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("AlbumId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(albumId)
  )
  final case class GetByAlbumIdSeq(albumIdSeq: Seq[Long]) extends ColSeqQuery(column = "AlbumId", values = albumIdSeq)

  final case class CountByGenreId(genreId: Long) extends ColCount(column = "GenreId", values = Seq(genreId))
  final case class GetByGenreId(genreId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("GenreId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(genreId)
  )
  final case class GetByGenreIdSeq(genreIdSeq: Seq[Long]) extends ColSeqQuery(column = "GenreId", values = genreIdSeq)

  final case class CountByMediaTypeId(mediaTypeId: Long) extends ColCount(column = "MediaTypeId", values = Seq(mediaTypeId))
  final case class GetByMediaTypeId(mediaTypeId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("MediaTypeId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(mediaTypeId)
  )
  final case class GetByMediaTypeIdSeq(mediaTypeIdSeq: Seq[Long]) extends ColSeqQuery(column = "MediaTypeId", values = mediaTypeIdSeq)

  final case class CountByName(nameArg: String) extends ColCount(column = "Name", values = Seq(nameArg))
  final case class GetByName(nameArg: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("Name") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(nameArg)
  )
  final case class GetByNameSeq(nameArgSeq: Seq[String]) extends ColSeqQuery(column = "Name", values = nameArgSeq)

  final case class CountByTrackId(trackId: Long) extends ColCount(column = "TrackId", values = Seq(trackId))
  final case class GetByTrackId(trackId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("TrackId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(trackId)
  )
  final case class GetByTrackIdSeq(trackIdSeq: Seq[Long]) extends ColSeqQuery(column = "TrackId", values = trackIdSeq)

  def insert(model: Track) = new Insert(model)
  def insertBatch(models: Seq[Track]) = new InsertBatch(models)
  def create(dataFields: Seq[DataField]) = new CreateFields(dataFields)

  def removeByPrimaryKey(trackId: Long) = new RemoveByPrimaryKey(Seq[Any](trackId))

  def update(trackId: Long, fields: Seq[DataField]) = new UpdateFields(Seq[Any](trackId), fields)

  override def fromRow(row: Row) = Track(
    trackId = LongType(row, "TrackId"),
    name = StringType(row, "Name"),
    albumId = LongType.opt(row, "AlbumId"),
    mediaTypeId = LongType(row, "MediaTypeId"),
    genreId = LongType.opt(row, "GenreId"),
    composer = StringType.opt(row, "Composer"),
    milliseconds = LongType(row, "Milliseconds"),
    bytes = LongType.opt(row, "Bytes"),
    unitPrice = BigDecimalType(row, "UnitPrice")
  )
}
