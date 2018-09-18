/* Generated File */
package models.media

import graphql.{GraphQLContext, GraphQLSchemaHelper}
import graphql.GraphQLUtils._
import models.note.NoteSchema
import sangria.execution.deferred.{Fetcher, HasId}
import sangria.schema._

object PlaylistSchema extends GraphQLSchemaHelper("playlist") {
  implicit val playlistPrimaryKeyId: HasId[Playlist, Long] = HasId[Playlist, Long](_.playlistId)
  private[this] def getByPrimaryKeySeq(c: GraphQLContext, idSeq: Seq[Long]) = {
    c.services.mediaServices.playlistService.getByPrimaryKeySeq(c.creds, idSeq)(c.trace)
  }
  val playlistByPrimaryKeyFetcher = Fetcher(getByPrimaryKeySeq)

  val playlistPlaylistIdArg = Argument("playlistId", LongType)
  val playlistPlaylistIdSeqArg = Argument("playlistIds", ListInputType(LongType))

  implicit lazy val playlistType: sangria.schema.ObjectType[GraphQLContext, Playlist] = deriveObjectType(
    sangria.macros.derive.AddFields(
      Field(
        name = "playlistTracks",
        fieldType = ListType(PlaylistTrackSchema.playlistTrackType),
        resolve = c => PlaylistTrackSchema.playlistTrackByPlaylistIdFetcher.deferRelSeq(
          PlaylistTrackSchema.playlistTrackByPlaylistIdRelation, c.value.playlistId
        )
      ),
      Field(
        name = "relatedNotes",
        fieldType = ListType(NoteSchema.noteType),
        resolve = c => c.ctx.app.coreServices.notes.getFor(c.ctx.creds, "playlist", c.value.playlistId)(c.ctx.trace)
      )
    )
  )

  implicit lazy val playlistResultType: sangria.schema.ObjectType[GraphQLContext, PlaylistResult] = deriveObjectType()

  val queryFields = fields(
    unitField(name = "playlist", desc = None, t = OptionType(playlistType), f = (c, td) => {
      c.ctx.services.mediaServices.playlistService.getByPrimaryKey(c.ctx.creds, c.arg(playlistPlaylistIdArg))(td)
    }, playlistPlaylistIdArg),
    unitField(name = "playlistSeq", desc = None, t = ListType(playlistType), f = (c, td) => {
      c.ctx.services.mediaServices.playlistService.getByPrimaryKeySeq(c.ctx.creds, c.arg(playlistPlaylistIdSeqArg))(td)
    }, playlistPlaylistIdSeqArg),
    unitField(name = "playlistSearch", desc = None, t = playlistResultType, f = (c, td) => {
      runSearch(c.ctx.services.mediaServices.playlistService, c, td).map(toResult)
    }, queryArg, reportFiltersArg, orderBysArg, limitArg, offsetArg),
    unitField(name = "playlistByPlaylistId", desc = None, t = OptionType(playlistType), f = (c, td) => {
      c.ctx.services.mediaServices.playlistService.getByPlaylistId(c.ctx.creds, c.arg(playlistPlaylistIdArg))(td).map(_.headOption)
    }, playlistPlaylistIdArg),
    unitField(name = "playlistByPlaylistIdSeq", desc = None, t = ListType(playlistType), f = (c, td) => {
      c.ctx.services.mediaServices.playlistService.getByPlaylistIdSeq(c.ctx.creds, c.arg(playlistPlaylistIdSeqArg))(td)
    }, playlistPlaylistIdSeqArg)
  )

  val playlistMutationType = ObjectType(
    name = "PlaylistMutations",
    fields = fields(
      unitField(name = "create", desc = None, t = OptionType(playlistType), f = (c, td) => {
        c.ctx.services.mediaServices.playlistService.create(c.ctx.creds, c.arg(dataFieldsArg))(td)
      }, dataFieldsArg),
      unitField(name = "update", desc = None, t = OptionType(playlistType), f = (c, td) => {
        c.ctx.services.mediaServices.playlistService.update(c.ctx.creds, c.arg(playlistPlaylistIdArg), c.arg(dataFieldsArg))(td).map(_._1)
      }, playlistPlaylistIdArg, dataFieldsArg),
      unitField(name = "remove", desc = None, t = playlistType, f = (c, td) => {
        c.ctx.services.mediaServices.playlistService.remove(c.ctx.creds, c.arg(playlistPlaylistIdArg))(td)
      }, playlistPlaylistIdArg)
    )
  )

  val mutationFields = fields(unitField(name = "playlist", desc = None, t = playlistMutationType, f = (_, _) => scala.concurrent.Future.successful(())))

  private[this] def toResult(r: GraphQLSchemaHelper.SearchResult[Playlist]) = {
    PlaylistResult(paging = r.paging, filters = r.args.filters, orderBys = r.args.orderBys, totalCount = r.count, results = r.results, durationMs = r.dur)
  }
}
