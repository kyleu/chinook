/* Generated File */
package services.commerce

import models.auth.Credentials
import models.commerce.InvoiceLine
import models.queries.commerce.InvoiceLineQueries
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import scala.concurrent.Future
import services.ModelServiceHelper
import services.database.ApplicationDatabase
import util.FutureUtils.serviceContext
import util.tracing.{TraceData, TracingService}

@javax.inject.Singleton
class InvoiceLineService @javax.inject.Inject() (override val tracing: TracingService) extends ModelServiceHelper[InvoiceLine]("invoiceLine") {
  def getByPrimaryKey(creds: Credentials, invoiceLineId: Long)(implicit trace: TraceData) = {
    traceF("get.by.primary.key")(td => ApplicationDatabase.queryF(InvoiceLineQueries.getByPrimaryKey(invoiceLineId))(td))
  }
  def getByPrimaryKeyRequired(creds: Credentials, invoiceLineId: Long)(implicit trace: TraceData) = getByPrimaryKey(creds, invoiceLineId).map { opt =>
    opt.getOrElse(throw new IllegalStateException(s"Cannot load invoiceLine with invoiceLineId [$invoiceLineId]."))
  }
  def getByPrimaryKeySeq(creds: Credentials, invoiceLineIdSeq: Seq[Long])(implicit trace: TraceData) = {
    traceF("get.by.primary.key.seq")(td => ApplicationDatabase.queryF(InvoiceLineQueries.getByPrimaryKeySeq(invoiceLineIdSeq))(td))
  }

