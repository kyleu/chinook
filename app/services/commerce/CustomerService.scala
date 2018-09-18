/* Generated File */
package services.commerce

import models.auth.Credentials
import models.commerce.Customer
import models.queries.commerce.CustomerQueries
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import scala.concurrent.Future
import services.ModelServiceHelper
import services.database.ApplicationDatabase
import util.FutureUtils.serviceContext
import util.tracing.{TraceData, TracingService}

@javax.inject.Singleton
class CustomerService @javax.inject.Inject() (override val tracing: TracingService) extends ModelServiceHelper[Customer]("customer") {
  def getByPrimaryKey(creds: Credentials, customerId: Long)(implicit trace: TraceData) = {
    traceF("get.by.primary.key")(td => ApplicationDatabase.queryF(CustomerQueries.getByPrimaryKey(customerId))(td))
  }
  def getByPrimaryKeyRequired(creds: Credentials, customerId: Long)(implicit trace: TraceData) = getByPrimaryKey(creds, customerId).map { opt =>
    opt.getOrElse(throw new IllegalStateException(s"Cannot load customer with customerId [$customerId]."))
  }
  def getByPrimaryKeySeq(creds: Credentials, customerIdSeq: Seq[Long])(implicit trace: TraceData) = {
    traceF("get.by.primary.key.seq")(td => ApplicationDatabase.queryF(CustomerQueries.getByPrimaryKeySeq(customerIdSeq))(td))
  }

