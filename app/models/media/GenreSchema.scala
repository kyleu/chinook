/* Generated File */
package models.media

import graphql.{GraphQLContext, GraphQLSchemaHelper}
import graphql.GraphQLUtils._
import models.note.NoteSchema
import sangria.execution.deferred.{Fetcher, HasId}
import sangria.schema._

object GenreSchema extends GraphQLSchemaHelper("genre") {
  implicit val genrePrimaryKeyId: HasId[Genre, Long] = HasId[Genre, Long](_.genreId)
  private[this] def getByPrimaryKeySeq(c: GraphQLContext, idSeq: Seq[Long]) = {
    c.services.mediaServices.genreService.getByPrimaryKeySeq(c.creds, idSeq)(c.trace)
  }
  val genreByPrimaryKeyFetcher = Fetcher(getByPrimaryKeySeq)

  val genreGenreIdArg = Argument("genreId", LongType)
  val genreGenreIdSeqArg = Argument("genreIds", ListInputType(LongType))

  implicit lazy val genreType: sangria.schema.ObjectType[GraphQLContext, Genre] = deriveObjectType(
    sangria.macros.derive.AddFields(
      Field(
        name = "tracks",
        fieldType = ListType(TrackSchema.trackType),
        resolve = c => TrackSchema.trackByGenreIdFetcher.deferRelSeq(
          TrackSchema.trackByGenreIdRelation, Some(c.value.genreId)
        )
      ),
      Field(
        name = "relatedNotes",
        fieldType = ListType(NoteSchema.noteType),
        resolve = c => c.ctx.app.coreServices.notes.getFor(c.ctx.creds, "genre", c.value.genreId)(c.ctx.trace)
      )
    )
  )

  implicit lazy val genreResultType: sangria.schema.ObjectType[GraphQLContext, GenreResult] = deriveObjectType()

  val queryFields = fields(
    unitField(name = "genre", desc = None, t = OptionType(genreType), f = (c, td) => {
      c.ctx.services.mediaServices.genreService.getByPrimaryKey(c.ctx.creds, c.arg(genreGenreIdArg))(td)
    }, genreGenreIdArg),
    unitField(name = "genreSeq", desc = None, t = ListType(genreType), f = (c, td) => {
      c.ctx.services.mediaServices.genreService.getByPrimaryKeySeq(c.ctx.creds, c.arg(genreGenreIdSeqArg))(td)
    }, genreGenreIdSeqArg),
    unitField(name = "genreSearch", desc = None, t = genreResultType, f = (c, td) => {
      runSearch(c.ctx.services.mediaServices.genreService, c, td).map(toResult)
    }, queryArg, reportFiltersArg, orderBysArg, limitArg, offsetArg),
    unitField(name = "genreByGenreId", desc = None, t = OptionType(genreType), f = (c, td) => {
      c.ctx.services.mediaServices.genreService.getByGenreId(c.ctx.creds, c.arg(genreGenreIdArg))(td).map(_.headOption)
    }, genreGenreIdArg),
    unitField(name = "genreByGenreIdSeq", desc = None, t = ListType(genreType), f = (c, td) => {
      c.ctx.services.mediaServices.genreService.getByGenreIdSeq(c.ctx.creds, c.arg(genreGenreIdSeqArg))(td)
    }, genreGenreIdSeqArg)
  )

  val genreMutationType = ObjectType(
    name = "GenreMutations",
    fields = fields(
      unitField(name = "create", desc = None, t = OptionType(genreType), f = (c, td) => {
        c.ctx.services.mediaServices.genreService.create(c.ctx.creds, c.arg(dataFieldsArg))(td)
      }, dataFieldsArg),
      unitField(name = "update", desc = None, t = OptionType(genreType), f = (c, td) => {
        c.ctx.services.mediaServices.genreService.update(c.ctx.creds, c.arg(genreGenreIdArg), c.arg(dataFieldsArg))(td).map(_._1)
      }, genreGenreIdArg, dataFieldsArg),
      unitField(name = "remove", desc = None, t = genreType, f = (c, td) => {
        c.ctx.services.mediaServices.genreService.remove(c.ctx.creds, c.arg(genreGenreIdArg))(td)
      }, genreGenreIdArg)
    )
  )

  val mutationFields = fields(unitField(name = "genre", desc = None, t = genreMutationType, f = (_, _) => scala.concurrent.Future.successful(())))

  private[this] def toResult(r: GraphQLSchemaHelper.SearchResult[Genre]) = {
    GenreResult(paging = r.paging, filters = r.args.filters, orderBys = r.args.orderBys, totalCount = r.count, results = r.results, durationMs = r.dur)
  }
}
