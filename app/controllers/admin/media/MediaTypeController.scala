/* Generated File */
package controllers.admin.media

import controllers.admin.ServiceController
import models.Application
import models.media.MediaTypeResult
import models.result.RelationCount
import models.result.orderBy.OrderBy
import play.api.http.MimeTypes
import scala.concurrent.Future
import services.audit.AuditRecordService
import services.media.{MediaTypeService, TrackService}
import util.JsonSerializers._
import util.ReftreeUtils._

@javax.inject.Singleton
class MediaTypeController @javax.inject.Inject() (
    override val app: Application, svc: MediaTypeService, auditRecordSvc: AuditRecordService,
    trackS: TrackService
) extends ServiceController(svc) {
  import app.contexts.webContext

  def createForm = withSession("create.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.MediaTypeController.list()
    val call = controllers.admin.media.routes.MediaTypeController.create()
    Future.successful(Ok(views.html.admin.media.mediaTypeForm(
      request.identity, models.media.MediaType.empty(), "New Media Type", cancel, call, isNew = true, debug = app.config.debug
    )))
  }

  def create = withSession("create", admin = true) { implicit request => implicit td =>
    svc.create(request, modelForm(request.body)).map {
      case Some(model) => Redirect(controllers.admin.media.routes.MediaTypeController.view(model.mediaTypeId))
      case None => Redirect(controllers.admin.media.routes.MediaTypeController.list())
    }
  }

  def list(q: Option[String], orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("list", admin = true) { implicit request => implicit td =>
      val startMs = util.DateUtils.nowMillis
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      searchWithCount(q, orderBys, limit, offset).map(r => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.mediaTypeList(
          request.identity, Some(r._1), r._2, q, orderBy, orderAsc, limit.getOrElse(100), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(MediaTypeResult.fromRecords(q, Nil, orderBys, limit, offset, startMs, r._1, r._2).asJson)
        case ServiceController.MimeTypes.csv => csvResponse("MediaType", svc.csvFor(r._1, r._2))
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

  def view(mediaTypeId: Long, t: Option[String] = None) = withSession("view", admin = true) { implicit request => implicit td =>
    val modelF = svc.getByPrimaryKey(request, mediaTypeId)
    val notesF = app.coreServices.notes.getFor(request, "mediaType", mediaTypeId)

    notesF.flatMap(notes => modelF.map {
      case Some(model) => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.media.mediaTypeView(request.identity, model, notes, app.config.debug))
        case MimeTypes.JSON => Ok(model.asJson)
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = model)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = model)).as(ServiceController.MimeTypes.svg)
      }
      case None => NotFound(s"No MediaType found with mediaTypeId [$mediaTypeId].")
    })
  }

  def editForm(mediaTypeId: Long) = withSession("edit.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.media.routes.MediaTypeController.view(mediaTypeId)
    val call = controllers.admin.media.routes.MediaTypeController.edit(mediaTypeId)
    svc.getByPrimaryKey(request, mediaTypeId).map {
      case Some(model) => Ok(
        views.html.admin.media.mediaTypeForm(request.identity, model, s"Media Type [$mediaTypeId]", cancel, call, debug = app.config.debug)
      )
      case None => NotFound(s"No MediaType found with mediaTypeId [$mediaTypeId].")
    }
  }

  def edit(mediaTypeId: Long) = withSession("edit", admin = true) { implicit request => implicit td =>
    svc.update(request, mediaTypeId = mediaTypeId, fields = modelForm(request.body)).map(res => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.MediaTypeController.view(res._1.mediaTypeId)).flashing("success" -> res._2)
      case Accepts.Json() => Ok(res.asJson)
    })
  }

  def remove(mediaTypeId: Long) = withSession("remove", admin = true) { implicit request => implicit td =>
    svc.remove(request, mediaTypeId = mediaTypeId).map(_ => render {
      case Accepts.Html() => Redirect(controllers.admin.media.routes.MediaTypeController.list())
      case Accepts.Json() => Ok(io.circe.Json.obj("status" -> io.circe.Json.fromString("removed")))
    })
  }

  def relationCounts(mediaTypeId: Long) = withSession("relation.counts", admin = true) { implicit request => implicit td =>
    val creds = models.auth.Credentials.fromRequest(request)
    val trackByMediaTypeIdF = trackS.countByMediaTypeId(creds, mediaTypeId)
    for (trackC <- trackByMediaTypeIdF) yield {
      Ok(Seq(
        RelationCount(model = "track", field = "mediaTypeId", count = trackC)
      ).asJson)
    }
  }
}
