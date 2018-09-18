/* Generated File */
package controllers.admin.media

import controllers.admin.ServiceController
import models.Application
import models.media.PlaylistResult
import models.result.RelationCount
import models.result.orderBy.OrderBy
import play.api.http.MimeTypes
import scala.concurrent.Future
import services.audit.AuditRecordService
import services.media.{PlaylistService, PlaylistTrackService}
import util.JsonSerializers._
import util.ReftreeUtils._

@javax.inject.Singleton
class PlaylistController @javax.inject.Inject() (
    override val app: Application, svc: PlaylistService, auditRecordSvc: AuditRecordService,
    playlistTrackS: PlaylistTrackService
) extends ServiceController(svc) {
  import app.contexts.webContext

  def createForm = withSession("create.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.PlaylistController.list()
    val call = controllers.admin.media.routes.PlaylistController.create()
    Future.successful(Ok(views.html.admin.media.playlistForm(
      request.identity, models.media.Playlist.empty(), "New Playlist", cancel, call, isNew = true, debug = app.config.debug
    )))
  }

  def create = withSession("create", admin = true) { implicit request => implicit td =>
    svc.create(request, modelForm(request.body)).map {
      case Some(model) => Redirect(controllers.admin.media.routes.PlaylistController.view(model.playlistId))
      case None => Redirect(controllers.admin.media.routes.PlaylistController.list())
    }
  }

  def list(q: Option[String], orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("list", admin = true) { implicit request => implicit td =>
      val startMs = util.DateUtils.nowMillis
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      searchWithCount(q, orderBys, limit, offset).map(r => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.playlistList(
          request.identity, Some(r._1), r._2, q, orderBy, orderAsc, limit.getOrElse(100), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(PlaylistResult.fromRecords(q, Nil, orderBys, limit, offset, startMs, r._1, r._2).asJson)
        case ServiceController.MimeTypes.csv => csvResponse("Playlist", svc.csvFor(r._1, r._2))
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

  def view(playlistId: Long, t: Option[String] = None) = withSession("view", admin = true) { implicit request => implicit td =>
    val modelF = svc.getByPrimaryKey(request, playlistId)
    val notesF = app.coreServices.notes.getFor(request, "playlist", playlistId)

    notesF.flatMap(notes => modelF.map {
      case Some(model) => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.playlistView(request.identity, model, notes, app.config.debug))
        case MimeTypes.JSON => Ok(model.asJson)
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = model)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = model)).as(ServiceController.MimeTypes.svg)
      }
      case None => NotFound(s"No Playlist found with playlistId [$playlistId].")
    })
  }

  def editForm(playlistId: Long) = withSession("edit.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.PlaylistController.view(playlistId)
    val call = controllers.admin.media.routes.PlaylistController.edit(playlistId)
    svc.getByPrimaryKey(request, playlistId).map {
      case Some(model) => Ok(
        views.html.admin.media.playlistForm(request.identity, model, s"Playlist [$playlistId]", cancel, call, debug = app.config.debug)
      )
      case None => NotFound(s"No Playlist found with playlistId [$playlistId].")
    }
  }

  def edit(playlistId: Long) = withSession("edit", admin = true) { implicit request => implicit td =>
    svc.update(request, playlistId = playlistId, fields = modelForm(request.body)).map(res => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.PlaylistController.view(res._1.playlistId)).flashing("success" -> res._2)
      case Accepts.Json() => Ok(res.asJson)
    })
  }

  def remove(playlistId: Long) = withSession("remove", admin = true) { implicit request => implicit td =>
    svc.remove(request, playlistId = playlistId).map(_ => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.PlaylistController.list())
      case Accepts.Json() => Ok(io.circe.Json.obj("status" -> io.circe.Json.fromString("removed")))
    })
  }

  def relationCounts(playlistId: Long) = withSession("relation.counts", admin = true) { implicit request => implicit td =>
    val creds = models.auth.Credentials.fromRequest(request)
    val playlistTrackByPlaylistIdF = playlistTrackS.countByPlaylistId(creds, playlistId)
    for (playlistTrackC <- playlistTrackByPlaylistIdF) yield {
      Ok(Seq(
        RelationCount(model = "playlistTrack", field = "playlistId", count = playlistTrackC)
      ).asJson)
    }
  }
}
