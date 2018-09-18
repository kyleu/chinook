/* Generated File */
package controllers.admin.commerce

import controllers.admin.ServiceController
import models.Application
import models.commerce.EmployeeResult
import models.result.RelationCount
import models.result.orderBy.OrderBy
import play.api.http.MimeTypes
import scala.concurrent.Future
import services.audit.AuditRecordService
import services.commerce.{CustomerService, EmployeeService}
import util.JsonSerializers._
import util.ReftreeUtils._

@javax.inject.Singleton
class EmployeeController @javax.inject.Inject() (
    override val app: Application, svc: EmployeeService, auditRecordSvc: AuditRecordService,
    customerS: CustomerService
) extends ServiceController(svc) {
  import app.contexts.webContext

  def createForm = withSession("create.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.commerce.routes.EmployeeController.list()
    val call = controllers.admin.commerce.routes.EmployeeController.create()
    Future.successful(Ok(views.html.admin.commerce.employeeForm(
      request.identity, models.commerce.Employee.empty(), "New Employee", cancel, call, isNew = true, debug = app.config.debug
    )))
  }

  def create = withSession("create", admin = true) { implicit request => implicit td =>
    svc.create(request, modelForm(request.body)).map {
      case Some(model) => Redirect(controllers.admin.commerce.routes.EmployeeController.view(model.employeeId))
      case None => Redirect(controllers.admin.commerce.routes.EmployeeController.list())
    }
  }

  def list(q: Option[String], orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("list", admin = true) { implicit request => implicit td =>
      val startMs = util.DateUtils.nowMillis
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      searchWithCount(q, orderBys, limit, offset).map(r => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.employeeList(
          request.identity, Some(r._1), r._2, q, orderBy, orderAsc, limit.getOrElse(100), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(EmployeeResult.fromRecords(q, Nil, orderBys, limit, offset, startMs, r._1, r._2).asJson)
        case ServiceController.MimeTypes.csv => csvResponse("Employee", svc.csvFor(r._1, r._2))
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

  def byReportsTo(reportsTo: Long, orderBy: Option[String], orderAsc: Boolean, limit: Option[Int], offset: Option[Int], t: Option[String] = None) = {
    withSession("get.by.reportsTo", admin = true) { implicit request => implicit td =>
      val orderBys = OrderBy.forVals(orderBy, orderAsc).toSeq
      svc.getByReportsTo(request, reportsTo, orderBys, limit, offset).map(models => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.employeeByReportsTo(
          request.identity, reportsTo, models, orderBy, orderAsc, limit.getOrElse(5), offset.getOrElse(0)
        ))
        case MimeTypes.JSON => Ok(models.asJson)
        case ServiceController.MimeTypes.csv => csvResponse("Employee by reportsTo", svc.csvFor(0, models))
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = models)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = models)).as(ServiceController.MimeTypes.svg)
      })
    }
  }

  def view(employeeId: Long, t: Option[String] = None) = withSession("view", admin = true) { implicit request => implicit td =>
    val modelF = svc.getByPrimaryKey(request, employeeId)
    val notesF = app.coreServices.notes.getFor(request, "employee", employeeId)

    notesF.flatMap(notes => modelF.map {
      case Some(model) => renderChoice(t) {
        case MimeTypes.HTML => Ok(views.html.admin.commerce.employeeView(request.identity, model, notes, app.config.debug))
        case MimeTypes.JSON => Ok(model.asJson)
        case ServiceController.MimeTypes.png => Ok(renderToPng(v = model)).as(ServiceController.MimeTypes.png)
        case ServiceController.MimeTypes.svg => Ok(renderToSvg(v = model)).as(ServiceController.MimeTypes.svg)
      }
      case None => NotFound(s"No Employee found with employeeId [$employeeId].")
    })
  }

  def editForm(employeeId: Long) = withSession("edit.form", admin = true) { implicit request => implicit td =>
    val cancel = controllers.admin.commerce.routes.EmployeeController.view(employeeId)
    val call = controllers.admin.commerce.routes.EmployeeController.edit(employeeId)
    svc.getByPrimaryKey(request, employeeId).map {
      case Some(model) => Ok(
        views.html.admin.commerce.employeeForm(request.identity, model, s"Employee [$employeeId]", cancel, call, debug = app.config.debug)
      )
      case None => NotFound(s"No Employee found with employeeId [$employeeId].")
    }
  }

  def edit(employeeId: Long) = withSession("edit", admin = true) { implicit request => implicit td =>
    svc.update(request, employeeId = employeeId, fields = modelForm(request.body)).map(res => render {
      case Accepts.Html() => Redirect(controllers.admin.commerce.routes.EmployeeController.view(res._1.employeeId)).flashing("success" -> res._2)
      case Accepts.Json() => Ok(res.asJson)
    })
  }

  def remove(employeeId: Long) = withSession("remove", admin = true) { implicit request => implicit td =>
    svc.remove(request, employeeId = employeeId).map(_ => render {
      case Accepts.Html() => Redirect(controllers.admin.commerce.routes.EmployeeController.list())
      case Accepts.Json() => Ok(io.circe.Json.obj("status" -> io.circe.Json.fromString("removed")))
    })
  }

  def relationCounts(employeeId: Long) = withSession("relation.counts", admin = true) { implicit request => implicit td =>
    val creds = models.auth.Credentials.fromRequest(request)
    val customerBySupportRepIdF = customerS.countBySupportRepId(creds, employeeId)
    for (customerC <- customerBySupportRepIdF) yield {
      Ok(Seq(
        RelationCount(model = "customer", field = "supportRepId", count = customerC)
      ).asJson)
    }
  }
}
