/* Generated File */
package models.media

import graphql.{GraphQLContext, GraphQLSchemaHelper}
import graphql.GraphQLUtils._
import models.note.NoteSchema
import sangria.execution.deferred.{Fetcher, HasId}
import sangria.schema._

object ArtistSchema extends GraphQLSchemaHelper("artist") {
  implicit val artistPrimaryKeyId: HasId[Artist, Long] = HasId[Artist, Long](_.artistId)
  private[this] def getByPrimaryKeySeq(c: GraphQLContext, idSeq: Seq[Long]) = {
    c.services.mediaServices.artistService.getByPrimaryKeySeq(c.creds, idSeq)(c.trace)
  }
  val artistByPrimaryKeyFetcher = Fetcher(getByPrimaryKeySeq)

  val artistArtistIdArg = Argument("artistId", LongType)
  val artistArtistIdSeqArg = Argument("artistIds", ListInputType(LongType))

  implicit lazy val artistType: sangria.schema.ObjectType[GraphQLContext, Artist] = deriveObjectType(
    sangria.macros.derive.AddFields(
      Field(
        name = "albums",
        fieldType = ListType(AlbumSchema.albumType),
        resolve = c => AlbumSchema.albumByArtistIdFetcher.deferRelSeq(
          AlbumSchema.albumByArtistIdRelation, c.value.artistId
        )
      ),
      Field(
        name = "relatedNotes",
        fieldType = ListType(NoteSchema.noteType),
        resolve = c => c.ctx.app.coreServices.notes.getFor(c.ctx.creds, "artist", c.value.artistId)(c.ctx.trace)
      )
    )
  )

  implicit lazy val artistResultType: sangria.schema.ObjectType[GraphQLContext, ArtistResult] = deriveObjectType()

  val queryFields = fields(
    unitField(name = "artist", desc = None, t = OptionType(artistType), f = (c, td) => {
      c.ctx.services.mediaServices.artistService.getByPrimaryKey(c.ctx.creds, c.arg(artistArtistIdArg))(td)
    }, artistArtistIdArg),
    unitField(name = "artistSeq", desc = None, t = ListType(artistType), f = (c, td) => {
      c.ctx.services.mediaServices.artistService.getByPrimaryKeySeq(c.ctx.creds, c.arg(artistArtistIdSeqArg))(td)
    }, artistArtistIdSeqArg),
    unitField(name = "artistSearch", desc = None, t = artistResultType, f = (c, td) => {
      runSearch(c.ctx.services.mediaServices.artistService, c, td).map(toResult)
    }, queryArg, reportFiltersArg, orderBysArg, limitArg, offsetArg),
    unitField(name = "artistByArtistId", desc = None, t = OptionType(artistType), f = (c, td) => {
      c.ctx.services.mediaServices.artistService.getByArtistId(c.ctx.creds, c.arg(artistArtistIdArg))(td).map(_.headOption)
    }, artistArtistIdArg),
    unitField(name = "artistByArtistIdSeq", desc = None, t = ListType(artistType), f = (c, td) => {
      c.ctx.services.mediaServices.artistService.getByArtistIdSeq(c.ctx.creds, c.arg(artistArtistIdSeqArg))(td)
    }, artistArtistIdSeqArg)
  )

  val artistMutationType = ObjectType(
    name = "ArtistMutations",
    fields = fields(
      unitField(name = "create", desc = None, t = OptionType(artistType), f = (c, td) => {
        c.ctx.services.mediaServices.artistService.create(c.ctx.creds, c.arg(dataFieldsArg))(td)
      }, dataFieldsArg),
      unitField(name = "update", desc = None, t = OptionType(artistType), f = (c, td) => {
        c.ctx.services.mediaServices.artistService.update(c.ctx.creds, c.arg(artistArtistIdArg), c.arg(dataFieldsArg))(td).map(_._1)
      }, artistArtistIdArg, dataFieldsArg),
      unitField(name = "remove", desc = None, t = artistType, f = (c, td) => {
        c.ctx.services.mediaServices.artistService.remove(c.ctx.creds, c.arg(artistArtistIdArg))(td)
      }, artistArtistIdArg)
    )
  )

  val mutationFields = fields(unitField(name = "artist", desc = None, t = artistMutationType, f = (_, _) => scala.concurrent.Future.successful(())))

  private[this] def toResult(r: GraphQLSchemaHelper.SearchResult[Artist]) = {
    ArtistResult(paging = r.paging, filters = r.args.filters, orderBys = r.args.orderBys, totalCount = r.count, results = r.results, durationMs = r.dur)
  }
}
