/* Generated File */
package services.media

import models.auth.Credentials
import models.media.Track
import models.queries.media.TrackQueries
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import scala.concurrent.Future
import services.ModelServiceHelper
import services.database.ApplicationDatabase
import util.FutureUtils.serviceContext
import util.tracing.{TraceData, TracingService}

@javax.inject.Singleton
class TrackService @javax.inject.Inject() (override val tracing: TracingService) extends ModelServiceHelper[Track]("track") {
  def getByPrimaryKey(creds: Credentials, trackId: Long)(implicit trace: TraceData) = {
    traceF("get.by.primary.key")(td => ApplicationDatabase.queryF(TrackQueries.getByPrimaryKey(trackId))(td))
  }
  def getByPrimaryKeyRequired(creds: Credentials, trackId: Long)(implicit trace: TraceData) = getByPrimaryKey(creds, trackId).map { opt =>
    opt.getOrElse(throw new IllegalStateException(s"Cannot load track with trackId [$trackId]."))
  }
  def getByPrimaryKeySeq(creds: Credentials, trackIdSeq: Seq[Long])(implicit trace: TraceData) = {
    traceF("get.by.primary.key.seq")(td => ApplicationDatabase.queryF(TrackQueries.getByPrimaryKeySeq(trackIdSeq))(td))
  }

