/* Generated File */
package controllers.admin.media

import controllers.admin.ServiceController
import models.Application
import models.media.AlbumResult
import models.result.RelationCount
import models.result.orderBy.OrderBy
import play.api.http.MimeTypes
import scala.concurrent.Future
import services.audit.AuditRecordService
import services.media.{AlbumService, TrackService}
import util.JsonSerializers._
import util.ReftreeUtils._

@javax.inject.Singleton
class AlbumController @javax.inject.Inject() (
    override val app: Application, svc: AlbumService, auditRecordSvc: AuditRecordService,
    trackS: TrackService
) extends ServiceController(svc) {
  import app.contexts.webContext

  def createForm = withSession("create.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.AlbumController.list()
    val call = controllers.admin.media.routes.AlbumController.create()
    Future.successful(Ok(views.html.admin.media.albumForm(
      request.identity, models.media.Album.empty(), "New Album", cancel, call, isNew = true, debug = app.config.debug
    )))
  }

  def create = withSession("create", admin = true) { implicit request => implicit td =>
    svc.create(request, modelForm(request.body)).map {
      case Some(model) => Redirect(controllers.admin.media.routes.AlbumController.view(model.albumId))
      case None => Redirect(controllers.admin.media.routes.AlbumController.list())
    }
  }

  def list(q: Option[String], orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("list", admin = true) { implicit request => implicit td =>
      val startMs = util.DateUtils.nowMillis
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      searchWithCount(q, orderBys, limit, offset).map(r => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.albumList(
          request.identity, Some(r._1), r._2, q, orderBy, orderAsc, limit.getOrElse(100), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(AlbumResult.fromRecords(q, Nil, orderBys, limit, offset, startMs, r._1, r._2).asJson)
        case ServiceController.MimeTypes.csv => csvResponse("Album", svc.csvFor(r._1, r._2))
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = r._2)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = r._2)).as(ServiceController.MimeTypes.svg)
      })
    }
  }

  def autocomplete(q: Option[String], orderBy: Option[String], orderAsc: Boolean, limit: Option[Int]) = {
    withSession("autocomplete", admin = true) { implicit request => implicit td =>
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      search(q, orderBys, limit, None).map(r => Ok(r.map(_.toSummary).asJson))
    }
  }

  def byArtistId(artistId: Long, orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("get.by.artistId", admin = true) { implicit request => implicit td =>
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      svc.getByArtistId(request, artistId, orderBys, limit, offset).map(models => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.albumByArtistId(
          request.identity, artistId, models, orderBy, orderAsc, limit.getOrElse(5), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(models.asJson)
        case ServiceController.MimeTypes.csv => csvResponse("Album by artistId", svc.csvFor(0, models))
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = models)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = models)).as(ServiceController.MimeTypes.svg)
      })
    }
  }

  def view(albumId: Long, t: Option[String] = None) = withSession("view", admin = true) { implicit request => implicit td =>
    val modelF = svc.getByPrimaryKey(request, albumId)
    val notesF = app.coreServices.notes.getFor(request, "album", albumId)

    notesF.flatMap(notes => modelF.map {
      case Some(model) => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.albumView(request.identity, model, notes, app.config.debug))
        case MimeTypes.JSON => Ok(model.asJson)
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = model)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = model)).as(ServiceController.MimeTypes.svg)
      }
      case None => NotFound(s"No Album found with albumId [$albumId].")
    })
  }

  def editForm(albumId: Long) = withSession("edit.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.AlbumController.view(albumId)
    val call = controllers.admin.media.routes.AlbumController.edit(albumId)
    svc.getByPrimaryKey(request, albumId).map {
      case Some(model) => Ok(
        views.html.admin.media.albumForm(request.identity, model, s"Album [$albumId]", cancel, call, debug = app.config.debug)
      )
      case None => NotFound(s"No Album found with albumId [$albumId].")
    }
  }

  def edit(albumId: Long) = withSession("edit", admin = true) { implicit request => implicit td =>
    svc.update(request, albumId = albumId, fields = modelForm(request.body)).map(res => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.AlbumController.view(res._1.albumId)).flashing("success" -> res._2)
      case Accepts.Json() => Ok(res.asJson)
    })
  }

  def remove(albumId: Long) = withSession("remove", admin = true) { implicit request => implicit td =>
    svc.remove(request, albumId = albumId).map(_ => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.AlbumController.list())
      case Accepts.Json() => Ok(io.circe.Json.obj("status" -> io.circe.Json.fromString("removed")))
    })
  }

  def relationCounts(albumId: Long) = withSession("relation.counts", admin = true) { implicit request => implicit td =>
    val creds = models.auth.Credentials.fromRequest(request)
    val trackByAlbumIdF = trackS.countByAlbumId(creds, albumId)
    for (trackC <- trackByAlbumIdF) yield {
      Ok(Seq(
        RelationCount(model = "track", field = "albumId", count = trackC)
      ).asJson)
    }
  }
}
