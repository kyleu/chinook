/* Generated File */
package services.media

import models.auth.Credentials
import models.media.PlaylistTrack
import models.queries.media.PlaylistTrackQueries
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import scala.concurrent.Future
import services.ModelServiceHelper
import services.database.ApplicationDatabase
import util.FutureUtils.serviceContext
import util.tracing.{TraceData, TracingService}

@javax.inject.Singleton
class PlaylistTrackService @javax.inject.Inject() (override val tracing: TracingService) extends ModelServiceHelper[PlaylistTrack]("playlistTrack") {
  def getByPrimaryKey(creds: Credentials, playlistId: Long, trackId: Long)(implicit trace: TraceData) = {
    traceF("get.by.primary.key")(td => ApplicationDatabase.queryF(PlaylistTrackQueries.getByPrimaryKey(playlistId, trackId))(td))
  }
  def getByPrimaryKeySeq(creds: Credentials, pkSeq: Seq[(Long, Long)])(implicit trace: TraceData) = {
    traceF("get.by.primary.key.seq")(td => ApplicationDatabase.queryF(PlaylistTrackQueries.getByPrimaryKeySeq(pkSeq))(td))
  }

  override def countAll(creds: Credentials, filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("get.all.count")(td => ApplicationDatabase.queryF(PlaylistTrackQueries.countAll(filters))(td))
  }
  override def getAll(creds: Credentials, filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = {
    traceF("get.all")(td => ApplicationDatabase.queryF(PlaylistTrackQueries.getAll(filters, orderBys, limit, offset))(td))
  }

  // Search
  override def searchCount(creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("search.count")(td => ApplicationDatabase.queryF(PlaylistTrackQueries.searchCount(q, filters))(td))
  }
  override def search(
    creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search")(td => ApplicationDatabase.queryF(PlaylistTrackQueries.search(q, filters, orderBys, limit, offset))(td))
  }

  def searchExact(
    creds: Credentials, q: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search.exact")(td => ApplicationDatabase.queryF(PlaylistTrackQueries.searchExact(q, orderBys, limit, offset))(td))
  }

  def countByPlaylistId(creds: Credentials, playlistId: Long)(implicit trace: TraceData) = traceF("count.by.playlistId") { td =>
    ApplicationDatabase.queryF(PlaylistTrackQueries.CountByPlaylistId(playlistId))(td)
  }
  def getByPlaylistId(creds: Credentials, playlistId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.playlistId") { td =>
    ApplicationDatabase.queryF(PlaylistTrackQueries.GetByPlaylistId(playlistId, orderBys, limit, offset))(td)
  }
  def getByPlaylistIdSeq(creds: Credentials, playlistIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.playlistId.seq") { td =>
    ApplicationDatabase.queryF(PlaylistTrackQueries.GetByPlaylistIdSeq(playlistIdSeq))(td)
  }

  def countByTrackId(creds: Credentials, trackId: Long)(implicit trace: TraceData) = traceF("count.by.trackId") { td =>
    ApplicationDatabase.queryF(PlaylistTrackQueries.CountByTrackId(trackId))(td)
  }
  def getByTrackId(creds: Credentials, trackId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.trackId") { td =>
    ApplicationDatabase.queryF(PlaylistTrackQueries.GetByTrackId(trackId, orderBys, limit, offset))(td)
  }
  def getByTrackIdSeq(creds: Credentials, trackIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.trackId.seq") { td =>
    ApplicationDatabase.queryF(PlaylistTrackQueries.GetByTrackIdSeq(trackIdSeq))(td)
  }

  // Mutations
  def insert(creds: Credentials, model: PlaylistTrack)(implicit trace: TraceData) = traceF("insert") { td =>
    ApplicationDatabase.executeF(PlaylistTrackQueries.insert(model))(td).flatMap {
      case 1 => getByPrimaryKey(creds, model.playlistId, model.trackId)(td)
      case _ => throw new IllegalStateException("Unable to find newly-inserted Playlist Track.")
    }
  }
  def insertBatch(creds: Credentials, models: Seq[PlaylistTrack])(implicit trace: TraceData) = {
    traceF("insertBatch")(td => ApplicationDatabase.executeF(PlaylistTrackQueries.insertBatch(models))(td))
  }
  def create(creds: Credentials, fields: Seq[DataField])(implicit trace: TraceData) = traceF("create") { td =>
    ApplicationDatabase.executeF(PlaylistTrackQueries.create(fields))(td).flatMap { _ =>
      getByPrimaryKey(creds, fieldVal(fields, "playlistId").toLong, fieldVal(fields, "trackId").toLong)
    }
  }

  def remove(creds: Credentials, playlistId: Long, trackId: Long)(implicit trace: TraceData) = {
    traceF("remove")(td => getByPrimaryKey(creds, playlistId, trackId)(td).flatMap {
      case Some(current) =>
        ApplicationDatabase.executeF(PlaylistTrackQueries.removeByPrimaryKey(playlistId, trackId))(td).map(_ => current)
      case None => throw new IllegalStateException(s"Cannot find PlaylistTrack matching [$playlistId, $trackId].")
    })
  }

  def update(creds: Credentials, playlistId: Long, trackId: Long, fields: Seq[DataField])(implicit trace: TraceData) = {
    traceF("update")(td => getByPrimaryKey(creds, playlistId, trackId)(td).flatMap {
      case Some(current) if fields.isEmpty => Future.successful(current -> s"No changes required for Playlist Track [$playlistId, $trackId].")
      case Some(_) => ApplicationDatabase.executeF(PlaylistTrackQueries.update(playlistId, trackId, fields))(td).flatMap { _ =>
        getByPrimaryKey(creds, playlistId, trackId)(td).map {
          case Some(newModel) =>
            newModel -> s"Updated [${fields.size}] fields of Playlist Track [$playlistId, $trackId]."
          case None => throw new IllegalStateException(s"Cannot find PlaylistTrack matching [$playlistId, $trackId].")
        }
      }
      case None => throw new IllegalStateException(s"Cannot find PlaylistTrack matching [$playlistId, $trackId].")
    })
  }

  def csvFor(totalCount: Int, rows: Seq[PlaylistTrack])(implicit trace: TraceData) = {
    traceB("export.csv")(td => util.CsvUtils.csvFor(Some(key), totalCount, rows, PlaylistTrackQueries.fields)(td))
  }
}