  override def countAll(creds: Credentials, filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("get.all.count")(td => ApplicationDatabase.queryF(CustomerQueries.countAll(filters))(td))
  }
  override def getAll(creds: Credentials, filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = {
    traceF("get.all")(td => ApplicationDatabase.queryF(CustomerQueries.getAll(filters, orderBys, limit, offset))(td))
  }

  // Search
  override def searchCount(creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("search.count")(td => ApplicationDatabase.queryF(CustomerQueries.searchCount(q, filters))(td))
  }
  override def search(
    creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search")(td => ApplicationDatabase.queryF(CustomerQueries.search(q, filters, orderBys, limit, offset))(td))
  }

  def searchExact(
    creds: Credentials, q: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search.exact")(td => ApplicationDatabase.queryF(CustomerQueries.searchExact(q, orderBys, limit, offset))(td))
  }

  def countByCompany(creds: Credentials, company: String)(implicit trace: TraceData) = traceF("count.by.company") { td =>
    ApplicationDatabase.queryF(CustomerQueries.CountByCompany(company))(td)
  }
  def getByCompany(creds: Credentials, company: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.company") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetByCompany(company, orderBys, limit, offset))(td)
  }
  def getByCompanySeq(creds: Credentials, companySeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.company.seq") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetByCompanySeq(companySeq))(td)
  }

  def countByCustomerId(creds: Credentials, customerId: Long)(implicit trace: TraceData) = traceF("count.by.customerId") { td =>
    ApplicationDatabase.queryF(CustomerQueries.CountByCustomerId(customerId))(td)
  }
  def getByCustomerId(creds: Credentials, customerId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.customerId") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetByCustomerId(customerId, orderBys, limit, offset))(td)
  }
  def getByCustomerIdSeq(creds: Credentials, customerIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.customerId.seq") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetByCustomerIdSeq(customerIdSeq))(td)
  }

  def countByEmail(creds: Credentials, email: String)(implicit trace: TraceData) = traceF("count.by.email") { td =>
    ApplicationDatabase.queryF(CustomerQueries.CountByEmail(email))(td)
  }
  def getByEmail(creds: Credentials, email: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.email") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetByEmail(email, orderBys, limit, offset))(td)
  }
  def getByEmailSeq(creds: Credentials, emailSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.email.seq") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetByEmailSeq(emailSeq))(td)
  }

  def countByFirstName(creds: Credentials, firstName: String)(implicit trace: TraceData) = traceF("count.by.firstName") { td =>
    ApplicationDatabase.queryF(CustomerQueries.CountByFirstName(firstName))(td)
  }
  def getByFirstName(creds: Credentials, firstName: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.firstName") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetByFirstName(firstName, orderBys, limit, offset))(td)
  }
  def getByFirstNameSeq(creds: Credentials, firstNameSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.firstName.seq") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetByFirstNameSeq(firstNameSeq))(td)
  }

  def countByLastName(creds: Credentials, lastName: String)(implicit trace: TraceData) = traceF("count.by.lastName") { td =>
    ApplicationDatabase.queryF(CustomerQueries.CountByLastName(lastName))(td)
  }
  def getByLastName(creds: Credentials, lastName: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.lastName") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetByLastName(lastName, orderBys, limit, offset))(td)
  }
  def getByLastNameSeq(creds: Credentials, lastNameSeq: Seq[String])(implicit trace: TraceData) = traceF("get.by.lastName.seq") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetByLastNameSeq(lastNameSeq))(td)
  }

  def countBySupportRepId(creds: Credentials, supportRepId: Long)(implicit trace: TraceData) = traceF("count.by.supportRepId") { td =>
    ApplicationDatabase.queryF(CustomerQueries.CountBySupportRepId(supportRepId))(td)
  }
  def getBySupportRepId(creds: Credentials, supportRepId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.supportRepId") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetBySupportRepId(supportRepId, orderBys, limit, offset))(td)
  }
  def getBySupportRepIdSeq(creds: Credentials, supportRepIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.supportRepId.seq") { td =>
    ApplicationDatabase.queryF(CustomerQueries.GetBySupportRepIdSeq(supportRepIdSeq))(td)
  }

  // Mutations
  def insert(creds: Credentials, model: Customer)(implicit trace: TraceData) = traceF("insert") { td =>
    ApplicationDatabase.executeF(CustomerQueries.insert(model))(td).flatMap {
      case 1 => getByPrimaryKey(creds, model.customerId)(td)
      case _ => throw new IllegalStateException("Unable to find newly-inserted Customer.")
    }
  }
  def insertBatch(creds: Credentials, models: Seq[Customer])(implicit trace: TraceData) = {
    traceF("insertBatch")(td => ApplicationDatabase.executeF(CustomerQueries.insertBatch(models))(td))
  }
  def create(creds: Credentials, fields: Seq[DataField])(implicit trace: TraceData) = traceF("create") { td =>
    ApplicationDatabase.executeF(CustomerQueries.create(fields))(td).flatMap { _ =>
      getByPrimaryKey(creds, fieldVal(fields, "customerId").toLong)
    }
  }

  def remove(creds: Credentials, customerId: Long)(implicit trace: TraceData) = {
    traceF("remove")(td => getByPrimaryKey(creds, customerId)(td).flatMap {
      case Some(current) =>
        ApplicationDatabase.executeF(CustomerQueries.removeByPrimaryKey(customerId))(td).map(_ => current)
      case None => throw new IllegalStateException(s"Cannot find Customer matching [$customerId].")
    })
  }

  def update(creds: Credentials, customerId: Long, fields: Seq[DataField])(implicit trace: TraceData) = {
    traceF("update")(td => getByPrimaryKey(creds, customerId)(td).flatMap {
      case Some(current) if fields.isEmpty => Future.successful(current -> s"No changes required for Customer [$customerId].")
      case Some(_) => ApplicationDatabase.executeF(CustomerQueries.update(customerId, fields))(td).flatMap { _ =>
        getByPrimaryKey(creds, customerId)(td).map {
          case Some(newModel) =>
            newModel -> s"Updated [${fields.size}] fields of Customer [$customerId]."
          case None => throw new IllegalStateException(s"Cannot find Customer matching [$customerId].")
        }
      }
      case None => throw new IllegalStateException(s"Cannot find Customer matching [$customerId].")
    })
  }

  def csvFor(totalCount: Int, rows: Seq[Customer])(implicit trace: TraceData) = {
    traceB("export.csv")(td => util.CsvUtils.csvFor(Some(key), totalCount, rows, CustomerQueries.fields)(td))
  }
}
