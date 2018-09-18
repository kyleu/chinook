/* Generated File */
package controllers.admin.commerce

import controllers.admin.ServiceController
import models.Application
import models.commerce.InvoiceResult
import models.result.RelationCount
import models.result.orderBy.OrderBy
import play.api.http.MimeTypes
import scala.concurrent.Future
import services.audit.AuditRecordService
import services.commerce.{InvoiceLineService, InvoiceService}
import util.JsonSerializers._
import util.ReftreeUtils._

@javax.inject.Singleton
class InvoiceController @javax.inject.Inject() (
    override val app: Application, svc: InvoiceService, auditRecordSvc: AuditRecordService,
    invoiceLineS: InvoiceLineService
) extends ServiceController(svc) {
  import app.contexts.webContext

  def createForm = withSession("create.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.commerce.routes.InvoiceController.list()
    val call = controllers.admin.commerce.routes.InvoiceController.create()
    Future.successful(Ok(views.html.admin.commerce.invoiceForm(
      request.identity, models.commerce.Invoice.empty(), "New Invoice", cancel, call, isNew = true, debug = app.config.debug
    )))
  }

  def create = withSession("create", admin = true) { implicit request => implicit td =>
    svc.create(request, modelForm(request.body)).map {
      case Some(model) => Redirect(controllers.admin.commerce.routes.InvoiceController.view(model.invoiceId))
      case None => Redirect(controllers.admin.commerce.routes.InvoiceController.list())
    }
  }

  def list(q: Option[String], orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("list", admin = true) { implicit request => implicit td =>
      val startMs = util.DateUtils.nowMillis
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      searchWithCount(q, orderBys, limit, offset).map(r => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.invoiceList(
          request.identity, Some(r._1), r._2, q, orderBy, orderAsc, limit.getOrElse(100), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(InvoiceResult.fromRecords(q, Nil, orderBys, limit, offset, startMs, r._1, r._2).asJson)
        case ServiceController.MimeTypes.csv => csvResponse("Invoice", svc.csvFor(r._1, r._2))
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

  def byCustomerId(customerId: Long, orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("get.by.customerId", admin = true) { implicit request => implicit td =>
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      svc.getByCustomerId(request, customerId, orderBys, limit, offset).map(models => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.invoiceByCustomerId(
          request.identity, customerId, models, orderBy, orderAsc, limit.getOrElse(5), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(models.asJson)
        case ServiceController.MimeTypes.csv => csvResponse("Invoice by customerId", svc.csvFor(0, models))
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = models)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = models)).as(ServiceController.MimeTypes.svg)
      })
    }
  }

  def view(invoiceId: Long, t: Option[String] = None) = withSession("view", admin = true) { implicit request => implicit td =>
    val modelF = svc.getByPrimaryKey(request, invoiceId)
    val notesF = app.coreServices.notes.getFor(request, "invoice", invoiceId)

    notesF.flatMap(notes => modelF.map {
      case Some(model) => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.invoiceView(request.identity, model, notes, app.config.debug))
        case MimeTypes.JSON => Ok(model.asJson)
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = model)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = model)).as(ServiceController.MimeTypes.svg)
      }
      case None => NotFound(s"No Invoice found with invoiceId [$invoiceId].")
    })
  }

  def editForm(invoiceId: Long) = withSession("edit.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.commerce.routes.InvoiceController.view(invoiceId)
    val call = controllers.admin.commerce.routes.InvoiceController.edit(invoiceId)
    svc.getByPrimaryKey(request, invoiceId).map {
      case Some(model) => Ok(
        views.html.admin.commerce.invoiceForm(request.identity, model, s"Invoice [$invoiceId]", cancel, call, debug = app.config.debug)
      )
      case None => NotFound(s"No Invoice found with invoiceId [$invoiceId].")
    }
  }

  def edit(invoiceId: Long) = withSession("edit", admin = true) { implicit request => implicit td =>
    svc.update(request, invoiceId = invoiceId, fields = modelForm(request.body)).map(res => render {
      case Accepts.Html() => Redirect(controllers.admin.commerce.routes.InvoiceController.view(res._1.invoiceId)).flashing("success" -> res._2)
      case Accepts.Json() => Ok(res.asJson)
    })
  }

  def remove(invoiceId: Long) = withSession("remove", admin = true) { implicit request => implicit td =>
    svc.remove(request, invoiceId = invoiceId).map(_ => render {
      case Accepts.Html() => Redirect(controllers.admin.commerce.routes.InvoiceController.list())
      case Accepts.Json() => Ok(io.circe.Json.obj("status" -> io.circe.Json.fromString("removed")))
    })
  }

  def relationCounts(invoiceId: Long) = withSession("relation.counts", admin = true) { implicit request => implicit td =>
    val creds = models.auth.Credentials.fromRequest(request)
    val invoiceLineByInvoiceIdF = invoiceLineS.countByInvoiceId(creds, invoiceId)
    for (invoiceLineC <- invoiceLineByInvoiceIdF) yield {
      Ok(Seq(
        RelationCount(model = "invoiceLine", field = "invoiceId", count = invoiceLineC)
      ).asJson)
    }
  }
}
