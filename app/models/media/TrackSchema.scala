/* Generated File */
package models.media

import graphql.{GraphQLContext, GraphQLSchemaHelper}
import graphql.GraphQLUtils._
import models.commerce.InvoiceLineSchema
import models.note.NoteSchema
import sangria.execution.deferred.{Fetcher, HasId, Relation}
import sangria.schema._

object TrackSchema extends GraphQLSchemaHelper("track") {
  implicit val trackPrimaryKeyId: HasId[Track, Long] = HasId[Track, Long](_.trackId)
  private[this] def getByPrimaryKeySeq(c: GraphQLContext, idSeq: Seq[Long]) = {
    c.services.mediaServices.trackService.getByPrimaryKeySeq(c.creds, idSeq)(c.trace)
  }
  val trackByPrimaryKeyFetcher = Fetcher(getByPrimaryKeySeq)

  val trackTrackIdArg = Argument("trackId", LongType)
  val trackTrackIdSeqArg = Argument("trackIds", ListInputType(LongType))

  val trackAlbumIdArg = Argument("albumId", OptionInputType(LongType))
  val trackAlbumIdSeqArg = Argument("albumIds", ListInputType(LongType))
  val trackMediaTypeIdArg = Argument("mediaTypeId", LongType)
  val trackMediaTypeIdSeqArg = Argument("mediaTypeIds", ListInputType(LongType))
  val trackGenreIdArg = Argument("genreId", OptionInputType(LongType))
  val trackGenreIdSeqArg = Argument("genreIds", ListInputType(LongType))

  val trackByAlbumIdRelation = Relation[Track, Option[Long]]("byAlbumId", x => Seq(x.albumId))
  val trackByAlbumIdFetcher = Fetcher.rel[GraphQLContext, Track, Track, Long](
    getByPrimaryKeySeq, (c, rels) => c.services.mediaServices.trackService.getByAlbumIdSeq(c.creds, rels(trackByAlbumIdRelation).flatten)(c.trace)
  )

  val trackByMediaTypeIdRelation = Relation[Track, Long]("byMediaTypeId", x => Seq(x.mediaTypeId))
  val trackByMediaTypeIdFetcher = Fetcher.rel[GraphQLContext, Track, Track, Long](
    getByPrimaryKeySeq, (c, rels) => c.services.mediaServices.trackService.getByMediaTypeIdSeq(c.creds, rels(trackByMediaTypeIdRelation))(c.trace)
  )

  val trackByGenreIdRelation = Relation[Track, Option[Long]]("byGenreId", x => Seq(x.genreId))
  val trackByGenreIdFetcher = Fetcher.rel[GraphQLContext, Track, Track, Long](
    getByPrimaryKeySeq, (c, rels) => c.services.mediaServices.trackService.getByGenreIdSeq(c.creds, rels(trackByGenreIdRelation).flatten)(c.trace)
  )

  implicit lazy val trackType: sangria.schema.ObjectType[GraphQLContext, Track] = deriveObjectType(
    sangria.macros.derive.AddFields(
      Field(
        name = "invoiceLines",
        fieldType = ListType(InvoiceLineSchema.invoiceLineType),
        resolve = c => InvoiceLineSchema.invoiceLineByTrackIdFetcher.deferRelSeq(
          InvoiceLineSchema.invoiceLineByTrackIdRelation, c.value.trackId
        )
      ),
      Field(
        name = "playlistTracks",
        fieldType = ListType(PlaylistTrackSchema.playlistTrackType),
        resolve = c => PlaylistTrackSchema.playlistTrackByTrackIdFetcher.deferRelSeq(
          PlaylistTrackSchema.playlistTrackByTrackIdRelation, c.value.trackId
        )
      ),
      Field(
        name = "albumIdRel",
        fieldType = OptionType(AlbumSchema.albumType),
        resolve = ctx => AlbumSchema.albumByPrimaryKeyFetcher.deferOpt(ctx.value.albumId)
      ),
      Field(
        name = "mediaTypeIdRel",
        fieldType = MediaTypeSchema.mediaTypeType,
        resolve = ctx => MediaTypeSchema.mediaTypeByPrimaryKeyFetcher.defer(ctx.value.mediaTypeId)
      ),
      Field(
        name = "genreIdRel",
        fieldType = OptionType(GenreSchema.genreType),
        resolve = ctx => GenreSchema.genreByPrimaryKeyFetcher.deferOpt(ctx.value.genreId)
      ),
      Field(
        name = "relatedNotes",
        fieldType = ListType(NoteSchema.noteType),
        resolve = c => c.ctx.app.coreServices.notes.getFor(c.ctx.creds, "track", c.value.trackId)(c.ctx.trace)
      )
    )
  )

  implicit lazy val trackResultType: sangria.schema.ObjectType[GraphQLContext, TrackResult] = deriveObjectType()

