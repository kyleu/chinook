/* Generated File */
package models.media

import graphql.{GraphQLContext, GraphQLSchemaHelper}
import graphql.GraphQLUtils._
import models.note.NoteSchema
import sangria.execution.deferred.{Fetcher, HasId}
import sangria.schema._

object MediaTypeSchema extends GraphQLSchemaHelper("mediaType") {
  implicit val mediaTypePrimaryKeyId: HasId[MediaType, Long] = HasId[MediaType, Long](_.mediaTypeId)
  private[this] def getByPrimaryKeySeq(c: GraphQLContext, idSeq: Seq[Long]) = {
    c.services.mediaServices.mediaTypeService.getByPrimaryKeySeq(c.creds, idSeq)(c.trace)
  }
  val mediaTypeByPrimaryKeyFetcher = Fetcher(getByPrimaryKeySeq)

  val mediaTypeMediaTypeIdArg = Argument("mediaTypeId", LongType)
  val mediaTypeMediaTypeIdSeqArg = Argument("mediaTypeIds", ListInputType(LongType))

  implicit lazy val mediaTypeType: sangria.schema.ObjectType[GraphQLContext, MediaType] = deriveObjectType(
    sangria.macros.derive.AddFields(
      Field(
        name = "tracks",
        fieldType = ListType(TrackSchema.trackType),
        resolve = c => TrackSchema.trackByMediaTypeIdFetcher.deferRelSeq(
          TrackSchema.trackByMediaTypeIdRelation, c.value.mediaTypeId
        )
      ),
      Field(
        name = "relatedNotes",
        fieldType = ListType(NoteSchema.noteType),
        resolve = c => c.ctx.app.coreServices.notes.getFor(c.ctx.creds, "mediaType", c.value.mediaTypeId)(c.ctx.trace)
      )
    )
  )

  implicit lazy val mediaTypeResultType: sangria.schema.ObjectType[GraphQLContext, MediaTypeResult] = deriveObjectType()

  val queryFields = fields(
    unitField(name = "mediaType", desc = None, t = OptionType(mediaTypeType), f = (c, td) => {
      c.ctx.services.mediaServices.mediaTypeService.getByPrimaryKey(c.ctx.creds, c.arg(mediaTypeMediaTypeIdArg))(td)
    }, mediaTypeMediaTypeIdArg),
    unitField(name = "mediaTypeSeq", desc = None, t = ListType(mediaTypeType), f = (c, td) => {
      c.ctx.services.mediaServices.mediaTypeService.getByPrimaryKeySeq(c.ctx.creds, c.arg(mediaTypeMediaTypeIdSeqArg))(td)
    }, mediaTypeMediaTypeIdSeqArg),
    unitField(name = "mediaTypeSearch", desc = None, t = mediaTypeResultType, f = (c, td) => {
      runSearch(c.ctx.services.mediaServices.mediaTypeService, c, td).map(toResult)
    }, queryArg, reportFiltersArg, orderBysArg, limitArg, offsetArg),
    unitField(name = "mediaTypeByMediaTypeId", desc = None, t = OptionType(mediaTypeType), f = (c, td) => {
      c.ctx.services.mediaServices.mediaTypeService.getByMediaTypeId(c.ctx.creds, c.arg(mediaTypeMediaTypeIdArg))(td).map(_.headOption)
    }, mediaTypeMediaTypeIdArg),
    unitField(name = "mediaTypeByMediaTypeIdSeq", desc = None, t = ListType(mediaTypeType), f = (c, td) => {
      c.ctx.services.mediaServices.mediaTypeService.getByMediaTypeIdSeq(c.ctx.creds, c.arg(mediaTypeMediaTypeIdSeqArg))(td)
    }, mediaTypeMediaTypeIdSeqArg)
  )

  val mediaTypeMutationType = ObjectType(
    name = "MediaTypeMutations",
    fields = fields(
      unitField(name = "create", desc = None, t = OptionType(mediaTypeType), f = (c, td) => {
        c.ctx.services.mediaServices.mediaTypeService.create(c.ctx.creds, c.arg(dataFieldsArg))(td)
      }, dataFieldsArg),
      unitField(name = "update", desc = None, t = OptionType(mediaTypeType), f = (c, td) => {
        c.ctx.services.mediaServices.mediaTypeService.update(c.ctx.creds, c.arg(mediaTypeMediaTypeIdArg), c.arg(dataFieldsArg))(td).map(_._1)
      }, mediaTypeMediaTypeIdArg, dataFieldsArg),
      unitField(name = "remove", desc = None, t = mediaTypeType, f = (c, td) => {
        c.ctx.services.mediaServices.mediaTypeService.remove(c.ctx.creds, c.arg(mediaTypeMediaTypeIdArg))(td)
      }, mediaTypeMediaTypeIdArg)
    )
  )

  val mutationFields = fields(unitField(name = "mediaType", desc = None, t = mediaTypeMutationType, f = (_, _) => scala.concurrent.Future.successful(())))

  private[this] def toResult(r: GraphQLSchemaHelper.SearchResult[MediaType]) = {
    MediaTypeResult(paging = r.paging, filters = r.args.filters, orderBys = r.args.orderBys, totalCount = r.count, results = r.results, durationMs = r.dur)
  }
}
