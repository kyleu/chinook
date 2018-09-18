/* Generated File */
package services.media

import models.auth.Credentials
import models.media.Artist
import models.queries.media.ArtistQueries
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import scala.concurrent.Future
import services.ModelServiceHelper
import services.database.ApplicationDatabase
import util.FutureUtils.serviceContext
import util.tracing.{TraceData, TracingService}

@javax.inject.Singleton
class ArtistService @javax.inject.Inject() (override val tracing: TracingService) extends ModelServiceHelper[Artist]("artist") {
  def getByPrimaryKey(creds: Credentials, artistId: Long)(implicit trace: TraceData) = {
    traceF("get.by.primary.key")(td => ApplicationDatabase.queryF(ArtistQueries.getByPrimaryKey(artistId))(td))
  }
  def getByPrimaryKeyRequired(creds: Credentials, artistId: Long)(implicit trace: TraceData) = getByPrimaryKey(creds, artistId).map { opt =>
    opt.getOrElse(throw new IllegalStateException(s"Cannot load artist with artistId [$artistId]."))
  }
  def getByPrimaryKeySeq(creds: Credentials, artistIdSeq: Seq[Long])(implicit trace: TraceData) = {
    traceF("get.by.primary.key.seq")(td => ApplicationDatabase.queryF(ArtistQueries.getByPrimaryKeySeq(artistIdSeq))(td))
  }

  override def countAll(creds: Credentials, filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("get.all.count")(td => ApplicationDatabase.queryF(ArtistQueries.countAll(filters))(td))
  }
  override def getAll(creds: Credentials, filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = {
    traceF("get.all")(td => ApplicationDatabase.queryF(ArtistQueries.getAll(filters, orderBys, limit, offset))(td))
  }

  // Search
  override def searchCount(creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("search.count")(td => ApplicationDatabase.queryF(ArtistQueries.searchCount(q, filters))(td))
  }
  override def search(
    creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search")(td => ApplicationDatabase.queryF(ArtistQueries.search(q, filters, orderBys, limit, offset))(td))
  }

  def searchExact(
    creds: Credentials, q: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search.exact")(td => ApplicationDatabase.queryF(ArtistQueries.searchExact(q, orderBys, limit, offset))(td))
  }

  def countByArtistId(creds: Credentials, artistId: Long)(implicit trace: TraceData) = traceF("count.by.artistId") { td =>
    ApplicationDatabase.queryF(ArtistQueries.CountByArtistId(artistId))(td)
  }
  def getByArtistId(creds: Credentials, artistId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.artistId") { td =>
    ApplicationDatabase.queryF(ArtistQueries.GetByArtistId(artistId, orderBys, limit, offset))(td)
  }
  def getByArtistIdSeq(creds: Credentials, artistIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.artistId.seq") { td =>
    ApplicationDatabase.queryF(ArtistQueries.GetByArtistIdSeq(artistIdSeq))(td)
  }

  def countByName(creds: Credentials, name: String)(implicit trace: TraceData) = traceF("count.by.name") { td =>
    ApplicationDatabase.queryF(ArtistQueries.CountByName(name))(td)
  }
  def getByName(creds: Credentials, name: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.name") { td =>
    ApplicationDatabase.queryF(ArtistQueries.GetByName(name, orderBys, limit, offset))(td)
  }
  def getByNameSeq(creds: Credentials, nameSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.name.seq") { td =>
    ApplicationDatabase.queryF(ArtistQueries.GetByNameSeq(nameSeq))(td)
  }

  // Mutations
  def insert(creds: Credentials, model: Artist)(implicit trace: TraceData) = traceF("insert") { td =>
    ApplicationDatabase.executeF(ArtistQueries.insert(model))(td).flatMap {
      case 1 => getByPrimaryKey(creds, model.artistId)(td)
      case _ => throw new IllegalStateException("Unable to find newly-inserted Artist.")
    }
  }
  def insertBatch(creds: Credentials, models: Seq[Artist])(implicit trace: TraceData) = {
    traceF("insertBatch")(td => ApplicationDatabase.executeF(ArtistQueries.insertBatch(models))(td))
  }
  def create(creds: Credentials, fields: Seq[DataField])(implicit trace: TraceData) = traceF("create") { td =>
    ApplicationDatabase.executeF(ArtistQueries.create(fields))(td).flatMap { _ =>
      getByPrimaryKey(creds, fieldVal(fields, "artistId").toLong)
    }
  }

  def remove(creds: Credentials, artistId: Long)(implicit trace: TraceData) = {
    traceF("remove")(td => getByPrimaryKey(creds, artistId)(td).flatMap {
      case Some(current) =>
        ApplicationDatabase.executeF(ArtistQueries.removeByPrimaryKey(artistId))(td).map(_ => current)
      case None => throw new IllegalStateException(s"Cannot find Artist matching [$artistId].")
    })
  }

  def update(creds: Credentials, artistId: Long, fields: Seq[DataField])(implicit trace: TraceData) = {
    traceF("update")(td => getByPrimaryKey(creds, artistId)(td).flatMap {
      case Some(current) if fields.isEmpty => Future.successful(current -> s"No changes required for Artist [$artistId].")
      case Some(_) => ApplicationDatabase.executeF(ArtistQueries.update(artistId, fields))(td).flatMap { _ =>
        getByPrimaryKey(creds, artistId)(td).map {
          case Some(newModel) =>
            newModel -> s"Updated [${fields.size}] fields of Artist [$artistId]."
          case None => throw new IllegalStateException(s"Cannot find Artist matching [$artistId].")
        }
      }
      case None => throw new IllegalStateException(s"Cannot find Artist matching [$artistId].")
    })
  }

  def csvFor(totalCount: Int, rows: Seq[Artist])(implicit trace: TraceData) = {
    traceB("export.csv")(td => util.CsvUtils.csvFor(Some(key), totalCount, rows, ArtistQueries.fields)(td))
  }
}
