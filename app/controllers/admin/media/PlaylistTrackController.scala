/* Generated File */
package controllers.admin.media

import controllers.admin.ServiceController
import models.Application
import models.media.PlaylistTrackResult
import models.result.orderBy.OrderBy
import play.api.http.MimeTypes
import scala.concurrent.Future
import services.audit.AuditRecordService
import services.media.PlaylistTrackService
import util.JsonSerializers._
import util.ReftreeUtils._

@javax.inject.Singleton
class PlaylistTrackController @javax.inject.Inject() (
    override val app: Application, svc: PlaylistTrackService, auditRecordSvc: AuditRecordService
) extends ServiceController(svc) {
  import app.contexts.webContext

  def createForm = withSession("create.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.PlaylistTrackController.list()
    val call = controllers.admin.media.routes.PlaylistTrackController.create()
    Future.successful(Ok(views.html.admin.media.playlistTrackForm(
      request.identity, models.media.PlaylistTrack.empty(), "New Playlist Track", cancel, call, isNew = true, debug = app.config.debug
    )))
  }

  def create = withSession("create", admin = true) { implicit request => implicit td =>
    svc.create(request, modelForm(request.body)).map {
      case Some(model) => Redirect(controllers.admin.media.routes.PlaylistTrackController.view(model.playlistId, model.trackId))
      case None => Redirect(controllers.admin.media.routes.PlaylistTrackController.list())
    }
  }

  def list(q: Option[String], orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("list", admin = true) { implicit request => implicit td =>
      val startMs = util.DateUtils.nowMillis
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      searchWithCount(q, orderBys, limit, offset).map(r => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.playlistTrackList(
          request.identity, Some(r._1), r._2, q, orderBy, orderAsc, limit.getOrElse(100), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(PlaylistTrackResult.fromRecords(q, Nil, orderBys, limit, offset, startMs, r._1, r._2).asJson)
        case ServiceController.MimeTypes.csv => csvResponse("PlaylistTrack", svc.csvFor(r._1, r._2))
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

  def byTrackId(trackId: Long, orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("get.by.trackId", admin = true) { implicit request => implicit td =>
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      svc.getByTrackId(request, trackId, orderBys, limit, offset).map(models => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.playlistTrackByTrackId(
          request.identity, trackId, models, orderBy, orderAsc, limit.getOrElse(5), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(models.asJson)
        case ServiceController.MimeTypes.csv => csvResponse("PlaylistTrack by trackId", svc.csvFor(0, models))
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = models)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = models)).as(ServiceController.MimeTypes.svg)
      })
    }
  }

  def byPlaylistId(playlistId: Long, orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("get.by.playlistId", admin = true) { implicit request => implicit td =>
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      svc.getByPlaylistId(request, playlistId, orderBys, limit, offset).map(models => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.playlistTrackByPlaylistId(
          request.identity, playlistId, models, orderBy, orderAsc, limit.getOrElse(5), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(models.asJson)
        case ServiceController.MimeTypes.csv => csvResponse("PlaylistTrack by playlistId", svc.csvFor(0, models))
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = models)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = models)).as(ServiceController.MimeTypes.svg)
      })
    }
  }

  def view(playlistId: Long, trackId: Long, t: Option[String] = None) = withSession("view", admin = true) { implicit request => implicit td =>
    val modelF = svc.getByPrimaryKey(request, playlistId, trackId)
    val notesF = app.coreServices.notes.getFor(request, "playlistTrack", playlistId, trackId)

    notesF.flatMap(notes => modelF.map {
      case Some(model) => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.playlistTrackView(request.identity, model, notes, app.config.debug))
        case MimeTypes.JSON => Ok(model.asJson)
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = model)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = model)).as(ServiceController.MimeTypes.svg)
      }
      case None => NotFound(s"No PlaylistTrack found with playlistId, trackId [$playlistId, $trackId].")
    })
  }

  def editForm(playlistId: Long, trackId: Long) = withSession("edit.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.PlaylistTrackController.view(playlistId, trackId)
    val call = controllers.admin.media.routes.PlaylistTrackController.edit(playlistId, trackId)
    svc.getByPrimaryKey(request, playlistId, trackId).map {
      case Some(model) => Ok(
        views.html.admin.media.playlistTrackForm(request.identity, model, s"Playlist Track [$playlistId, $trackId]", cancel, call, debug = app.config.debug)
      )
      case None => NotFound(s"No PlaylistTrack found with playlistId, trackId [$playlistId, $trackId].")
    }
  }

  def edit(playlistId: Long, trackId: Long) = withSession("edit", admin = true) { implicit request => implicit td =>
    svc.update(request, playlistId = playlistId, trackId = trackId, fields = modelForm(request.body)).map(res => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.PlaylistTrackController.view(res._1.playlistId, res._1.trackId)).flashing("success" -> res._2)
      case Accepts.Json() => Ok(res.asJson)
    })
  }

  def remove(playlistId: Long, trackId: Long) = withSession("remove", admin = true) { implicit request => implicit td =>
    svc.remove(request, playlistId = playlistId, trackId = trackId).map(_ => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.PlaylistTrackController.list())
      case Accepts.Json() => Ok(io.circe.Json.obj("status" -> io.circe.Json.fromString("removed")))
    })
  }
}
