/* Generated File */
package models.commerce

import graphql.{GraphQLContext, GraphQLSchemaHelper}
import graphql.GraphQLUtils._
import models.note.NoteSchema
import sangria.execution.deferred.{Fetcher, HasId, Relation}
import sangria.schema._

object CustomerSchema extends GraphQLSchemaHelper("customer") {
  implicit val customerPrimaryKeyId: HasId[Customer, Long] = HasId[Customer, Long](_.customerId)
  private[this] def getByPrimaryKeySeq(c: GraphQLContext, idSeq: Seq[Long]) = {
    c.services.commerceServices.customerService.getByPrimaryKeySeq(c.creds, idSeq)(c.trace)
  }
  val customerByPrimaryKeyFetcher = Fetcher(getByPrimaryKeySeq)

  val customerCustomerIdArg = Argument("customerId", LongType)
  val customerCustomerIdSeqArg = Argument("customerIds", ListInputType(LongType))

  val customerSupportRepIdArg = Argument("supportRepId", OptionInputType(LongType))
  val customerSupportRepIdSeqArg = Argument("supportRepIds", ListInputType(LongType))

  val customerBySupportRepIdRelation = Relation[Customer, Option[Long]]("bySupportRepId", x => Seq(x.supportRepId))
  val customerBySupportRepIdFetcher = Fetcher.rel[GraphQLContext, Customer, Customer, Long](
    getByPrimaryKeySeq, (c, rels) => c.services.commerceServices.customerService.getBySupportRepIdSeq(c.creds, rels(customerBySupportRepIdRelation).flatten)(c.trace)
  )

  implicit lazy val customerType: sangria.schema.ObjectType[GraphQLContext, Customer] = deriveObjectType(
    sangria.macros.derive.AddFields(
      Field(
        name = "invoices",
        fieldType = ListType(InvoiceSchema.invoiceType),
        resolve = c => InvoiceSchema.invoiceByCustomerIdFetcher.deferRelSeq(
          InvoiceSchema.invoiceByCustomerIdRelation, c.value.customerId
        )
      ),
      Field(
        name = "supportRepIdRel",
        fieldType = OptionType(EmployeeSchema.employeeType),
        resolve = ctx => EmployeeSchema.employeeByPrimaryKeyFetcher.deferOpt(ctx.value.supportRepId)
      ),
      Field(
        name = "relatedNotes",
        fieldType = ListType(NoteSchema.noteType),
        resolve = c => c.ctx.app.coreServices.notes.getFor(c.ctx.creds, "customer", c.value.customerId)(c.ctx.trace)
      )
    )
  )

  implicit lazy val customerResultType: sangria.schema.ObjectType[GraphQLContext, CustomerResult] = deriveObjectType()

  val queryFields = fields(
    unitField(name = "customer", desc = None, t = OptionType(customerType), f = (c, td) => {
      c.ctx.services.commerceServices.customerService.getByPrimaryKey(c.ctx.creds, c.arg(customerCustomerIdArg))(td)
    }, customerCustomerIdArg),
    unitField(name = "customerSeq", desc = None, t = ListType(customerType), f = (c, td) => {
      c.ctx.services.commerceServices.customerService.getByPrimaryKeySeq(c.ctx.creds, c.arg(customerCustomerIdSeqArg))(td)
    }, customerCustomerIdSeqArg),
    unitField(name = "customerSearch", desc = None, t = customerResultType, f = (c, td) => {
      runSearch(c.ctx.services.commerceServices.customerService, c, td).map(toResult)
    }, queryArg, reportFiltersArg, orderBysArg, limitArg, offsetArg),
    unitField(name = "customerByCustomerId", desc = None, t = OptionType(customerType), f = (c, td) => {
      c.ctx.services.commerceServices.customerService.getByCustomerId(c.ctx.creds, c.arg(customerCustomerIdArg))(td).map(_.headOption)
    }, customerCustomerIdArg),
    unitField(name = "customerByCustomerIdSeq", desc = None, t = ListType(customerType), f = (c, td) => {
      c.ctx.services.commerceServices.customerService.getByCustomerIdSeq(c.ctx.creds, c.arg(customerCustomerIdSeqArg))(td)
    }, customerCustomerIdSeqArg),
    unitField(name = "customersBySupportRepId", desc = None, t = ListType(customerType), f = (c, td) => {
      c.ctx.services.commerceServices.customerService.getBySupportRepId(c.ctx.creds, c.arg(customerSupportRepIdArg).getOrElse(throw new IllegalStateException("No [supportRepId] provided")))(td)
    }, customerSupportRepIdArg),
    unitField(name = "customersBySupportRepIdSeq", desc = None, t = ListType(customerType), f = (c, td) => {
      c.ctx.services.commerceServices.customerService.getBySupportRepIdSeq(c.ctx.creds, c.arg(customerSupportRepIdSeqArg))(td)
    }, customerSupportRepIdSeqArg)
  )

  val customerMutationType = ObjectType(
    name = "CustomerMutations",
    fields = fields(
      unitField(name = "create", desc = None, t = OptionType(customerType), f = (c, td) => {
        c.ctx.services.commerceServices.customerService.create(c.ctx.creds, c.arg(dataFieldsArg))(td)
      }, dataFieldsArg),
      unitField(name = "update", desc = None, t = OptionType(customerType), f = (c, td) => {
        c.ctx.services.commerceServices.customerService.update(c.ctx.creds, c.arg(customerCustomerIdArg), c.arg(dataFieldsArg))(td).map(_._1)
      }, customerCustomerIdArg, dataFieldsArg),
      unitField(name = "remove", desc = None, t = customerType, f = (c, td) => {
        c.ctx.services.commerceServices.customerService.remove(c.ctx.creds, c.arg(customerCustomerIdArg))(td)
      }, customerCustomerIdArg)
    )
  )

  val mutationFields = fields(unitField(name = "customer", desc = None, t = customerMutationType, f = (_, _) => scala.concurrent.Future.successful(())))

  private[this] def toResult(r: GraphQLSchemaHelper.SearchResult[Customer]) = {
    CustomerResult(paging = r.paging, filters = r.args.filters, orderBys = r.args.orderBys, totalCount = r.count, results = r.results, durationMs = r.dur)
  }
}
