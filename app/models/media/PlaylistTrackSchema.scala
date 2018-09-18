/* Generated File */
package models.media

import graphql.{GraphQLContext, GraphQLSchemaHelper}
import graphql.GraphQLUtils._
import models.note.NoteSchema
import sangria.execution.deferred.{Fetcher, HasId, Relation}
import sangria.schema._

object PlaylistTrackSchema extends GraphQLSchemaHelper("playlistTrack") {
  implicit val playlistTrackPrimaryKeyId: HasId[PlaylistTrack, (Long, Long)] = HasId[PlaylistTrack, (Long, Long)](x => (x.playlistId, x.trackId))
  private[this] def getByPrimaryKeySeq(c: GraphQLContext, idSeq: Seq[(Long, Long)]) = {
    c.services.mediaServices.playlistTrackService.getByPrimaryKeySeq(c.creds, idSeq)(c.trace)
  }
  val playlistTrackByPrimaryKeyFetcher = Fetcher(getByPrimaryKeySeq)

  val playlistTrackPlaylistIdArg = Argument("playlistId", LongType)
  val playlistTrackPlaylistIdSeqArg = Argument("playlistIds", ListInputType(LongType))
  val playlistTrackTrackIdArg = Argument("trackId", LongType)
  val playlistTrackTrackIdSeqArg = Argument("trackIds", ListInputType(LongType))

  val playlistTrackByTrackIdRelation = Relation[PlaylistTrack, Long]("byTrackId", x => Seq(x.trackId))
  val playlistTrackByTrackIdFetcher = Fetcher.rel[GraphQLContext, PlaylistTrack, PlaylistTrack, (Long, Long)](
    getByPrimaryKeySeq, (c, rels) => c.services.mediaServices.playlistTrackService.getByTrackIdSeq(c.creds, rels(playlistTrackByTrackIdRelation))(c.trace)
  )

  val playlistTrackByPlaylistIdRelation = Relation[PlaylistTrack, Long]("byPlaylistId", x => Seq(x.playlistId))
  val playlistTrackByPlaylistIdFetcher = Fetcher.rel[GraphQLContext, PlaylistTrack, PlaylistTrack, (Long, Long)](
    getByPrimaryKeySeq, (c, rels) => c.services.mediaServices.playlistTrackService.getByPlaylistIdSeq(c.creds, rels(playlistTrackByPlaylistIdRelation))(c.trace)
  )

  implicit lazy val playlistTrackType: sangria.schema.ObjectType[GraphQLContext, PlaylistTrack] = deriveObjectType(
    sangria.macros.derive.AddFields(
      Field(
        name = "trackIdRel",
        fieldType = TrackSchema.trackType,
        resolve = ctx => TrackSchema.trackByPrimaryKeyFetcher.defer(ctx.value.trackId)
      ),
      Field(
        name = "playlistIdRel",
        fieldType = PlaylistSchema.playlistType,
        resolve = ctx => PlaylistSchema.playlistByPrimaryKeyFetcher.defer(ctx.value.playlistId)
      ),
      Field(
        name = "relatedNotes",
        fieldType = ListType(NoteSchema.noteType),
        resolve = c => c.ctx.app.coreServices.notes.getFor(c.ctx.creds, "playlistTrack", c.value.playlistId, c.value.trackId)(c.ctx.trace)
      )
    )
  )

  implicit lazy val playlistTrackResultType: sangria.schema.ObjectType[GraphQLContext, PlaylistTrackResult] = deriveObjectType()

  val queryFields = fields(
    unitField(name = "playlistTrack", desc = None, t = OptionType(playlistTrackType), f = (c, td) => {
      c.ctx.services.mediaServices.playlistTrackService.getByPrimaryKey(c.ctx.creds, c.arg(playlistTrackPlaylistIdArg), c.arg(playlistTrackTrackIdArg))(td)
    }, playlistTrackPlaylistIdArg, playlistTrackTrackIdArg),
    unitField(name = "playlistTrackSearch", desc = None, t = playlistTrackResultType, f = (c, td) => {
      runSearch(c.ctx.services.mediaServices.playlistTrackService, c, td).map(toResult)
    }, queryArg, reportFiltersArg, orderBysArg, limitArg, offsetArg),
    unitField(name = "playlistTracksByTrackId", desc = None, t = ListType(playlistTrackType), f = (c, td) => {
      c.ctx.services.mediaServices.playlistTrackService.getByTrackId(c.ctx.creds, c.arg(playlistTrackTrackIdArg))(td)
    }, playlistTrackTrackIdArg),
    unitField(name = "playlistTracksByTrackIdSeq", desc = None, t = ListType(playlistTrackType), f = (c, td) => {
      c.ctx.services.mediaServices.playlistTrackService.getByTrackIdSeq(c.ctx.creds, c.arg(playlistTrackTrackIdSeqArg))(td)
    }, playlistTrackTrackIdSeqArg)
  )

  val playlistTrackMutationType = ObjectType(
    name = "PlaylistTrackMutations",
    fields = fields(
      unitField(name = "create", desc = None, t = OptionType(playlistTrackType), f = (c, td) => {
        c.ctx.services.mediaServices.playlistTrackService.create(c.ctx.creds, c.arg(dataFieldsArg))(td)
      }, dataFieldsArg),
      unitField(name = "update", desc = None, t = OptionType(playlistTrackType), f = (c, td) => {
        c.ctx.services.mediaServices.playlistTrackService.update(c.ctx.creds, c.arg(playlistTrackPlaylistIdArg), c.arg(playlistTrackTrackIdArg), c.arg(dataFieldsArg))(td).map(_._1)
      }, playlistTrackPlaylistIdArg, playlistTrackTrackIdArg, dataFieldsArg),
      unitField(name = "remove", desc = None, t = playlistTrackType, f = (c, td) => {
        c.ctx.services.mediaServices.playlistTrackService.remove(c.ctx.creds, c.arg(playlistTrackPlaylistIdArg), c.arg(playlistTrackTrackIdArg))(td)
      }, playlistTrackPlaylistIdArg, playlistTrackTrackIdArg)
    )
  )

  val mutationFields = fields(unitField(name = "playlistTrack", desc = None, t = playlistTrackMutationType, f = (_, _) => scala.concurrent.Future.successful(())))

  private[this] def toResult(r: GraphQLSchemaHelper.SearchResult[PlaylistTrack]) = {
    PlaylistTrackResult(paging = r.paging, filters = r.args.filters, orderBys = r.args.orderBys, totalCount = r.count, results = r.results, durationMs = r.dur)
  }
}
