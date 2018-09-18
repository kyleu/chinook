/* Generated File */
package models.commerce

import graphql.{GraphQLContext, GraphQLSchemaHelper}
import graphql.GraphQLUtils._
import models.note.NoteSchema
import sangria.execution.deferred.{Fetcher, HasId, Relation}
import sangria.schema._

object InvoiceSchema extends GraphQLSchemaHelper("invoice") {
  implicit val invoicePrimaryKeyId: HasId[Invoice, Long] = HasId[Invoice, Long](_.invoiceId)
  private[this] def getByPrimaryKeySeq(c: GraphQLContext, idSeq: Seq[Long]) = {
    c.services.commerceServices.invoiceService.getByPrimaryKeySeq(c.creds, idSeq)(c.trace)
  }
  val invoiceByPrimaryKeyFetcher = Fetcher(getByPrimaryKeySeq)

  val invoiceInvoiceIdArg = Argument("invoiceId", LongType)
  val invoiceInvoiceIdSeqArg = Argument("invoiceIds", ListInputType(LongType))

  val invoiceCustomerIdArg = Argument("customerId", LongType)
  val invoiceCustomerIdSeqArg = Argument("customerIds", ListInputType(LongType))

  val invoiceByCustomerIdRelation = Relation[Invoice, Long]("byCustomerId", x => Seq(x.customerId))
  val invoiceByCustomerIdFetcher = Fetcher.rel[GraphQLContext, Invoice, Invoice, Long](
    getByPrimaryKeySeq, (c, rels) => c.services.commerceServices.invoiceService.getByCustomerIdSeq(c.creds, rels(invoiceByCustomerIdRelation))(c.trace)
  )

  implicit lazy val invoiceType: sangria.schema.ObjectType[GraphQLContext, Invoice] = deriveObjectType(
    sangria.macros.derive.AddFields(
      Field(
        name = "invoiceLines",
        fieldType = ListType(InvoiceLineSchema.invoiceLineType),
        resolve = c => InvoiceLineSchema.invoiceLineByInvoiceIdFetcher.deferRelSeq(
          InvoiceLineSchema.invoiceLineByInvoiceIdRelation, c.value.invoiceId
        )
      ),
      Field(
        name = "customerIdRel",
        fieldType = CustomerSchema.customerType,
        resolve = ctx => CustomerSchema.customerByPrimaryKeyFetcher.defer(ctx.value.customerId)
      ),
      Field(
        name = "relatedNotes",
        fieldType = ListType(NoteSchema.noteType),
        resolve = c => c.ctx.app.coreServices.notes.getFor(c.ctx.creds, "invoice", c.value.invoiceId)(c.ctx.trace)
      )
    )
  )

  implicit lazy val invoiceResultType: sangria.schema.ObjectType[GraphQLContext, InvoiceResult] = deriveObjectType()

  val queryFields = fields(
    unitField(name = "invoice", desc = None, t = OptionType(invoiceType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceService.getByPrimaryKey(c.ctx.creds, c.arg(invoiceInvoiceIdArg))(td)
    }, invoiceInvoiceIdArg),
    unitField(name = "invoiceSeq", desc = None, t = ListType(invoiceType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceService.getByPrimaryKeySeq(c.ctx.creds, c.arg(invoiceInvoiceIdSeqArg))(td)
    }, invoiceInvoiceIdSeqArg),
    unitField(name = "invoiceSearch", desc = None, t = invoiceResultType, f = (c, td) => {
      runSearch(c.ctx.services.commerceServices.invoiceService, c, td).map(toResult)
    }, queryArg, reportFiltersArg, orderBysArg, limitArg, offsetArg),
    unitField(name = "invoiceByInvoiceId", desc = None, t = OptionType(invoiceType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceService.getByInvoiceId(c.ctx.creds, c.arg(invoiceInvoiceIdArg))(td).map(_.headOption)
    }, invoiceInvoiceIdArg),
    unitField(name = "invoiceByInvoiceIdSeq", desc = None, t = ListType(invoiceType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceService.getByInvoiceIdSeq(c.ctx.creds, c.arg(invoiceInvoiceIdSeqArg))(td)
    }, invoiceInvoiceIdSeqArg),
    unitField(name = "invoicesByCustomerId", desc = None, t = ListType(invoiceType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceService.getByCustomerId(c.ctx.creds, c.arg(invoiceCustomerIdArg))(td)
    }, invoiceCustomerIdArg),
    unitField(name = "invoicesByCustomerIdSeq", desc = None, t = ListType(invoiceType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceService.getByCustomerIdSeq(c.ctx.creds, c.arg(invoiceCustomerIdSeqArg))(td)
    }, invoiceCustomerIdSeqArg)
  )

  val invoiceMutationType = ObjectType(
    name = "InvoiceMutations",
    fields = fields(
      unitField(name = "create", desc = None, t = OptionType(invoiceType), f = (c, td) => {
        c.ctx.services.commerceServices.invoiceService.create(c.ctx.creds, c.arg(dataFieldsArg))(td)
      }, dataFieldsArg),
      unitField(name = "update", desc = None, t = OptionType(invoiceType), f = (c, td) => {
        c.ctx.services.commerceServices.invoiceService.update(c.ctx.creds, c.arg(invoiceInvoiceIdArg), c.arg(dataFieldsArg))(td).map(_._1)
      }, invoiceInvoiceIdArg, dataFieldsArg),
      unitField(name = "remove", desc = None, t = invoiceType, f = (c, td) => {
        c.ctx.services.commerceServices.invoiceService.remove(c.ctx.creds, c.arg(invoiceInvoiceIdArg))(td)
      }, invoiceInvoiceIdArg)
    )
  )

  val mutationFields = fields(unitField(name = "invoice", desc = None, t = invoiceMutationType, f = (_, _) => scala.concurrent.Future.successful(())))

  private[this] def toResult(r: GraphQLSchemaHelper.SearchResult[Invoice]) = {
    InvoiceResult(paging = r.paging, filters = r.args.filters, orderBys = r.args.orderBys, totalCount = r.count, results = r.results, durationMs = r.dur)
  }
}
