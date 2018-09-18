/* Generated File */
package controllers.admin.commerce

import controllers.admin.ServiceController
import models.Application
import models.commerce.CustomerResult
import models.result.RelationCount
import models.result.orderBy.OrderBy
import play.api.http.MimeTypes
import scala.concurrent.Future
import services.audit.AuditRecordService
import services.commerce.{CustomerService, InvoiceService}
import util.JsonSerializers._
import util.ReftreeUtils._

@javax.inject.Singleton
class CustomerController @javax.inject.Inject() (
    override val app: Application, svc: CustomerService, auditRecordSvc: AuditRecordService,
    invoiceS: InvoiceService
) extends ServiceController(svc) {
  import app.contexts.webContext

  def createForm = withSession("create.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.commerce.routes.CustomerController.list()
    val call = controllers.admin.commerce.routes.CustomerController.create()
    Future.successful(Ok(views.html.admin.commerce.customerForm(
      request.identity, models.commerce.Customer.empty(), "New Customer", cancel, call, isNew = true, debug = app.config.debug
    )))
  }

  def create = withSession("create", admin = true) { implicit request => implicit td =>
    svc.create(request, modelForm(request.body)).map {
      case Some(model) => Redirect(controllers.admin.commerce.routes.CustomerController.view(model.customerId))
      case None => Redirect(controllers.admin.commerce.routes.CustomerController.list())
    }
  }

  def list(q: Option[String], orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("list", admin = true) { implicit request => implicit td =>
      val startMs = util.DateUtils.nowMillis
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      searchWithCount(q, orderBys, limit, offset).map(r => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.customerList(
          request.identity, Some(r._1), r._2, q, orderBy, orderAsc, limit.getOrElse(100), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(CustomerResult.fromRecords(q, Nil, orderBys, limit, offset, startMs, r._1, r._2).asJson)
        case ServiceController.MimeTypes.csv => csvResponse("Customer", svc.csvFor(r._1, r._2))
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

  def bySupportRepId(supportRepId: Long, orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("get.by.supportRepId", admin = true) { implicit request => implicit td =>
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      svc.getBySupportRepId(request, supportRepId, orderBys, limit, offset).map(models => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.customerBySupportRepId(
          request.identity, supportRepId, models, orderBy, orderAsc, limit.getOrElse(5), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(models.asJson)
        case ServiceController.MimeTypes.csv => csvResponse("Customer by supportRepId", svc.csvFor(0, models))
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = models)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = models)).as(ServiceController.MimeTypes.svg)
      })
    }
  }

  def view(customerId: Long, t: Option[String] = None) = withSession("view", admin = true) { implicit request => implicit td =>
    val modelF = svc.getByPrimaryKey(request, customerId)
    val notesF = app.coreServices.notes.getFor(request, "customer", customerId)

    notesF.flatMap(notes => modelF.map {
      case Some(model) => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.customerView(request.identity, model, notes, app.config.debug))
        case MimeTypes.JSON => Ok(model.asJson)
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = model)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = model)).as(ServiceController.MimeTypes.svg)
      }
      case None => NotFound(s"No Customer found with customerId [$customerId].")
    })
  }

  def editForm(customerId: Long) = withSession("edit.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.commerce.routes.CustomerController.view(customerId)
    val call = controllers.admin.commerce.routes.CustomerController.edit(customerId)
    svc.getByPrimaryKey(request, customerId).map {
      case Some(model) => Ok(
        views.html.admin.commerce.customerForm(request.identity, model, s"Customer [$customerId]", cancel, call, debug = app.config.debug)
      )
      case None => NotFound(s"No Customer found with customerId [$customerId].")
    }
  }

  def edit(customerId: Long) = withSession("edit", admin = true) { implicit request => implicit td =>
    svc.update(request, customerId = customerId, fields = modelForm(request.body)).map(res => render {
      case Accepts.Html() => Redirect(controllers.admin.commerce.routes.CustomerController.view(res._1.customerId)).flashing("success" -> res._2)
      case Accepts.Json() => Ok(res.asJson)
    })
  }

  def remove(customerId: Long) = withSession("remove", admin = true) { implicit request => implicit td =>
    svc.remove(request, customerId = customerId).map(_ => render {
      case Accepts.Html() => Redirect(controllers.admin.commerce.routes.CustomerController.list())
      case Accepts.Json() => Ok(io.circe.Json.obj("status" -> io.circe.Json.fromString("removed")))
    })
  }

  def relationCounts(customerId: Long) = withSession("relation.counts", admin = true) { implicit request => implicit td =>
    val creds = models.auth.Credentials.fromRequest(request)
    val invoiceByCustomerIdF = invoiceS.countByCustomerId(creds, customerId)
    for (invoiceC <- invoiceByCustomerIdF) yield {
      Ok(Seq(
        RelationCount(model = "invoice", field = "customerId", count = invoiceC)
      ).asJson)
    }
  }
}
