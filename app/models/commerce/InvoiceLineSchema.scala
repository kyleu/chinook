/* Generated File */
package models.commerce

import graphql.{GraphQLContext, GraphQLSchemaHelper}
import graphql.GraphQLUtils._
import models.media.TrackSchema
import models.note.NoteSchema
import sangria.execution.deferred.{Fetcher, HasId, Relation}
import sangria.schema._

object InvoiceLineSchema extends GraphQLSchemaHelper("invoiceLine") {
  implicit val invoiceLinePrimaryKeyId: HasId[InvoiceLine, Long] = HasId[InvoiceLine, Long](_.invoiceLineId)
  private[this] def getByPrimaryKeySeq(c: GraphQLContext, idSeq: Seq[Long]) = {
    c.services.commerceServices.invoiceLineService.getByPrimaryKeySeq(c.creds, idSeq)(c.trace)
  }
  val invoiceLineByPrimaryKeyFetcher = Fetcher(getByPrimaryKeySeq)

  val invoiceLineInvoiceLineIdArg = Argument("invoiceLineId", LongType)
  val invoiceLineInvoiceLineIdSeqArg = Argument("invoiceLineIds", ListInputType(LongType))

  val invoiceLineInvoiceIdArg = Argument("invoiceId", LongType)
  val invoiceLineInvoiceIdSeqArg = Argument("invoiceIds", ListInputType(LongType))
  val invoiceLineTrackIdArg = Argument("trackId", LongType)
  val invoiceLineTrackIdSeqArg = Argument("trackIds", ListInputType(LongType))

  val invoiceLineByInvoiceIdRelation = Relation[InvoiceLine, Long]("byInvoiceId", x => Seq(x.invoiceId))
  val invoiceLineByInvoiceIdFetcher = Fetcher.rel[GraphQLContext, InvoiceLine, InvoiceLine, Long](
    getByPrimaryKeySeq, (c, rels) => c.services.commerceServices.invoiceLineService.getByInvoiceIdSeq(c.creds, rels(invoiceLineByInvoiceIdRelation))(c.trace)
  )

  val invoiceLineByTrackIdRelation = Relation[InvoiceLine, Long]("byTrackId", x => Seq(x.trackId))
  val invoiceLineByTrackIdFetcher = Fetcher.rel[GraphQLContext, InvoiceLine, InvoiceLine, Long](
    getByPrimaryKeySeq, (c, rels) => c.services.commerceServices.invoiceLineService.getByTrackIdSeq(c.creds, rels(invoiceLineByTrackIdRelation))(c.trace)
  )

  implicit lazy val invoiceLineType: sangria.schema.ObjectType[GraphQLContext, InvoiceLine] = deriveObjectType(
    sangria.macros.derive.AddFields(
      Field(
        name = "invoiceIdRel",
        fieldType = InvoiceSchema.invoiceType,
        resolve = ctx => InvoiceSchema.invoiceByPrimaryKeyFetcher.defer(ctx.value.invoiceId)
      ),
      Field(
        name = "trackIdRel",
        fieldType = TrackSchema.trackType,
        resolve = ctx => TrackSchema.trackByPrimaryKeyFetcher.defer(ctx.value.trackId)
      ),
      Field(
        name = "relatedNotes",
        fieldType = ListType(NoteSchema.noteType),
        resolve = c => c.ctx.app.coreServices.notes.getFor(c.ctx.creds, "invoiceLine", c.value.invoiceLineId)(c.ctx.trace)
      )
    )
  )

  implicit lazy val invoiceLineResultType: sangria.schema.ObjectType[GraphQLContext, InvoiceLineResult] = deriveObjectType()