  override def countAll(creds: Credentials, filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("get.all.count")(td => ApplicationDatabase.queryF(TrackQueries.countAll(filters))(td))
  }
  override def getAll(creds: Credentials, filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = {
    traceF("get.all")(td => ApplicationDatabase.queryF(TrackQueries.getAll(filters, orderBys, limit, offset))(td))
  }

  // Search
  override def searchCount(creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("search.count")(td => ApplicationDatabase.queryF(TrackQueries.searchCount(q, filters))(td))
  }
  override def search(
    creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search")(td => ApplicationDatabase.queryF(TrackQueries.search(q, filters, orderBys, limit, offset))(td))
  }

  def searchExact(
    creds: Credentials, q: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search.exact")(td => ApplicationDatabase.queryF(TrackQueries.searchExact(q, orderBys, limit, offset))(td))
  }

  def countByAlbumId(creds: Credentials, albumId: Long)(implicit trace: TraceData) = traceF("count.by.albumId") { td =>
    ApplicationDatabase.queryF(TrackQueries.CountByAlbumId(albumId))(td)
  }
  def getByAlbumId(creds: Credentials, albumId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.albumId") { td =>
    ApplicationDatabase.queryF(TrackQueries.GetByAlbumId(albumId, orderBys, limit, offset))(td)
  }
  def getByAlbumIdSeq(creds: Credentials, albumIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.albumId.seq") { td =>
    ApplicationDatabase.queryF(TrackQueries.GetByAlbumIdSeq(albumIdSeq))(td)
  }

  def countByGenreId(creds: Credentials, genreId: Long)(implicit trace: TraceData) = traceF("count.by.genreId") { td =>
    ApplicationDatabase.queryF(TrackQueries.CountByGenreId(genreId))(td)
  }
  def getByGenreId(creds: Credentials, genreId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.genreId") { td =>
    ApplicationDatabase.queryF(TrackQueries.GetByGenreId(genreId, orderBys, limit, offset))(td)
  }
  def getByGenreIdSeq(creds: Credentials, genreIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.genreId.seq") { td =>
    ApplicationDatabase.queryF(TrackQueries.GetByGenreIdSeq(genreIdSeq))(td)
  }

  def countByMediaTypeId(creds: Credentials, mediaTypeId: Long)(implicit trace: TraceData) = traceF("count.by.mediaTypeId") { td =>
    ApplicationDatabase.queryF(TrackQueries.CountByMediaTypeId(mediaTypeId))(td)
  }
  def getByMediaTypeId(creds: Credentials, mediaTypeId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.mediaTypeId") { td =>
    ApplicationDatabase.queryF(TrackQueries.GetByMediaTypeId(mediaTypeId, orderBys, limit, offset))(td)
  }
  def getByMediaTypeIdSeq(creds: Credentials, mediaTypeIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.mediaTypeId.seq") { td =>
    ApplicationDatabase.queryF(TrackQueries.GetByMediaTypeIdSeq(mediaTypeIdSeq))(td)
  }

  def countByName(creds: Credentials, name: String)(implicit trace: TraceData) = traceF("count.by.name") { td =>
    ApplicationDatabase.queryF(TrackQueries.CountByName(name))(td)
  }
  def getByName(creds: Credentials, name: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.name") { td =>
    ApplicationDatabase.queryF(TrackQueries.GetByName(name, orderBys, limit, offset))(td)
  }
  def getByNameSeq(creds: Credentials, nameSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.name.seq") { td =>
    ApplicationDatabase.queryF(TrackQueries.GetByNameSeq(nameSeq))(td)
  }

  def countByTrackId(creds: Credentials, trackId: Long)(implicit trace: TraceData) = traceF("count.by.trackId") { td =>
    ApplicationDatabase.queryF(TrackQueries.CountByTrackId(trackId))(td)
  }
  def getByTrackId(creds: Credentials, trackId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.trackId") { td =>
    ApplicationDatabase.queryF(TrackQueries.GetByTrackId(trackId, orderBys, limit, offset))(td)
  }
  def getByTrackIdSeq(creds: Credentials, trackIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.trackId.seq") { td =>
    ApplicationDatabase.queryF(TrackQueries.GetByTrackIdSeq(trackIdSeq))(td)
  }

  // Mutations
  def insert(creds: Credentials, model: Track)(implicit trace: TraceData) = traceF("insert") { td =>
    ApplicationDatabase.executeF(TrackQueries.insert(model))(td).flatMap {
      case 1 => getByPrimaryKey(creds, model.trackId)(td)
      case _ => throw new IllegalStateException("Unable to find newly-inserted Track.")
    }
  }
  def insertBatch(creds: Credentials, models: Seq[Track])(implicit trace: TraceData) = {
    traceF("insertBatch")(td => ApplicationDatabase.executeF(TrackQueries.insertBatch(models))(td))
  }
  def create(creds: Credentials, fields: Seq[DataField])(implicit trace: TraceData) = traceF("create") { td =>
    ApplicationDatabase.executeF(TrackQueries.create(fields))(td).flatMap { _ =>
      getByPrimaryKey(creds, fieldVal(fields, "trackId").toLong)
    }
  }

  def remove(creds: Credentials, trackId: Long)(implicit trace: TraceData) = {
    traceF("remove")(td => getByPrimaryKey(creds, trackId)(td).flatMap {
      case Some(current) =>
        ApplicationDatabase.executeF(TrackQueries.removeByPrimaryKey(trackId))(td).map(_ => current)
      case None => throw new IllegalStateException(s"Cannot find Track matching [$trackId].")
    })
  }

  def update(creds: Credentials, trackId: Long, fields: Seq[DataField])(implicit trace: TraceData) = {
    traceF("update")(td => getByPrimaryKey(creds, trackId)(td).flatMap {
      case Some(current) if fields.isEmpty => Future.successful(current -> s"No changes required for Track [$trackId].")
      case Some(_) => ApplicationDatabase.executeF(TrackQueries.update(trackId, fields))(td).flatMap { _ =>
        getByPrimaryKey(creds, trackId)(td).map {
          case Some(newModel) =>
            newModel -> s"Updated [${fields.size}] fields of Track [$trackId]."
          case None => throw new IllegalStateException(s"Cannot find Track matching [$trackId].")
        }
      }
      case None => throw new IllegalStateException(s"Cannot find Track matching [$trackId].")
    })
  }

  def csvFor(totalCount: Int, rows: Seq[Track])(implicit trace: TraceData) = {
    traceB("export.csv")(td => util.CsvUtils.csvFor(Some(key), totalCount, rows, TrackQueries.fields)(td))
  }
}
