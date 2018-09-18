/* Generated File */
package controllers.admin.media

import controllers.admin.ServiceController
import models.Application
import models.media.ArtistResult
import models.result.RelationCount
import models.result.orderBy.OrderBy
import play.api.http.MimeTypes
import scala.concurrent.Future
import services.audit.AuditRecordService
import services.media.{AlbumService, ArtistService}
import util.JsonSerializers._
import util.ReftreeUtils._

@javax.inject.Singleton
class ArtistController @javax.inject.Inject() (
    override val app: Application, svc: ArtistService, auditRecordSvc: AuditRecordService,
    albumS: AlbumService
) extends ServiceController(svc) {
  import app.contexts.webContext

  def createForm = withSession("create.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.ArtistController.list()
    val call = controllers.admin.media.routes.ArtistController.create()
    Future.successful(Ok(views.html.admin.media.artistForm(
      request.identity, models.media.Artist.empty(), "New Artist", cancel, call, isNew = true, debug = app.config.debug
    )))
  }

  def create = withSession("create", admin = true) { implicit request => implicit td =>
    svc.create(request, modelForm(request.body)).map {
      case Some(model) => Redirect(controllers.admin.media.routes.ArtistController.view(model.artistId))
      case None => Redirect(controllers.admin.media.routes.ArtistController.list())
    }
  }

  def list(q: Option[String], orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("list", admin = true) { implicit request => implicit td =>
      val startMs = util.DateUtils.nowMillis
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      searchWithCount(q, orderBys, limit, offset).map(r => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.artistList(
          request.identity, Some(r._1), r._2, q, orderBy, orderAsc, limit.getOrElse(100), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(ArtistResult.fromRecords(q, Nil, orderBys, limit, offset, startMs, r._1, r._2).asJson)
        case ServiceController.MimeTypes.csv => csvResponse("Artist", svc.csvFor(r._1, r._2))
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

  def view(artistId: Long, t: Option[String] = None) = withSession("view", admin = true) { implicit request => implicit td =>
    val modelF = svc.getByPrimaryKey(request, artistId)
    val notesF = app.coreServices.notes.getFor(request, "artist", artistId)

    notesF.flatMap(notes => modelF.map {
      case Some(model) => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.artistView(request.identity, model, notes, app.config.debug))
        case MimeTypes.JSON => Ok(model.asJson)
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = model)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = model)).as(ServiceController.MimeTypes.svg)
      }
      case None => NotFound(s"No Artist found with artistId [$artistId].")
    })
  }

  def editForm(artistId: Long) = withSession("edit.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.ArtistController.view(artistId)
    val call = controllers.admin.media.routes.ArtistController.edit(artistId)
    svc.getByPrimaryKey(request, artistId).map {
      case Some(model) => Ok(
        views.html.admin.media.artistForm(request.identity, model, s"Artist [$artistId]", cancel, call, debug = app.config.debug)
      )
      case None => NotFound(s"No Artist found with artistId [$artistId].")
    }
  }

  def edit(artistId: Long) = withSession("edit", admin = true) { implicit request => implicit td =>
    svc.update(request, artistId = artistId, fields = modelForm(request.body)).map(res => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.ArtistController.view(res._1.artistId)).flashing("success" -> res._2)
      case Accepts.Json() => Ok(res.asJson)
    })
  }

  def remove(artistId: Long) = withSession("remove", admin = true) { implicit request => implicit td =>
    svc.remove(request, artistId = artistId).map(_ => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.ArtistController.list())
      case Accepts.Json() => Ok(io.circe.Json.obj("status" -> io.circe.Json.fromString("removed")))
    })
  }

  def relationCounts(artistId: Long) = withSession("relation.counts", admin = true) { implicit request => implicit td =>
    val creds = models.auth.Credentials.fromRequest(request)
    val albumByArtistIdF = albumS.countByArtistId(creds, artistId)
    for (albumC <- albumByArtistIdF) yield {
      Ok(Seq(
        RelationCount(model = "album", field = "artistId", count = albumC)
      ).asJson)
    }
  }
}
