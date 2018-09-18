/* Generated File */
package services.commerce

import java.time.LocalDateTime
import models.auth.Credentials
import models.commerce.Invoice
import models.queries.commerce.InvoiceQueries
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import scala.concurrent.Future
import services.ModelServiceHelper
import services.database.ApplicationDatabase
import util.FutureUtils.serviceContext
import util.tracing.{TraceData, TracingService}

@javax.inject.Singleton
class InvoiceService @javax.inject.Inject() (override val tracing: TracingService) extends ModelServiceHelper[Invoice]("invoice") {
  def getByPrimaryKey(creds: Credentials, invoiceId: Long)(implicit trace: TraceData) = {
    traceF("get.by.primary.key")(td => ApplicationDatabase.queryF(InvoiceQueries.getByPrimaryKey(invoiceId))(td))
  }
  def getByPrimaryKeyRequired(creds: Credentials, invoiceId: Long)(implicit trace: TraceData) = getByPrimaryKey(creds, invoiceId).map { opt =>
    opt.getOrElse(throw new IllegalStateException(s"Cannot load invoice with invoiceId [$invoiceId]."))
  }
  def getByPrimaryKeySeq(creds: Credentials, invoiceIdSeq: Seq[Long])(implicit trace: TraceData) = {
    traceF("get.by.primary.key.seq")(td => ApplicationDatabase.queryF(InvoiceQueries.getByPrimaryKeySeq(invoiceIdSeq))(td))
  }

  override def countAll(creds: Credentials, filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("get.all.count")(td => ApplicationDatabase.queryF(InvoiceQueries.countAll(filters))(td))
  }
  override def getAll(creds: Credentials, filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = {
    traceF("get.all")(td => ApplicationDatabase.queryF(InvoiceQueries.getAll(filters, orderBys, limit, offset))(td))
  }

  // Search
  override def searchCount(creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("search.count")(td => ApplicationDatabase.queryF(InvoiceQueries.searchCount(q, filters))(td))
  }
  override def search(
    creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search")(td => ApplicationDatabase.queryF(InvoiceQueries.search(q, filters, orderBys, limit, offset))(td))
  }

  def searchExact(
    creds: Credentials, q: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search.exact")(td => ApplicationDatabase.queryF(InvoiceQueries.searchExact(q, orderBys, limit, offset))(td))
  }

  def countByCustomerId(creds: Credentials, customerId: Long)(implicit trace: TraceData) = traceF("count.by.customerId") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.CountByCustomerId(customerId))(td)
  }
  def getByCustomerId(creds: Credentials, customerId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.customerId") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.GetByCustomerId(customerId, orderBys, limit, offset))(td)
  }
  def getByCustomerIdSeq(creds: Credentials, customerIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.customerId.seq") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.GetByCustomerIdSeq(customerIdSeq))(td)
  }

  def countByInvoiceDate(creds: Credentials, invoiceDate: LocalDateTime)(implicit trace: TraceData) = traceF("count.by.invoiceDate") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.CountByInvoiceDate(invoiceDate))(td)
  }
  def getByInvoiceDate(creds: Credentials, invoiceDate: LocalDateTime, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.invoiceDate") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.GetByInvoiceDate(invoiceDate, orderBys, limit, offset))(td)
  }
  def getByInvoiceDateSeq(creds: Credentials, invoiceDateSeq: Seq[LocalDateTime])(implicit trace: TraceData) = traceF("get.by.invoiceDate.seq") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.GetByInvoiceDateSeq(invoiceDateSeq))(td)
  }

  def countByInvoiceId(creds: Credentials, invoiceId: Long)(implicit trace: TraceData) = traceF("count.by.invoiceId") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.CountByInvoiceId(invoiceId))(td)
  }
  def getByInvoiceId(creds: Credentials, invoiceId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.invoiceId") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.GetByInvoiceId(invoiceId, orderBys, limit, offset))(td)
  }
  def getByInvoiceIdSeq(creds: Credentials, invoiceIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.invoiceId.seq") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.GetByInvoiceIdSeq(invoiceIdSeq))(td)
  }

  def countByTotal(creds: Credentials, total: BigDecimal)(implicit trace: TraceData) = traceF("count.by.total") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.CountByTotal(total))(td)
  }
  def getByTotal(creds: Credentials, total: BigDecimal, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.total") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.GetByTotal(total, orderBys, limit, offset))(td)
  }
  def getByTotalSeq(creds: Credentials, totalSeq: Seq[BigDecimal])(implicit trace: TraceData) = traceF("get.by.total.seq") { td =>
    ApplicationDatabase.queryF(InvoiceQueries.GetByTotalSeq(totalSeq))(td)
  }

  // Mutations
  def insert(creds: Credentials, model: Invoice)(implicit trace: TraceData) = traceF("insert") { td =>
    ApplicationDatabase.executeF(InvoiceQueries.insert(model))(td).flatMap {
      case 1 => getByPrimaryKey(creds, model.invoiceId)(td)
      case _ => throw new IllegalStateException("Unable to find newly-inserted Invoice.")
    }
  }
  def insertBatch(creds: Credentials, models: Seq[Invoice])(implicit trace: TraceData) = {
    traceF("insertBatch")(td => ApplicationDatabase.executeF(InvoiceQueries.insertBatch(models))(td))
  }
  def create(creds: Credentials, fields: Seq[DataField])(implicit trace: TraceData) = traceF("create") { td =>
    ApplicationDatabase.executeF(InvoiceQueries.create(fields))(td).flatMap { _ =>
      getByPrimaryKey(creds, fieldVal(fields, "invoiceId").toLong)
    }
  }

  def remove(creds: Credentials, invoiceId: Long)(implicit trace: TraceData) = {
    traceF("remove")(td => getByPrimaryKey(creds, invoiceId)(td).flatMap {
      case Some(current) =>
        ApplicationDatabase.executeF(InvoiceQueries.removeByPrimaryKey(invoiceId))(td).map(_ => current)
      case None => throw new IllegalStateException(s"Cannot find Invoice matching [$invoiceId].")
    })
  }

  def update(creds: Credentials, invoiceId: Long, fields: Seq[DataField])(implicit trace: TraceData) = {
    traceF("update")(td => getByPrimaryKey(creds, invoiceId)(td).flatMap {
      case Some(current) if fields.isEmpty => Future.successful(current -> s"No changes required for Invoice [$invoiceId].")
      case Some(_) => ApplicationDatabase.executeF(InvoiceQueries.update(invoiceId, fields))(td).flatMap { _ =>
        getByPrimaryKey(creds, invoiceId)(td).map {
          case Some(newModel) =>
            newModel -> s"Updated [${fields.size}] fields of Invoice [$invoiceId]."
          case None => throw new IllegalStateException(s"Cannot find Invoice matching [$invoiceId].")
        }
      }
      case None => throw new IllegalStateException(s"Cannot find Invoice matching [$invoiceId].")
    })
  }

  def csvFor(totalCount: Int, rows: Seq[Invoice])(implicit trace: TraceData) = {
    traceB("export.csv")(td => util.CsvUtils.csvFor(Some(key), totalCount, rows, InvoiceQueries.fields)(td))
  }
}
