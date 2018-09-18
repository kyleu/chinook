/* Generated File */
package models.queries.media

import models.database.{DatabaseField, Row}
import models.database.DatabaseFieldType._
import models.media.Genre
import models.queries.BaseQueries
import models.result.ResultFieldHelper
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy

object GenreQueries extends BaseQueries[Genre]("genre", "Genre") {
  override val fields = Seq(
    DatabaseField(title = "Genre Id", prop = "genreId", col = "GenreId", typ = LongType),
    DatabaseField(title = "Name", prop = "name", col = "Name", typ = StringType)
  )
  override protected val pkColumns = Seq("GenreId")
  override protected val searchColumns = Seq("GenreId", "Name")

  def countAll(filters: Seq[Filter] = Nil) = onCountAll(filters)
  def getAll(filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new GetAll(filters, orderBys, limit, offset)
  }

  def search(q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new Search(q, filters, orderBys, limit, offset)
  }
  def searchCount(q: Option[String], filters: Seq[Filter] = Nil) = new SearchCount(q, filters)
  def searchExact(q: String, orderBys: Seq[OrderBy], limit: Option[Int], offset: Option[Int]) = new SearchExact(q, orderBys, limit, offset)

  def getByPrimaryKey(genreId: Long) = new GetByPrimaryKey(Seq(genreId))
  def getByPrimaryKeySeq(genreIdSeq: Seq[Long]) = new ColSeqQuery(column = "GenreId", values = genreIdSeq)

  final case class CountByGenreId(genreId: Long) extends ColCount(column = "GenreId", values = Seq(genreId))
  final case class GetByGenreId(genreId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("GenreId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(genreId)
  )
  final case class GetByGenreIdSeq(genreIdSeq: Seq[Long]) extends ColSeqQuery(column = "GenreId", values = genreIdSeq)

  final case class CountByName(nameArg: String) extends ColCount(column = "Name", values = Seq(nameArg))
  final case class GetByName(nameArg: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("Name") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(nameArg)
  )
  final case class GetByNameSeq(nameArgSeq: Seq[String]) extends ColSeqQuery(column = "Name", values = nameArgSeq)

  def insert(model: Genre) = new Insert(model)
  def insertBatch(models: Seq[Genre]) = new InsertBatch(models)
  def create(dataFields: Seq[DataField]) = new CreateFields(dataFields)

  def removeByPrimaryKey(genreId: Long) = new RemoveByPrimaryKey(Seq[Any](genreId))

  def update(genreId: Long, fields: Seq[DataField]) = new UpdateFields(Seq[Any](genreId), fields)

  override def fromRow(row: Row) = Genre(
    genreId = LongType(row, "GenreId"),
    name = StringType.opt(row, "Name")
  )
}