  override def countAll(creds: Credentials, filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("get.all.count")(td => ApplicationDatabase.queryF(InvoiceLineQueries.countAll(filters))(td))
  }
  override def getAll(creds: Credentials, filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = {
    traceF("get.all")(td => ApplicationDatabase.queryF(InvoiceLineQueries.getAll(filters, orderBys, limit, offset))(td))
  }

  // Search
  override def searchCount(creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil)(implicit trace: TraceData) = {
    traceF("search.count")(td => ApplicationDatabase.queryF(InvoiceLineQueries.searchCount(q, filters))(td))
  }
  override def search(
    creds: Credentials, q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search")(td => ApplicationDatabase.queryF(InvoiceLineQueries.search(q, filters, orderBys, limit, offset))(td))
  }

  def searchExact(
    creds: Credentials, q: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None
  )(implicit trace: TraceData) = {
    traceF("search.exact")(td => ApplicationDatabase.queryF(InvoiceLineQueries.searchExact(q, orderBys, limit, offset))(td))
  }

  def countByInvoiceId(creds: Credentials, invoiceId: Long)(implicit trace: TraceData) = traceF("count.by.invoiceId") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.CountByInvoiceId(invoiceId))(td)
  }
  def getByInvoiceId(creds: Credentials, invoiceId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.invoiceId") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.GetByInvoiceId(invoiceId, orderBys, limit, offset))(td)
  }
  def getByInvoiceIdSeq(creds: Credentials, invoiceIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.invoiceId.seq") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.GetByInvoiceIdSeq(invoiceIdSeq))(td)
  }

  def countByInvoiceLineId(creds: Credentials, invoiceLineId: Long)(implicit trace: TraceData) = traceF("count.by.invoiceLineId") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.CountByInvoiceLineId(invoiceLineId))(td)
  }
  def getByInvoiceLineId(creds: Credentials, invoiceLineId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.invoiceLineId") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.GetByInvoiceLineId(invoiceLineId, orderBys, limit, offset))(td)
  }
  def getByInvoiceLineIdSeq(creds: Credentials, invoiceLineIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.invoiceLineId.seq") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.GetByInvoiceLineIdSeq(invoiceLineIdSeq))(td)
  }

  def countByQuantity(creds: Credentials, quantity: Long)(implicit trace: TraceData) = traceF("count.by.quantity") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.CountByQuantity(quantity))(td)
  }
  def getByQuantity(creds: Credentials, quantity: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.quantity") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.GetByQuantity(quantity, orderBys, limit, offset))(td)
  }
  def getByQuantitySeq(creds: Credentials, quantitySeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.quantity.seq") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.GetByQuantitySeq(quantitySeq))(td)
  }

  def countByTrackId(creds: Credentials, trackId: Long)(implicit trace: TraceData) = traceF("count.by.trackId") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.CountByTrackId(trackId))(td)
  }
  def getByTrackId(creds: Credentials, trackId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.trackId") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.GetByTrackId(trackId, orderBys, limit, offset))(td)
  }
  def getByTrackIdSeq(creds: Credentials, trackIdSeq: Seq[Long])(implicit trace: TraceData) = traceF("get.by.trackId.seq") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.GetByTrackIdSeq(trackIdSeq))(td)
  }

  def countByUnitPrice(creds: Credentials, unitPrice: BigDecimal)(implicit trace: TraceData) = traceF("count.by.unitPrice") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.CountByUnitPrice(unitPrice))(td)
  }
  def getByUnitPrice(creds: Credentials, unitPrice: BigDecimal, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None)(implicit trace: TraceData) = traceF("get.by.unitPrice") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.GetByUnitPrice(unitPrice, orderBys, limit, offset))(td)
  }
  def getByUnitPriceSeq(creds: Credentials, unitPriceSeq: Seq[BigDecimal])(implicit trace: TraceData) = traceF("get.by.unitPrice.seq") { td =>
    ApplicationDatabase.queryF(InvoiceLineQueries.GetByUnitPriceSeq(unitPriceSeq))(td)
  }

  // Mutations
  def insert(creds: Credentials, model: InvoiceLine)(implicit trace: TraceData) = traceF("insert") { td =>
    ApplicationDatabase.executeF(InvoiceLineQueries.insert(model))(td).flatMap {
      case 1 => getByPrimaryKey(creds, model.invoiceLineId)(td)
      case _ => throw new IllegalStateException("Unable to find newly-inserted Invoice Line.")
    }
  }
  def insertBatch(creds: Credentials, models: Seq[InvoiceLine])(implicit trace: TraceData) = {
    traceF("insertBatch")(td => ApplicationDatabase.executeF(InvoiceLineQueries.insertBatch(models))(td))
  }
  def create(creds: Credentials, fields: Seq[DataField])(implicit trace: TraceData) = traceF("create") { td =>
    ApplicationDatabase.executeF(InvoiceLineQueries.create(fields))(td).flatMap { _ =>
      getByPrimaryKey(creds, fieldVal(fields, "invoiceLineId").toLong)
    }
  }

  def remove(creds: Credentials, invoiceLineId: Long)(implicit trace: TraceData) = {
    traceF("remove")(td => getByPrimaryKey(creds, invoiceLineId)(td).flatMap {
      case Some(current) =>
        ApplicationDatabase.executeF(InvoiceLineQueries.removeByPrimaryKey(invoiceLineId))(td).map(_ => current)
      case None => throw new IllegalStateException(s"Cannot find InvoiceLine matching [$invoiceLineId].")
    })
  }

  def update(creds: Credentials, invoiceLineId: Long, fields: Seq[DataField])(implicit trace: TraceData) = {
    traceF("update")(td => getByPrimaryKey(creds, invoiceLineId)(td).flatMap {
      case Some(current) if fields.isEmpty => Future.successful(current -> s"No changes required for Invoice Line [$invoiceLineId].")
      case Some(_) => ApplicationDatabase.executeF(InvoiceLineQueries.update(invoiceLineId, fields))(td).flatMap { _ =>
        getByPrimaryKey(creds, invoiceLineId)(td).map {
          case Some(newModel) =>
            newModel -> s"Updated [${fields.size}] fields of Invoice Line [$invoiceLineId]."
          case None => throw new IllegalStateException(s"Cannot find InvoiceLine matching [$invoiceLineId].")
        }
      }
      case None => throw new IllegalStateException(s"Cannot find InvoiceLine matching [$invoiceLineId].")
    })
  }

  def csvFor(totalCount: Int, rows: Seq[InvoiceLine])(implicit trace: TraceData) = {
    traceB("export.csv")(td => util.CsvUtils.csvFor(Some(key), totalCount, rows, InvoiceLineQueries.fields)(td))
  }
}
