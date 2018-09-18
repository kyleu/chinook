/* Generated File */
package models.queries.media

import models.database.{DatabaseField, Row}
import models.database.DatabaseFieldType._
import models.media.PlaylistTrack
import models.queries.BaseQueries
import models.result.ResultFieldHelper
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy

object PlaylistTrackQueries extends BaseQueries[PlaylistTrack]("playlistTrack", "PlaylistTrack") {
  override val fields = Seq(
    DatabaseField(title = "Playlist Id", prop = "playlistId", col = "PlaylistId", typ = LongType),
    DatabaseField(title = "Track Id", prop = "trackId", col = "TrackId", typ = LongType)
  )
  override protected val pkColumns = Seq("PlaylistId", "TrackId")
  override protected val searchColumns = Seq("PlaylistId", "TrackId")

  def countAll(filters: Seq[Filter] = Nil) = onCountAll(filters)
  def getAll(filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new GetAll(filters, orderBys, limit, offset)
  }

  def search(q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new Search(q, filters, orderBys, limit, offset)
  }
  def searchCount(q: Option[String], filters: Seq[Filter] = Nil) = new SearchCount(q, filters)
  def searchExact(q: String, orderBys: Seq[OrderBy], limit: Option[Int], offset: Option[Int]) = new SearchExact(q, orderBys, limit, offset)

  def getByPrimaryKey(playlistId: Long, trackId: Long) = new GetByPrimaryKey(Seq[Any](playlistId, trackId))
  def getByPrimaryKeySeq(idSeq: Seq[(Long, Long)]) = new SeqQuery(
    whereClause = Some(idSeq.map(_ => "(\"PlaylistId\" = ? and \"TrackId\" = ?)").mkString(" or ")),
    values = idSeq.flatMap(_.productIterator.toSeq)
  )

  final case class CountByPlaylistId(playlistId: Long) extends ColCount(column = "PlaylistId", values = Seq(playlistId))
  final case class GetByPlaylistId(playlistId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("PlaylistId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(playlistId)
  )
  final case class GetByPlaylistIdSeq(playlistIdSeq: Seq[Long]) extends ColSeqQuery(column = "PlaylistId", values = playlistIdSeq)

  final case class CountByTrackId(trackId: Long) extends ColCount(column = "TrackId", values = Seq(trackId))
  final case class GetByTrackId(trackId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("TrackId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(trackId)
  )
  final case class GetByTrackIdSeq(trackIdSeq: Seq[Long]) extends ColSeqQuery(column = "TrackId", values = trackIdSeq)

  def insert(model: PlaylistTrack) = new Insert(model)
  def insertBatch(models: Seq[PlaylistTrack]) = new InsertBatch(models)
  def create(dataFields: Seq[DataField]) = new CreateFields(dataFields)

  def removeByPrimaryKey(playlistId: Long, trackId: Long) = new RemoveByPrimaryKey(Seq[Any](playlistId, trackId))

  def update(playlistId: Long, trackId: Long, fields: Seq[DataField]) = new UpdateFields(Seq[Any](playlistId, trackId), fields)

  override def fromRow(row: Row) = PlaylistTrack(
    playlistId = LongType(row, "PlaylistId"),
    trackId = LongType(row, "TrackId")
  )
}
