/* Generated File */
package services.media

import models.auth.Credentials
import models.media.Playlist
import models.queries.media.PlaylistQueries
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import scala.concurrent.Future
import services.ModelServiceHelper
import services.database.ApplicationDatabase
import util.FutureUtils.serviceContext
import util.tracing.{TraceData, TracingService}

@javax.inject.Singleton
class PlaylistService @javax.inject.Inject() (override val tracing: TracingService) extends ModelServiceHelper[Playlist]("playlist") {
  def getByPrimaryKey(creds: Credentials, playlistId: Long)(implicit trace: TraceData) = {
    traceF("get.by.primary.key")(td => ApplicationDatabase.queryF(PlaylistQueries.getByPrimaryKey(playlistId))(td))
  }
  def getByPrimaryKeyRequired(creds: Credentials, playlistId: Long)(implicit trace: TraceData) = getByPrimaryKey(creds, playlistId).map { opt =>
    opt.getOrElse(throw new IllegalStateException(s"Cannot load playlist with playlistId [$playlistId]."))
  }
  def getByPrimaryKeySeq(creds: Credentials, playlistIdSeq: Seq[Long])(implicit trace: TraceData) = {
    traceF("get.by.primary.key.seq")(td => ApplicationDatabase.queryF(PlaylistQueries.getByPrimaryKeySeq(playlistIdSeq))(td))
  }

  override def countAll(creds: Credentials, filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("get.all.count")(td => ApplicationDatabase.queryF(PlaylistQueries.countAll(filters))(td))
  }
  override def getAll(creds: Credentials, filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = {
    traceF("get.all")(td => ApplicationDatabase.queryF(PlaylistQueries.getAll(filters, orderBys, limit, offset))(td))
  }

  // Search
  override def searchCount(creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("search.count")(td => ApplicationDatabase.queryF(PlaylistQueries.searchCount(q, filters))(td))
  }
  override def search(
    creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search")(td => ApplicationDatabase.queryF(PlaylistQueries.search(q, filters, orderBys, limit, offset))(td))
  }

  def searchExact(
    creds: Credentials, q: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search.exact")(td => ApplicationDatabase.queryF(PlaylistQueries.searchExact(q, orderBys, limit, offset))(td))
  }

  def countByName(creds: Credentials, name: String)(implicit trace: TraceData) = traceF("count.by.name") { td =>
    ApplicationDatabase.queryF(PlaylistQueries.CountByName(name))(td)
  }
  def getByName(creds: Credentials, name: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.name") { td =>
    ApplicationDatabase.queryF(PlaylistQueries.GetByName(name, orderBys, limit, offset))(td)
  }
  def getByNameSeq(creds: Credentials, nameSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.name.seq") { td =>
    ApplicationDatabase.queryF(PlaylistQueries.GetByNameSeq(nameSeq))(td)
  }

  def countByPlaylistId(creds: Credentials, playlistId: Long)(implicit trace: TraceData) = traceF("count.by.playlistId") { td =>
    ApplicationDatabase.queryF(PlaylistQueries.CountByPlaylistId(playlistId))(td)
  }
  def getByPlaylistId(creds: Credentials, playlistId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.playlistId") { td =>
    ApplicationDatabase.queryF(PlaylistQueries.GetByPlaylistId(playlistId, orderBys, limit, offset))(td)
  }
  def getByPlaylistIdSeq(creds: Credentials, playlistIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.playlistId.seq") { td =>
    ApplicationDatabase.queryF(PlaylistQueries.GetByPlaylistIdSeq(playlistIdSeq))(td)
  }

  // Mutations
  def insert(creds: Credentials, model: Playlist)(implicit trace: TraceData) = traceF("insert") { td =>
    ApplicationDatabase.executeF(PlaylistQueries.insert(model))(td).flatMap {
      case 1 => getByPrimaryKey(creds, model.playlistId)(td)
      case _ => throw new IllegalStateException("Unable to find newly-inserted Playlist.")
    }
  }
  def insertBatch(creds: Credentials, models: Seq[Playlist])(implicit trace: TraceData) = {
    traceF("insertBatch")(td => ApplicationDatabase.executeF(PlaylistQueries.insertBatch(models))(td))
  }
  def create(creds: Credentials, fields: Seq[DataField])(implicit trace: TraceData) = traceF("create") { td =>
    ApplicationDatabase.executeF(PlaylistQueries.create(fields))(td).flatMap { _ =>
      getByPrimaryKey(creds, fieldVal(fields, "playlistId").toLong)
    }
  }

  def remove(creds: Credentials, playlistId: Long)(implicit trace: TraceData) = {
    traceF("remove")(td => getByPrimaryKey(creds, playlistId)(td).flatMap {
      case Some(current) =>
        ApplicationDatabase.executeF(PlaylistQueries.removeByPrimaryKey(playlistId))(td).map(_ => current)
      case None => throw new IllegalStateException(s"Cannot find Playlist matching [$playlistId].")
    })
  }

  def update(creds: Credentials, playlistId: Long, fields: Seq[DataField])(implicit trace: TraceData) = {
    traceF("update")(td => getByPrimaryKey(creds, playlistId)(td).flatMap {
      case Some(current) if fields.isEmpty => Future.successful(current -> s"No changes required for Playlist [$playlistId].")
      case Some(_) => ApplicationDatabase.executeF(PlaylistQueries.update(playlistId, fields))(td).flatMap { _ =>
        getByPrimaryKey(creds, playlistId)(td).map {
          case Some(newModel) =>
            newModel -> s"Updated [${fields.size}] fields of Playlist [$playlistId]."
          case None => throw new IllegalStateException(s"Cannot find Playlist matching [$playlistId].")
        }
      }
      case None => throw new IllegalStateException(s"Cannot find Playlist matching [$playlistId].")
    })
  }

  def csvFor(totalCount: Int, rows: Seq[Playlist])(implicit trace: TraceData) = {
    traceB("export.csv")(td => util.CsvUtils.csvFor(Some(key), totalCount, rows, PlaylistQueries.fields)(td))
  }
}
