/* Generated File */
package services.media

import models.auth.Credentials
import models.media.Album
import models.queries.media.AlbumQueries
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import scala.concurrent.Future
import services.ModelServiceHelper
import services.database.ApplicationDatabase
import util.FutureUtils.serviceContext
import util.tracing.{TraceData, TracingService}

@javax.inject.Singleton
class AlbumService @javax.inject.Inject() (override val tracing: TracingService) extends ModelServiceHelper[Album]("album") {
  def getByPrimaryKey(creds: Credentials, albumId: Long)(implicit trace: TraceData) = {
    traceF("get.by.primary.key")(td => ApplicationDatabase.queryF(AlbumQueries.getByPrimaryKey(albumId))(td))
  }
  def getByPrimaryKeyRequired(creds: Credentials, albumId: Long)(implicit trace: TraceData) = getByPrimaryKey(creds, albumId).map { opt =>
    opt.getOrElse(throw new IllegalStateException(s"Cannot load album with albumId [$albumId]."))
  }
  def getByPrimaryKeySeq(creds: Credentials, albumIdSeq: Seq[Long])(implicit trace: TraceData) = {
    traceF("get.by.primary.key.seq")(td => ApplicationDatabase.queryF(AlbumQueries.getByPrimaryKeySeq(albumIdSeq))(td))
  }

  override def countAll(creds: Credentials, filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("get.all.count")(td => ApplicationDatabase.queryF(AlbumQueries.countAll(filters))(td))
  }
  override def getAll(creds: Credentials, filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = {
    traceF("get.all")(td => ApplicationDatabase.queryF(AlbumQueries.getAll(filters, orderBys, limit, offset))(td))
  }

  // Search
  override def searchCount(creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("search.count")(td => ApplicationDatabase.queryF(AlbumQueries.searchCount(q, filters))(td))
  }
  override def search(
    creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search")(td => ApplicationDatabase.queryF(AlbumQueries.search(q, filters, orderBys, limit, offset))(td))
  }

  def searchExact(
    creds: Credentials, q: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search.exact")(td => ApplicationDatabase.queryF(AlbumQueries.searchExact(q, orderBys, limit, offset))(td))
  }

  def countByAlbumId(creds: Credentials, albumId: Long)(implicit trace: TraceData) = traceF("count.by.albumId") { td =>
    ApplicationDatabase.queryF(AlbumQueries.CountByAlbumId(albumId))(td)
  }
  def getByAlbumId(creds: Credentials, albumId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.albumId") { td =>
    ApplicationDatabase.queryF(AlbumQueries.GetByAlbumId(albumId, orderBys, limit, offset))(td)
  }
  def getByAlbumIdSeq(creds: Credentials, albumIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.albumId.seq") { td =>
    ApplicationDatabase.queryF(AlbumQueries.GetByAlbumIdSeq(albumIdSeq))(td)
  }

  def countByArtistId(creds: Credentials, artistId: Long)(implicit trace: TraceData) = traceF("count.by.artistId") { td =>
    ApplicationDatabase.queryF(AlbumQueries.CountByArtistId(artistId))(td)
  }
  def getByArtistId(creds: Credentials, artistId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.artistId") { td =>
    ApplicationDatabase.queryF(AlbumQueries.GetByArtistId(artistId, orderBys, limit, offset))(td)
  }
  def getByArtistIdSeq(creds: Credentials, artistIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.artistId.seq") { td =>
    ApplicationDatabase.queryF(AlbumQueries.GetByArtistIdSeq(artistIdSeq))(td)
  }

  def countByTitle(creds: Credentials, title: String)(implicit trace: TraceData) = traceF("count.by.title") { td =>
    ApplicationDatabase.queryF(AlbumQueries.CountByTitle(title))(td)
  }
  def getByTitle(creds: Credentials, title: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.title") { td =>
    ApplicationDatabase.queryF(AlbumQueries.GetByTitle(title, orderBys, limit, offset))(td)
  }
  def getByTitleSeq(creds: Credentials, titleSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.title.seq") { td =>
    ApplicationDatabase.queryF(AlbumQueries.GetByTitleSeq(titleSeq))(td)
  }

  // Mutations
  def insert(creds: Credentials, model: Album)(implicit trace: TraceData) = traceF("insert") { td =>
    ApplicationDatabase.executeF(AlbumQueries.insert(model))(td).flatMap {
      case 1 => getByPrimaryKey(creds, model.albumId)(td)
      case _ => throw new IllegalStateException("Unable to find newly-inserted Album.")
    }
  }
  def insertBatch(creds: Credentials, models: Seq[Album])(implicit trace: TraceData) = {
    traceF("insertBatch")(td => ApplicationDatabase.executeF(AlbumQueries.insertBatch(models))(td))
  }
  def create(creds: Credentials, fields: Seq[DataField])(implicit trace: TraceData) = traceF("create") { td =>
    ApplicationDatabase.executeF(AlbumQueries.create(fields))(td).flatMap { _ =>
      getByPrimaryKey(creds, fieldVal(fields, "albumId").toLong)
    }
  }

  def remove(creds: Credentials, albumId: Long)(implicit trace: TraceData) = {
    traceF("remove")(td => getByPrimaryKey(creds, albumId)(td).flatMap {
      case Some(current) =>
        ApplicationDatabase.executeF(AlbumQueries.removeByPrimaryKey(albumId))(td).map(_ => current)
      case None => throw new IllegalStateException(s"Cannot find Album matching [$albumId].")
    })
  }

  def update(creds: Credentials, albumId: Long, fields: Seq[DataField])(implicit trace: TraceData) = {
    traceF("update")(td => getByPrimaryKey(creds, albumId)(td).flatMap {
      case Some(current) if fields.isEmpty => Future.successful(current -> s"No changes required for Album [$albumId].")
      case Some(_) => ApplicationDatabase.executeF(AlbumQueries.update(albumId, fields))(td).flatMap { _ =>
        getByPrimaryKey(creds, albumId)(td).map {
          case Some(newModel) =>
            newModel -> s"Updated [${fields.size}] fields of Album [$albumId]."
          case None => throw new IllegalStateException(s"Cannot find Album matching [$albumId].")
        }
      }
      case None => throw new IllegalStateException(s"Cannot find Album matching [$albumId].")
    })
  }

  def csvFor(totalCount: Int, rows: Seq[Album])(implicit trace: TraceData) = {
    traceB("export.csv")(td => util.CsvUtils.csvFor(Some(key), totalCount, rows, AlbumQueries.fields)(td))
  }
}