  val queryFields = fields(
    unitField(name = "track", desc = None, t = OptionType(trackType), f = (c, td) => {
      c.ctx.services.mediaServices.trackService.getByPrimaryKey(c.ctx.creds, c.arg(trackTrackIdArg))(td)
    }, trackTrackIdArg),
    unitField(name = "trackSeq", desc = None, t = ListType(trackType), f = (c, td) => {
      c.ctx.services.mediaServices.trackService.getByPrimaryKeySeq(c.ctx.creds, c.arg(trackTrackIdSeqArg))(td)
    }, trackTrackIdSeqArg),
    unitField(name = "trackSearch", desc = None, t = trackResultType, f = (c, td) => {
      runSearch(c.ctx.services.mediaServices.trackService, c, td).map(toResult)
    }, queryArg, reportFiltersArg, orderBysArg, limitArg, offsetArg),
    unitField(name = "trackByTrackId", desc = None, t = OptionType(trackType), f = (c, td) => {
      c.ctx.services.mediaServices.trackService.getByTrackId(c.ctx.creds, c.arg(trackTrackIdArg))(td).map(_.headOption)
    }, trackTrackIdArg),
    unitField(name = "trackByTrackIdSeq", desc = None, t = ListType(trackType), f = (c, td) => {
      c.ctx.services.mediaServices.trackService.getByTrackIdSeq(c.ctx.creds, c.arg(trackTrackIdSeqArg))(td)
    }, trackTrackIdSeqArg),
    unitField(name = "tracksByAlbumId", desc = None, t = ListType(trackType), f = (c, td) => {
      c.ctx.services.mediaServices.trackService.getByAlbumId(c.ctx.creds, c.arg(trackAlbumIdArg).getOrElse(throw new IllegalStateException("No [albumId] provided")))(td)
    }, trackAlbumIdArg),
    unitField(name = "tracksByAlbumIdSeq", desc = None, t = ListType(trackType), f = (c, td) => {
      c.ctx.services.mediaServices.trackService.getByAlbumIdSeq(c.ctx.creds, c.arg(trackAlbumIdSeqArg))(td)
    }, trackAlbumIdSeqArg),
    unitField(name = "tracksByMediaTypeId", desc = None, t = ListType(trackType), f = (c, td) => {
      c.ctx.services.mediaServices.trackService.getByMediaTypeId(c.ctx.creds, c.arg(trackMediaTypeIdArg))(td)
    }, trackMediaTypeIdArg),
    unitField(name = "tracksByMediaTypeIdSeq", desc = None, t = ListType(trackType), f = (c, td) => {
      c.ctx.services.mediaServices.trackService.getByMediaTypeIdSeq(c.ctx.creds, c.arg(trackMediaTypeIdSeqArg))(td)
    }, trackMediaTypeIdSeqArg),
    unitField(name = "tracksByGenreId", desc = None, t = ListType(trackType), f = (c, td) => {
      c.ctx.services.mediaServices.trackService.getByGenreId(c.ctx.creds, c.arg(trackGenreIdArg).getOrElse(throw new IllegalStateException("No [genreId] provided")))(td)
    }, trackGenreIdArg),
    unitField(name = "tracksByGenreIdSeq", desc = None, t = ListType(trackType), f = (c, td) => {
      c.ctx.services.mediaServices.trackService.getByGenreIdSeq(c.ctx.creds, c.arg(trackGenreIdSeqArg))(td)
    }, trackGenreIdSeqArg)
  )

  val trackMutationType = ObjectType(
    name = "TrackMutations",
    fields = fields(
      unitField(name = "create", desc = None, t = OptionType(trackType), f = (c, td) => {
        c.ctx.services.mediaServices.trackService.create(c.ctx.creds, c.arg(dataFieldsArg))(td)
      }, dataFieldsArg),
      unitField(name = "update", desc = None, t = OptionType(trackType), f = (c, td) => {
        c.ctx.services.mediaServices.trackService.update(c.ctx.creds, c.arg(trackTrackIdArg), c.arg(dataFieldsArg))(td).map(_._1)
      }, trackTrackIdArg, dataFieldsArg),
      unitField(name = "remove", desc = None, t = trackType, f = (c, td) => {
        c.ctx.services.mediaServices.trackService.remove(c.ctx.creds, c.arg(trackTrackIdArg))(td)
      }, trackTrackIdArg)
    )
  )

  val mutationFields = fields(unitField(name = "track", desc = None, t = trackMutationType, f = (_, _) => scala.concurrent.Future.successful(())))

  private[this] def toResult(r: GraphQLSchemaHelper.SearchResult[Track]) = {
    TrackResult(paging = r.paging, filters = r.args.filters, orderBys = r.args.orderBys, totalCount = r.count, results = r.results, durationMs = r.dur)
  }
}
