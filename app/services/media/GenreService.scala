/* Generated File */
package services.media

import models.auth.Credentials
import models.media.Genre
import models.queries.media.GenreQueries
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import scala.concurrent.Future
import services.ModelServiceHelper
import services.database.ApplicationDatabase
import util.FutureUtils.serviceContext
import util.tracing.{TraceData, TracingService}

@javax.inject.Singleton
class GenreService @javax.inject.Inject() (override val tracing: TracingService) extends ModelServiceHelper[Genre]("genre") {
  def getByPrimaryKey(creds: Credentials, genreId: Long)(implicit trace: TraceData) = {
    traceF("get.by.primary.key")(td => ApplicationDatabase.queryF(GenreQueries.getByPrimaryKey(genreId))(td))
  }
  def getByPrimaryKeyRequired(creds: Credentials, genreId: Long)(implicit trace: TraceData) = getByPrimaryKey(creds, genreId).map { opt =>
    opt.getOrElse(throw new IllegalStateException(s"Cannot load genre with genreId [$genreId]."))
  }
  def getByPrimaryKeySeq(creds: Credentials, genreIdSeq: Seq[Long])(implicit trace: TraceData) = {
    traceF("get.by.primary.key.seq")(td => ApplicationDatabase.queryF(GenreQueries.getByPrimaryKeySeq(genreIdSeq))(td))
  }

  override def countAll(creds: Credentials, filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("get.all.count")(td => ApplicationDatabase.queryF(GenreQueries.countAll(filters))(td))
  }
  override def getAll(creds: Credentials, filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = {
    traceF("get.all")(td => ApplicationDatabase.queryF(GenreQueries.getAll(filters, orderBys, limit, offset))(td))
  }

  // Search
  override def searchCount(creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("search.count")(td => ApplicationDatabase.queryF(GenreQueries.searchCount(q, filters))(td))
  }
  override def search(
    creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search")(td => ApplicationDatabase.queryF(GenreQueries.search(q, filters, orderBys, limit, offset))(td))
  }

  def searchExact(
    creds: Credentials, q: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search.exact")(td => ApplicationDatabase.queryF(GenreQueries.searchExact(q, orderBys, limit, offset))(td))
  }

  def countByGenreId(creds: Credentials, genreId: Long)(implicit trace: TraceData) = traceF("count.by.genreId") { td =>
    ApplicationDatabase.queryF(GenreQueries.CountByGenreId(genreId))(td)
  }
  def getByGenreId(creds: Credentials, genreId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.genreId") { td =>
    ApplicationDatabase.queryF(GenreQueries.GetByGenreId(genreId, orderBys, limit, offset))(td)
  }
  def getByGenreIdSeq(creds: Credentials, genreIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.genreId.seq") { td =>
    ApplicationDatabase.queryF(GenreQueries.GetByGenreIdSeq(genreIdSeq))(td)
  }

  def countByName(creds: Credentials, name: String)(implicit trace: TraceData) = traceF("count.by.name") { td =>
    ApplicationDatabase.queryF(GenreQueries.CountByName(name))(td)
  }
  def getByName(creds: Credentials, name: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.name") { td =>
    ApplicationDatabase.queryF(GenreQueries.GetByName(name, orderBys, limit, offset))(td)
  }
  def getByNameSeq(creds: Credentials, nameSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.name.seq") { td =>
    ApplicationDatabase.queryF(GenreQueries.GetByNameSeq(nameSeq))(td)
  }

  // Mutations
  def insert(creds: Credentials, model: Genre)(implicit trace: TraceData) = traceF("insert") { td =>
    ApplicationDatabase.executeF(GenreQueries.insert(model))(td).flatMap {
      case 1 => getByPrimaryKey(creds, model.genreId)(td)
      case _ => throw new IllegalStateException("Unable to find newly-inserted Genre.")
    }
  }
  def insertBatch(creds: Credentials, models: Seq[Genre])(implicit trace: TraceData) = {
    traceF("insertBatch")(td => ApplicationDatabase.executeF(GenreQueries.insertBatch(models))(td))
  }
  def create(creds: Credentials, fields: Seq[DataField])(implicit trace: TraceData) = traceF("create") { td =>
    ApplicationDatabase.executeF(GenreQueries.create(fields))(td).flatMap { _ =>
      getByPrimaryKey(creds, fieldVal(fields, "genreId").toLong)
    }
  }

  def remove(creds: Credentials, genreId: Long)(implicit trace: TraceData) = {
    traceF("remove")(td => getByPrimaryKey(creds, genreId)(td).flatMap {
      case Some(current) =>
        ApplicationDatabase.executeF(GenreQueries.removeByPrimaryKey(genreId))(td).map(_ => current)
      case None => throw new IllegalStateException(s"Cannot find Genre matching [$genreId].")
    })
  }

  def update(creds: Credentials, genreId: Long, fields: Seq[DataField])(implicit trace: TraceData) = {
    traceF("update")(td => getByPrimaryKey(creds, genreId)(td).flatMap {
      case Some(current) if fields.isEmpty => Future.successful(current -> s"No changes required for Genre [$genreId].")
      case Some(_) => ApplicationDatabase.executeF(GenreQueries.update(genreId, fields))(td).flatMap { _ =>
        getByPrimaryKey(creds, genreId)(td).map {
          case Some(newModel) =>
            newModel -> s"Updated [${fields.size}] fields of Genre [$genreId]."
          case None => throw new IllegalStateException(s"Cannot find Genre matching [$genreId].")
        }
      }
      case None => throw new IllegalStateException(s"Cannot find Genre matching [$genreId].")
    })
  }

  def csvFor(totalCount: Int, rows: Seq[Genre])(implicit trace: TraceData) = {
    traceB("export.csv")(td => util.CsvUtils.csvFor(Some(key), totalCount, rows, GenreQueries.fields)(td))
  }
}
