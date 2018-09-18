/* Generated File */
package services.commerce

import models.auth.Credentials
import models.commerce.Employee
import models.queries.commerce.EmployeeQueries
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import scala.concurrent.Future
import services.ModelServiceHelper
import services.database.ApplicationDatabase
import util.FutureUtils.serviceContext
import util.tracing.{TraceData, TracingService}

@javax.inject.Singleton
class EmployeeService @javax.inject.Inject() (override val tracing: TracingService) extends ModelServiceHelper[Employee]("employee") {
  def getByPrimaryKey(creds: Credentials, employeeId: Long)(implicit trace: TraceData) = {
    traceF("get.by.primary.key")(td => ApplicationDatabase.queryF(EmployeeQueries.getByPrimaryKey(employeeId))(td))
  }
  def getByPrimaryKeyRequired(creds: Credentials, employeeId: Long)(implicit trace: TraceData) = getByPrimaryKey(creds, employeeId).map { opt =>
    opt.getOrElse(throw new IllegalStateException(s"Cannot load employee with employeeId [$employeeId]."))
  }
  def getByPrimaryKeySeq(creds: Credentials, employeeIdSeq: Seq[Long])(implicit trace: TraceData) = {
    traceF("get.by.primary.key.seq")(td => ApplicationDatabase.queryF(EmployeeQueries.getByPrimaryKeySeq(employeeIdSeq))(td))
  }

  override def countAll(creds: Credentials, filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("get.all.count")(td => ApplicationDatabase.queryF(EmployeeQueries.countAll(filters))(td))
  }
  override def getAll(creds: Credentials, filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = {
    traceF("get.all")(td => ApplicationDatabase.queryF(EmployeeQueries.getAll(filters, orderBys, limit, offset))(td))
  }

  // Search
  override def searchCount(creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("search.count")(td => ApplicationDatabase.queryF(EmployeeQueries.searchCount(q, filters))(td))
  }
  override def search(
    creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search")(td => ApplicationDatabase.queryF(EmployeeQueries.search(q, filters, orderBys, limit, offset))(td))
  }

  def searchExact(
    creds: Credentials, q: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search.exact")(td => ApplicationDatabase.queryF(EmployeeQueries.searchExact(q, orderBys, limit, offset))(td))
  }

  def countByEmail(creds: Credentials, email: String)(implicit trace: TraceData) = traceF("count.by.email") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.CountByEmail(email))(td)
  }
  def getByEmail(creds: Credentials, email: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.email") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByEmail(email, orderBys, limit, offset))(td)
  }
  def getByEmailSeq(creds: Credentials, emailSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.email.seq") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByEmailSeq(emailSeq))(td)
  }

  def countByEmployeeId(creds: Credentials, employeeId: Long)(implicit trace: TraceData) = traceF("count.by.employeeId") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.CountByEmployeeId(employeeId))(td)
  }
  def getByEmployeeId(creds: Credentials, employeeId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.employeeId") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByEmployeeId(employeeId, orderBys, limit, offset))(td)
  }
  def getByEmployeeIdSeq(creds: Credentials, employeeIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.employeeId.seq") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByEmployeeIdSeq(employeeIdSeq))(td)
  }

  def countByFirstName(creds: Credentials, firstName: String)(implicit trace: TraceData) = traceF("count.by.firstName") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.CountByFirstName(firstName))(td)
  }
  def getByFirstName(creds: Credentials, firstName: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.firstName") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByFirstName(firstName, orderBys, limit, offset))(td)
  }
  def getByFirstNameSeq(creds: Credentials, firstNameSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.firstName.seq") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByFirstNameSeq(firstNameSeq))(td)
  }

  def countByLastName(creds: Credentials, lastName: String)(implicit trace: TraceData) = traceF("count.by.lastName") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.CountByLastName(lastName))(td)
  }
  def getByLastName(creds: Credentials, lastName: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.lastName") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByLastName(lastName, orderBys, limit, offset))(td)
  }
  def getByLastNameSeq(creds: Credentials, lastNameSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.lastName.seq") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByLastNameSeq(lastNameSeq))(td)
  }

  def countByReportsTo(creds: Credentials, reportsTo: Long)(implicit trace: TraceData) = traceF("count.by.reportsTo") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.CountByReportsTo(reportsTo))(td)
  }
  def getByReportsTo(creds: Credentials, reportsTo: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.reportsTo") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByReportsTo(reportsTo, orderBys, limit, offset))(td)
  }
  def getByReportsToSeq(creds: Credentials, reportsToSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.reportsTo.seq") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByReportsToSeq(reportsToSeq))(td)
  }

  def countByTitle(creds: Credentials, title: String)(implicit trace: TraceData) = traceF("count.by.title") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.CountByTitle(title))(td)
  }
  def getByTitle(creds: Credentials, title: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.title") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByTitle(title, orderBys, limit, offset))(td)
  }
  def getByTitleSeq(creds: Credentials, titleSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.title.seq") { td =>
    ApplicationDatabase.queryF(EmployeeQueries.GetByTitleSeq(titleSeq))(td)
  }

  // Mutations
  def insert(creds: Credentials, model: Employee)(implicit trace: TraceData) = traceF("insert") { td =>
    ApplicationDatabase.executeF(EmployeeQueries.insert(model))(td).flatMap {
      case 1 => getByPrimaryKey(creds, model.employeeId)(td)
      case _ => throw new IllegalStateException("Unable to find newly-inserted Employee.")
    }
  }
  def insertBatch(creds: Credentials, models: Seq[Employee])(implicit trace: TraceData) = {
    traceF("insertBatch")(td => ApplicationDatabase.executeF(EmployeeQueries.insertBatch(models))(td))
  }
  def create(creds: Credentials, fields: Seq[DataField])(implicit trace: TraceData) = traceF("create") { td =>
    ApplicationDatabase.executeF(EmployeeQueries.create(fields))(td).flatMap { _ =>
      getByPrimaryKey(creds, fieldVal(fields, "employeeId").toLong)
    }
  }

  def remove(creds: Credentials, employeeId: Long)(implicit trace: TraceData) = {
    traceF("remove")(td => getByPrimaryKey(creds, employeeId)(td).flatMap {
      case Some(current) =>
        ApplicationDatabase.executeF(EmployeeQueries.removeByPrimaryKey(employeeId))(td).map(_ => current)
      case None => throw new IllegalStateException(s"Cannot find Employee matching [$employeeId].")
    })
  }

  def update(creds: Credentials, employeeId: Long, fields: Seq[DataField])(implicit trace: TraceData) = {
    traceF("update")(td => getByPrimaryKey(creds, employeeId)(td).flatMap {
      case Some(current) if fields.isEmpty => Future.successful(current -> s"No changes required for Employee [$employeeId].")
      case Some(_) => ApplicationDatabase.executeF(EmployeeQueries.update(employeeId, fields))(td).flatMap { _ =>
        getByPrimaryKey(creds, employeeId)(td).map {
          case Some(newModel) =>
            newModel -> s"Updated [${fields.size}] fields of Employee [$employeeId]."
          case None => throw new IllegalStateException(s"Cannot find Employee matching [$employeeId].")
        }
      }
      case None => throw new IllegalStateException(s"Cannot find Employee matching [$employeeId].")
    })
  }

  def csvFor(totalCount: Int, rows: Seq[Employee])(implicit trace: TraceData) = {
    traceB("export.csv")(td => util.CsvUtils.csvFor(Some(key), totalCount, rows, EmployeeQueries.fields)(td))
  }
}
