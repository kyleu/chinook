/* Generated File */
package controllers.admin.commerce

import controllers.admin.ServiceController
import models.Application
import models.commerce.InvoiceLineResult
import models.result.orderBy.OrderBy
import play.api.http.MimeTypes
import scala.concurrent.Future
import services.audit.AuditRecordService
import services.commerce.InvoiceLineService
import util.JsonSerializers._
import util.ReftreeUtils._

@javax.inject.Singleton
class InvoiceLineController @javax.inject.Inject() (
    override val app: Application, svc: InvoiceLineService, auditRecordSvc: AuditRecordService
) extends ServiceController(svc) {
  import app.contexts.webContext

  def createForm = withSession("create.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.commerce.routes.InvoiceLineController.list()
    val call = controllers.admin.commerce.routes.InvoiceLineController.create()
    Future.successful(Ok(views.html.admin.commerce.invoiceLineForm(
      request.identity, models.commerce.InvoiceLine.empty(), "New Invoice Line", cancel, call, isNew = true, debug = app.config.debug
    )))
  }

  def create = withSession("create", admin = true) { implicit request => implicit td =>
    svc.create(request, modelForm(request.body)).map {
      case Some(model) => Redirect(controllers.admin.commerce.routes.InvoiceLineController.view(model.invoiceLineId))
      case None => Redirect(controllers.admin.commerce.routes.InvoiceLineController.list())
    }
  }

  def list(q: Option[String], orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("list", admin = true) { implicit request => implicit td =>
      val startMs = util.DateUtils.nowMillis
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      searchWithCount(q, orderBys, limit, offset).map(r => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.invoiceLineList(
          request.identity, Some(r._1), r._2, q, orderBy, orderAsc, limit.getOrElse(100), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(InvoiceLineResult.fromRecords(q, Nil, orderBys, limit, offset, startMs, r._1, r._2).asJson)
        case ServiceController.MimeTypes.csv => csvResponse("InvoiceLine", svc.csvFor(r._1, r._2))
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

  def byInvoiceId(invoiceId: Long, orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("get.by.invoiceId", admin = true) { implicit request => implicit td =>
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      svc.getByInvoiceId(request, invoiceId, orderBys, limit, offset).map(models => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.invoiceLineByInvoiceId(
          request.identity, invoiceId, models, orderBy, orderAsc, limit.getOrElse(5), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(models.asJson)
        case ServiceController.MimeTypes.csv => csvResponse("InvoiceLine by invoiceId", svc.csvFor(0, models))
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = models)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = models)).as(ServiceController.MimeTypes.svg)
      })
    }
  }

  def byTrackId(trackId: Long, orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("get.by.trackId", admin = true) { implicit request => implicit td =>
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      svc.getByTrackId(request, trackId, orderBys, limit, offset).map(models => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.invoiceLineByTrackId(
          request.identity, trackId, models, orderBy, orderAsc, limit.getOrElse(5), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(models.asJson)
        case ServiceController.MimeTypes.csv => csvResponse("InvoiceLine by trackId", svc.csvFor(0, models))
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = models)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = models)).as(ServiceController.MimeTypes.svg)
      })
    }
  }

  def view(invoiceLineId: Long, t: Option[String] = None) = withSession("view", admin = true) { implicit request => implicit td =>
    val modelF = svc.getByPrimaryKey(request, invoiceLineId)
    val notesF = app.coreServices.notes.getFor(request, "invoiceLine", invoiceLineId)

    notesF.flatMap(notes => modelF.map {
      case Some(model) => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.invoiceLineView(request.identity, model, notes, app.config.debug))
        case MimeTypes.JSON => Ok(model.asJson)
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = model)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = model)).as(ServiceController.MimeTypes.svg)
      }
      case None => NotFound(s"No InvoiceLine found with invoiceLineId [$invoiceLineId].")
    })
  }

  def editForm(invoiceLineId: Long) = withSession("edit.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.commerce.routes.InvoiceLineController.view(invoiceLineId)
    val call = controllers.admin.commerce.routes.InvoiceLineController.edit(invoiceLineId)
    svc.getByPrimaryKey(request, invoiceLineId).map {
      case Some(model) => Ok(
        views.html.admin.commerce.invoiceLineForm(request.identity, model, s"Invoice Line [$invoiceLineId]", cancel, call, debug = app.config.debug)
      )
      case None => NotFound(s"No InvoiceLine found with invoiceLineId [$invoiceLineId].")
    }
  }

  def edit(invoiceLineId: Long) = withSession("edit", admin = true) { implicit request => implicit td =>
    svc.update(request, invoiceLineId = invoiceLineId, fields = modelForm(request.body)).map(res => render {
      case Accepts.Html() => Redirect(controllers.admin.commerce.routes.InvoiceLineController.view(res._1.invoiceLineId)).flashing("success" -> res._2)
      case Accepts.Json() => Ok(res.asJson)
    })
  }

  def remove(invoiceLineId: Long) = withSession("remove", admin = true) { implicit request => implicit td =>
    svc.remove(request, invoiceLineId = invoiceLineId).map(_ => render {
      case Accepts.Html() => Redirect(controllers.admin.commerce.routes.InvoiceLineController.list())
      case Accepts.Json() => Ok(io.circe.Json.obj("status" -> io.circe.Json.fromString("removed")))
    })
  }
}