  val queryFields = fields(
    unitField(name = "invoiceLine", desc = None, t = OptionType(invoiceLineType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceLineService.getByPrimaryKey(c.ctx.creds, c.arg(invoiceLineInvoiceLineIdArg))(td)
    }, invoiceLineInvoiceLineIdArg),
    unitField(name = "invoiceLineSeq", desc = None, t = ListType(invoiceLineType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceLineService.getByPrimaryKeySeq(c.ctx.creds, c.arg(invoiceLineInvoiceLineIdSeqArg))(td)
    }, invoiceLineInvoiceLineIdSeqArg),
    unitField(name = "invoiceLineSearch", desc = None, t = invoiceLineResultType, f = (c, td) => {
      runSearch(c.ctx.services.commerceServices.invoiceLineService, c, td).map(toResult)
    }, queryArg, reportFiltersArg, orderBysArg, limitArg, offsetArg),
    unitField(name = "invoiceLineByInvoiceLineId", desc = None, t = OptionType(invoiceLineType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceLineService.getByInvoiceLineId(c.ctx.creds, c.arg(invoiceLineInvoiceLineIdArg))(td).map(_.headOption)
    }, invoiceLineInvoiceLineIdArg),
    unitField(name = "invoiceLineByInvoiceLineIdSeq", desc = None, t = ListType(invoiceLineType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceLineService.getByInvoiceLineIdSeq(c.ctx.creds, c.arg(invoiceLineInvoiceLineIdSeqArg))(td)
    }, invoiceLineInvoiceLineIdSeqArg),
    unitField(name = "invoiceLinesByInvoiceId", desc = None, t = ListType(invoiceLineType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceLineService.getByInvoiceId(c.ctx.creds, c.arg(invoiceLineInvoiceIdArg))(td)
    }, invoiceLineInvoiceIdArg),
    unitField(name = "invoiceLinesByInvoiceIdSeq", desc = None, t = ListType(invoiceLineType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceLineService.getByInvoiceIdSeq(c.ctx.creds, c.arg(invoiceLineInvoiceIdSeqArg))(td)
    }, invoiceLineInvoiceIdSeqArg),
    unitField(name = "invoiceLinesByTrackId", desc = None, t = ListType(invoiceLineType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceLineService.getByTrackId(c.ctx.creds, c.arg(invoiceLineTrackIdArg))(td)
    }, invoiceLineTrackIdArg),
    unitField(name = "invoiceLinesByTrackIdSeq", desc = None, t = ListType(invoiceLineType), f = (c, td) => {
      c.ctx.services.commerceServices.invoiceLineService.getByTrackIdSeq(c.ctx.creds, c.arg(invoiceLineTrackIdSeqArg))(td)
    }, invoiceLineTrackIdSeqArg)
  )

  val invoiceLineMutationType = ObjectType(
    name = "InvoiceLineMutations",
    fields = fields(
      unitField(name = "create", desc = None, t = OptionType(invoiceLineType), f = (c, td) => {
        c.ctx.services.commerceServices.invoiceLineService.create(c.ctx.creds, c.arg(dataFieldsArg))(td)
      }, dataFieldsArg),
      unitField(name = "update", desc = None, t = OptionType(invoiceLineType), f = (c, td) => {
        c.ctx.services.commerceServices.invoiceLineService.update(c.ctx.creds, c.arg(invoiceLineInvoiceLineIdArg), c.arg(dataFieldsArg))(td).map(_._1)
      }, invoiceLineInvoiceLineIdArg, dataFieldsArg),
      unitField(name = "remove", desc = None, t = invoiceLineType, f = (c, td) => {
        c.ctx.services.commerceServices.invoiceLineService.remove(c.ctx.creds, c.arg(invoiceLineInvoiceLineIdArg))(td)
      }, invoiceLineInvoiceLineIdArg)
    )
  )

  val mutationFields = fields(unitField(name = "invoiceLine", desc = None, t = invoiceLineMutationType, f = (_, _) => scala.concurrent.Future.successful(())))

  private[this] def toResult(r: GraphQLSchemaHelper.SearchResult[InvoiceLine]) = {
    InvoiceLineResult(paging = r.paging, filters = r.args.filters, orderBys = r.args.orderBys, totalCount = r.count, results = r.results, durationMs = r.dur)
  }
}
