/* Generated File */
package controllers.admin.media

import controllers.admin.ServiceController
import models.Application
import models.media.GenreResult
import models.result.RelationCount
import models.result.orderBy.OrderBy
import play.api.http.MimeTypes
import scala.concurrent.Future
import services.audit.AuditRecordService
import services.media.{GenreService, TrackService}
import util.JsonSerializers._
import util.ReftreeUtils._

@javax.inject.Singleton
class GenreController @javax.inject.Inject() (
    override val app: Application, svc: GenreService, auditRecordSvc: AuditRecordService,
    trackS: TrackService
) extends ServiceController(svc) {
  import app.contexts.webContext

  def createForm = withSession("create.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.GenreController.list()
    val call = controllers.admin.media.routes.GenreController.create()
    Future.successful(Ok(views.html.admin.media.genreForm(
      request.identity, models.media.Genre.empty(), "New Genre", cancel, call, isNew = true, debug = app.config.debug
    )))
  }

  def create = withSession("create", admin = true) { implicit request => implicit td =>
    svc.create(request, modelForm(request.body)).map {
      case Some(model) => Redirect(controllers.admin.media.routes.GenreController.view(model.genreId))
      case None => Redirect(controllers.admin.media.routes.GenreController.list())
    }
  }

  def list(q: Option[String], orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("list", admin = true) { implicit request => implicit td =>
      val startMs = util.DateUtils.nowMillis
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      searchWithCount(q, orderBys, limit, offset).map(r => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.genreList(
          request.identity, Some(r._1), r._2, q, orderBy, orderAsc, limit.getOrElse(100), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(GenreResult.fromRecords(q, Nil, orderBys, limit, offset, startMs, r._1, r._2).asJson)
        case ServiceController.MimeTypes.csv => csvResponse("Genre", svc.csvFor(r._1, r._2))
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

  def view(genreId: Long, t: Option[String] = None) = withSession("view", admin = true) { implicit request => implicit td =>
    val modelF = svc.getByPrimaryKey(request, genreId)
    val notesF = app.coreServices.notes.getFor(request, "genre", genreId)

    notesF.flatMap(notes => modelF.map {
      case Some(model) => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.genreView(request.identity, model, notes, app.config.debug))
        case MimeTypes.JSON => Ok(model.asJson)
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = model)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = model)).as(ServiceController.MimeTypes.svg)
      }
      case None => NotFound(s"No Genre found with genreId [$genreId].")
    })
  }

  def editForm(genreId: Long) = withSession("edit.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.GenreController.view(genreId)
    val call = controllers.admin.media.routes.GenreController.edit(genreId)
    svc.getByPrimaryKey(request, genreId).map {
      case Some(model) => Ok(
        views.html.admin.media.genreForm(request.identity, model, s"Genre [$genreId]", cancel, call, debug = app.config.debug)
      )
      case None => NotFound(s"No Genre found with genreId [$genreId].")
    }
  }

  def edit(genreId: Long) = withSession("edit", admin = true) { implicit request => implicit td =>
    svc.update(request, genreId = genreId, fields = modelForm(request.body)).map(res => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.GenreController.view(res._1.genreId)).flashing("success" -> res._2)
      case Accepts.Json() => Ok(res.asJson)
    })
  }

  def remove(genreId: Long) = withSession("remove", admin = true) { implicit request => implicit td =>
    svc.remove(request, genreId = genreId).map(_ => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.GenreController.list())
      case Accepts.Json() => Ok(io.circe.Json.obj("status" -> io.circe.Json.fromString("removed")))
    })
  }

  def relationCounts(genreId: Long) = withSession("relation.counts", admin = true) { implicit request => implicit td =>
    val creds = models.auth.Credentials.fromRequest(request)
    val trackByGenreIdF = trackS.countByGenreId(creds, genreId)
    for (trackC <- trackByGenreIdF) yield {
      Ok(Seq(
        RelationCount(model = "track", field = "genreId", count = trackC)
      ).asJson)
    }
  }
}
