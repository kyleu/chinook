/* Generated File */
package services.media

import models.auth.Credentials
import models.media.MediaType
import models.queries.media.MediaTypeQueries
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import scala.concurrent.Future
import services.ModelServiceHelper
import services.database.ApplicationDatabase
import util.FutureUtils.serviceContext
import util.tracing.{TraceData, TracingService}

@javax.inject.Singleton
class MediaTypeService @javax.inject.Inject() (override val tracing: TracingService) extends ModelServiceHelper[MediaType]("mediaType") {
  def getByPrimaryKey(creds: Credentials, mediaTypeId: Long)(implicit trace: TraceData) = {
    traceF("get.by.primary.key")(td => ApplicationDatabase.queryF(MediaTypeQueries.getByPrimaryKey(mediaTypeId))(td))
  }
  def getByPrimaryKeyRequired(creds: Credentials, mediaTypeId: Long)(implicit trace: TraceData) = getByPrimaryKey(creds, mediaTypeId).map { opt =>
    opt.getOrElse(throw new IllegalStateException(s"Cannot load mediaType with mediaTypeId [$mediaTypeId]."))
  }
  def getByPrimaryKeySeq(creds: Credentials, mediaTypeIdSeq: Seq[Long])(implicit trace: TraceData) = {
    traceF("get.by.primary.key.seq")(td => ApplicationDatabase.queryF(MediaTypeQueries.getByPrimaryKeySeq(mediaTypeIdSeq))(td))
  }

  override def countAll(creds: Credentials, filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("get.all.count")(td => ApplicationDatabase.queryF(MediaTypeQueries.countAll(filters))(td))
  }
  override def getAll(creds: Credentials, filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = {
    traceF("get.all")(td => ApplicationDatabase.queryF(MediaTypeQueries.getAll(filters, orderBys, limit, offset))(td))
  }

  // Search
  override def searchCount(creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("search.count")(td => ApplicationDatabase.queryF(MediaTypeQueries.searchCount(q, filters))(td))
  }
  override def search(
    creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search")(td => ApplicationDatabase.queryF(MediaTypeQueries.search(q, filters, orderBys, limit, offset))(td))
  }

  def searchExact(
    creds: Credentials, q: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search.exact")(td => ApplicationDatabase.queryF(MediaTypeQueries.searchExact(q, orderBys, limit, offset))(td))
  }

  def countByMediaTypeId(creds: Credentials, mediaTypeId: Long)(implicit trace: TraceData) = traceF("count.by.mediaTypeId") { td =>
    ApplicationDatabase.queryF(MediaTypeQueries.CountByMediaTypeId(mediaTypeId))(td)
  }
  def getByMediaTypeId(creds: Credentials, mediaTypeId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.mediaTypeId") { td =>
    ApplicationDatabase.queryF(MediaTypeQueries.GetByMediaTypeId(mediaTypeId, orderBys, limit, offset))(td)
  }
  def getByMediaTypeIdSeq(creds: Credentials, mediaTypeIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.mediaTypeId.seq") { td =>
    ApplicationDatabase.queryF(MediaTypeQueries.GetByMediaTypeIdSeq(mediaTypeIdSeq))(td)
  }

  def countByName(creds: Credentials, name: String)(implicit trace: TraceData) = traceF("count.by.name") { td =>
    ApplicationDatabase.queryF(MediaTypeQueries.CountByName(name))(td)
  }
  def getByName(creds: Credentials, name: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.name") { td =>
    ApplicationDatabase.queryF(MediaTypeQueries.GetByName(name, orderBys, limit, offset))(td)
  }
  def getByNameSeq(creds: Credentials, nameSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.name.seq") { td =>
    ApplicationDatabase.queryF(MediaTypeQueries.GetByNameSeq(nameSeq))(td)
  }

  // Mutations
  def insert(creds: Credentials, model: MediaType)(implicit trace: TraceData) = traceF("insert") { td =>
    ApplicationDatabase.executeF(MediaTypeQueries.insert(model))(td).flatMap {
      case 1 => getByPrimaryKey(creds, model.mediaTypeId)(td)
      case _ => throw new IllegalStateException("Unable to find newly-inserted Media Type.")
    }
  }
  def insertBatch(creds: Credentials, models: Seq[MediaType])(implicit trace: TraceData) = {
    traceF("insertBatch")(td => ApplicationDatabase.executeF(MediaTypeQueries.insertBatch(models))(td))
  }
  def create(creds: Credentials, fields: Seq[DataField])(implicit trace: TraceData) = traceF("create") { td =>
    ApplicationDatabase.executeF(MediaTypeQueries.create(fields))(td).flatMap { _ =>
      getByPrimaryKey(creds, fieldVal(fields, "mediaTypeId").toLong)
    }
  }

  def remove(creds: Credentials, mediaTypeId: Long)(implicit trace: TraceData) = {
    traceF("remove")(td => getByPrimaryKey(creds, mediaTypeId)(td).flatMap {
      case Some(current) =>
        ApplicationDatabase.executeF(MediaTypeQueries.removeByPrimaryKey(mediaTypeId))(td).map(_ => current)
      case None => throw new IllegalStateException(s"Cannot find MediaType matching [$mediaTypeId].")
    })
  }

  def update(creds: Credentials, mediaTypeId: Long, fields: Seq[DataField])(implicit trace: TraceData) = {
    traceF("update")(td => getByPrimaryKey(creds, mediaTypeId)(td).flatMap {
      case Some(current) if fields.isEmpty => Future.successful(current -> s"No changes required for Media Type [$mediaTypeId].")
      case Some(_) => ApplicationDatabase.executeF(MediaTypeQueries.update(mediaTypeId, fields))(td).flatMap { _ =>
        getByPrimaryKey(creds, mediaTypeId)(td).map {
          case Some(newModel) =>
            newModel -> s"Updated [${fields.size}] fields of Media Type [$mediaTypeId]."
          case None => throw new IllegalStateException(s"Cannot find MediaType matching [$mediaTypeId].")
        }
      }
      case None => throw new IllegalStateException(s"Cannot find MediaType matching [$mediaTypeId].")
    })
  }

  def csvFor(totalCount: Int, rows: Seq[MediaType])(implicit trace: TraceData) = {
    traceB("export.csv")(td => util.CsvUtils.csvFor(Some(key), totalCount, rows, MediaTypeQueries.fields)(td))
  }
}
