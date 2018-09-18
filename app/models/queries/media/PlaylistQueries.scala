/* Generated File */
package models.queries.media

import models.database.{DatabaseField, Row}
import models.database.DatabaseFieldType._
import models.media.Playlist
import models.queries.BaseQueries
import models.result.ResultFieldHelper
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy

object PlaylistQueries extends BaseQueries[Playlist]("playlist", "Playlist") {
  override val fields = Seq(
    DatabaseField(title = "Playlist Id", prop = "playlistId", col = "PlaylistId", typ = LongType),
    DatabaseField(title = "Name", prop = "name", col = "Name", typ = StringType)
  )
  override protected val pkColumns = Seq("PlaylistId")
  override protected val searchColumns = Seq("PlaylistId", "Name")

  def countAll(filters: Seq[Filter] = Nil) = onCountAll(filters)
  def getAll(filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new GetAll(filters, orderBys, limit, offset)
  }

  def search(q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new Search(q, filters, orderBys, limit, offset)
  }
  def searchCount(q: Option[String], filters: Seq[Filter] = Nil) = new SearchCount(q, filters)
  def searchExact(q: String, orderBys: Seq[OrderBy], limit: Option[Int], offset: Option[Int]) = new SearchExact(q, orderBys, limit, offset)

  def getByPrimaryKey(playlistId: Long) = new GetByPrimaryKey(Seq(playlistId))
  def getByPrimaryKeySeq(playlistIdSeq: Seq[Long]) = new ColSeqQuery(column = "PlaylistId", values = playlistIdSeq)

  final case class CountByName(nameArg: String) extends ColCount(column = "Name", values = Seq(nameArg))
  final case class GetByName(nameArg: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("Name") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(nameArg)
  )
  final case class GetByNameSeq(nameArgSeq: Seq[String]) extends ColSeqQuery(column = "Name", values = nameArgSeq)

  final case class CountByPlaylistId(playlistId: Long) extends ColCount(column = "PlaylistId", values = Seq(playlistId))
  final case class GetByPlaylistId(playlistId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("PlaylistId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(playlistId)
  )
  final case class GetByPlaylistIdSeq(playlistIdSeq: Seq[Long]) extends ColSeqQuery(column = "PlaylistId", values = playlistIdSeq)

  def insert(model: Playlist) = new Insert(model)
  def insertBatch(models: Seq[Playlist]) = new InsertBatch(models)
  def create(dataFields: Seq[DataField]) = new CreateFields(dataFields)

  def removeByPrimaryKey(playlistId: Long) = new RemoveByPrimaryKey(Seq[Any](playlistId))

  def update(playlistId: Long, fields: Seq[DataField]) = new UpdateFields(Seq[Any](playlistId), fields)

  override def fromRow(row: Row) = Playlist(
    playlistId = LongType(row, "PlaylistId"),
    name = StringType.opt(row, "Name")
  )
}
