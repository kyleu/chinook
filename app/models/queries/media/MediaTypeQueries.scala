/* Generated File */
package models.queries.media

import models.database.{DatabaseField, Row}
import models.database.DatabaseFieldType._
import models.media.MediaType
import models.queries.BaseQueries
import models.result.ResultFieldHelper
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy

object MediaTypeQueries extends BaseQueries[MediaType]("mediaType", "MediaType") {
  override val fields = Seq(
    DatabaseField(title = "Media Type Id", prop = "mediaTypeId", col = "MediaTypeId", typ = LongType),
    DatabaseField(title = "Name", prop = "name", col = "Name", typ = StringType)
  )
  override protected val pkColumns = Seq("MediaTypeId")
  override protected val searchColumns = Seq("MediaTypeId", "Name")

  def countAll(filters: Seq[Filter] = Nil) = onCountAll(filters)
  def getAll(filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new GetAll(filters, orderBys, limit, offset)
  }

  def search(q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new Search(q, filters, orderBys, limit, offset)
  }
  def searchCount(q: Option[String], filters: Seq[Filter] = Nil) = new SearchCount(q, filters)
  def searchExact(q: String, orderBys: Seq[OrderBy], limit: Option[Int], offset: Option[Int]) = new SearchExact(q, orderBys, limit, offset)

  def getByPrimaryKey(mediaTypeId: Long) = new GetByPrimaryKey(Seq(mediaTypeId))
  def getByPrimaryKeySeq(mediaTypeIdSeq: Seq[Long]) = new ColSeqQuery(column = "MediaTypeId", values = mediaTypeIdSeq)

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

  def insert(model: MediaType) = new Insert(model)
  def insertBatch(models: Seq[MediaType]) = new InsertBatch(models)
  def create(dataFields: Seq[DataField]) = new CreateFields(dataFields)

  def removeByPrimaryKey(mediaTypeId: Long) = new RemoveByPrimaryKey(Seq[Any](mediaTypeId))

  def update(mediaTypeId: Long, fields: Seq[DataField]) = new UpdateFields(Seq[Any](mediaTypeId), fields)

  override def fromRow(row: Row) = MediaType(
    mediaTypeId = LongType(row, "MediaTypeId"),
    name = StringType.opt(row, "Name")
  )
}
