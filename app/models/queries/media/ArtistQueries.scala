/* Generated File */
package models.queries.media

import models.database.{DatabaseField, Row}
import models.database.DatabaseFieldType._
import models.media.Artist
import models.queries.BaseQueries
import models.result.ResultFieldHelper
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy

object ArtistQueries extends BaseQueries[Artist]("artist", "Artist") {
  override val fields = Seq(
    DatabaseField(title = "Artist Id", prop = "artistId", col = "ArtistId", typ = LongType),
    DatabaseField(title = "Name", prop = "name", col = "Name", typ = StringType)
  )
  override protected val pkColumns = Seq("ArtistId")
  override protected val searchColumns = Seq("ArtistId", "Name")

  def countAll(filters: Seq[Filter] = Nil) = onCountAll(filters)
  def getAll(filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new GetAll(filters, orderBys, limit, offset)
  }

  def search(q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new Search(q, filters, orderBys, limit, offset)
  }
  def searchCount(q: Option[String], filters: Seq[Filter] = Nil) = new SearchCount(q, filters)
  def searchExact(q: String, orderBys: Seq[OrderBy], limit: Option[Int], offset: Option[Int]) = new SearchExact(q, orderBys, limit, offset)

  def getByPrimaryKey(artistId: Long) = new GetByPrimaryKey(Seq(artistId))
  def getByPrimaryKeySeq(artistIdSeq: Seq[Long]) = new ColSeqQuery(column = "ArtistId", values = artistIdSeq)

  final case class CountByArtistId(artistId: Long) extends ColCount(column = "ArtistId", values = Seq(artistId))
  final case class GetByArtistId(artistId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("ArtistId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(artistId)
  )
  final case class GetByArtistIdSeq(artistIdSeq: Seq[Long]) extends ColSeqQuery(column = "ArtistId", values = artistIdSeq)

  final case class CountByName(nameArg: String) extends ColCount(column = "Name", values = Seq(nameArg))
  final case class GetByName(nameArg: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("Name") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(nameArg)
  )
  final case class GetByNameSeq(nameArgSeq: Seq[String]) extends ColSeqQuery(column = "Name", values = nameArgSeq)

  def insert(model: Artist) = new Insert(model)
  def insertBatch(models: Seq[Artist]) = new InsertBatch(models)
  def create(dataFields: Seq[DataField]) = new CreateFields(dataFields)

  def removeByPrimaryKey(artistId: Long) = new RemoveByPrimaryKey(Seq[Any](artistId))

  def update(artistId: Long, fields: Seq[DataField]) = new UpdateFields(Seq[Any](artistId), fields)

  override def fromRow(row: Row) = Artist(
    artistId = LongType(row, "ArtistId"),
    name = StringType.opt(row, "Name")
  )
}
