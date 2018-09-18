/* Generated File */
package models.commerce

import graphql.{GraphQLContext, GraphQLSchemaHelper}
import graphql.GraphQLUtils._
import models.note.NoteSchema
import sangria.execution.deferred.{Fetcher, HasId, Relation}
import sangria.schema._

object EmployeeSchema extends GraphQLSchemaHelper("employee") {
  implicit val employeePrimaryKeyId: HasId[Employee, Long] = HasId[Employee, Long](_.employeeId)
  private[this] def getByPrimaryKeySeq(c: GraphQLContext, idSeq: Seq[Long]) = {
    c.services.commerceServices.employeeService.getByPrimaryKeySeq(c.creds, idSeq)(c.trace)
  }
  val employeeByPrimaryKeyFetcher = Fetcher(getByPrimaryKeySeq)

  val employeeEmployeeIdArg = Argument("employeeId", LongType)
  val employeeEmployeeIdSeqArg = Argument("employeeIds", ListInputType(LongType))

  val employeeReportsToArg = Argument("reportsTo", OptionInputType(LongType))
  val employeeReportsToSeqArg = Argument("reportsTos", ListInputType(LongType))

  val employeeByReportsToRelation = Relation[Employee, Option[Long]]("byReportsTo", x => Seq(x.reportsTo))
  val employeeByReportsToFetcher = Fetcher.rel[GraphQLContext, Employee, Employee, Long](
    getByPrimaryKeySeq, (c, rels) => c.services.commerceServices.employeeService.getByReportsToSeq(c.creds, rels(employeeByReportsToRelation).flatten)(c.trace)
  )

  implicit lazy val employeeType: sangria.schema.ObjectType[GraphQLContext, Employee] = deriveObjectType(
    sangria.macros.derive.AddFields(
      Field(
        name = "customerSupportReps",
        fieldType = ListType(CustomerSchema.customerType),
        resolve = c => CustomerSchema.customerBySupportRepIdFetcher.deferRelSeq(
          CustomerSchema.customerBySupportRepIdRelation, Some(c.value.employeeId)
        )
      ),
      Field(
        name = "reportsToRel",
        fieldType = OptionType(EmployeeSchema.employeeType),
        resolve = ctx => EmployeeSchema.employeeByPrimaryKeyFetcher.deferOpt(ctx.value.reportsTo)
      ),
      Field(
        name = "relatedNotes",
        fieldType = ListType(NoteSchema.noteType),
        resolve = c => c.ctx.app.coreServices.notes.getFor(c.ctx.creds, "employee", c.value.employeeId)(c.ctx.trace)
      )
    )
  )

  implicit lazy val employeeResultType: sangria.schema.ObjectType[GraphQLContext, EmployeeResult] = deriveObjectType()

  val queryFields = fields(
    unitField(name = "employee", desc = None, t = OptionType(employeeType), f = (c, td) => {
      c.ctx.services.commerceServices.employeeService.getByPrimaryKey(c.ctx.creds, c.arg(employeeEmployeeIdArg))(td)
    }, employeeEmployeeIdArg),
    unitField(name = "employeeSeq", desc = None, t = ListType(employeeType), f = (c, td) => {
      c.ctx.services.commerceServices.employeeService.getByPrimaryKeySeq(c.ctx.creds, c.arg(employeeEmployeeIdSeqArg))(td)
    }, employeeEmployeeIdSeqArg),
    unitField(name = "employeeSearch", desc = None, t = employeeResultType, f = (c, td) => {
      runSearch(c.ctx.services.commerceServices.employeeService, c, td).map(toResult)
    }, queryArg, reportFiltersArg, orderBysArg, limitArg, offsetArg),
    unitField(name = "employeeByEmployeeId", desc = None, t = OptionType(employeeType), f = (c, td) => {
      c.ctx.services.commerceServices.employeeService.getByEmployeeId(c.ctx.creds, c.arg(employeeEmployeeIdArg))(td).map(_.headOption)
    }, employeeEmployeeIdArg),
    unitField(name = "employeeByEmployeeIdSeq", desc = None, t = ListType(employeeType), f = (c, td) => {
      c.ctx.services.commerceServices.employeeService.getByEmployeeIdSeq(c.ctx.creds, c.arg(employeeEmployeeIdSeqArg))(td)
    }, employeeEmployeeIdSeqArg),
    unitField(name = "employeesByReportsTo", desc = None, t = ListType(employeeType), f = (c, td) => {
      c.ctx.services.commerceServices.employeeService.getByReportsTo(c.ctx.creds, c.arg(employeeReportsToArg).getOrElse(throw new IllegalStateException("No [reportsTo] provided")))(td)
    }, employeeReportsToArg),
    unitField(name = "employeesByReportsToSeq", desc = None, t = ListType(employeeType), f = (c, td) => {
      c.ctx.services.commerceServices.employeeService.getByReportsToSeq(c.ctx.creds, c.arg(employeeReportsToSeqArg))(td)
    }, employeeReportsToSeqArg)
  )

  val employeeMutationType = ObjectType(
    name = "EmployeeMutations",
    fields = fields(
      unitField(name = "create", desc = None, t = OptionType(employeeType), f = (c, td) => {
        c.ctx.services.commerceServices.employeeService.create(c.ctx.creds, c.arg(dataFieldsArg))(td)
      }, dataFieldsArg),
      unitField(name = "update", desc = None, t = OptionType(employeeType), f = (c, td) => {
        c.ctx.services.commerceServices.employeeService.update(c.ctx.creds, c.arg(employeeEmployeeIdArg), c.arg(dataFieldsArg))(td).map(_._1)
      }, employeeEmployeeIdArg, dataFieldsArg),
      unitField(name = "remove", desc = None, t = employeeType, f = (c, td) => {
        c.ctx.services.commerceServices.employeeService.remove(c.ctx.creds, c.arg(employeeEmployeeIdArg))(td)
      }, employeeEmployeeIdArg)
    )
  )

  val mutationFields = fields(unitField(name = "employee", desc = None, t = employeeMutationType, f = (_, _) => scala.concurrent.Future.successful(())))

  private[this] def toResult(r: GraphQLSchemaHelper.SearchResult[Employee]) = {
    EmployeeResult(paging = r.paging, filters = r.args.filters, orderBys = r.args.orderBys, totalCount = r.count, results = r.results, durationMs = r.dur)
  }
}
