/* Generated File */
package models.media

import graphql.{GraphQLContext, GraphQLSchemaHelper}
import graphql.GraphQLUtils._
import models.note.NoteSchema
import sangria.execution.deferred.{Fetcher, HasId, Relation}
import sangria.schema._

object AlbumSchema extends GraphQLSchemaHelper("album") {
  implicit val albumPrimaryKeyId: HasId[Album, Long] = HasId[Album, Long](_.albumId)
  private[this] def getByPrimaryKeySeq(c: GraphQLContext, idSeq: Seq[Long]) = {
    c.services.mediaServices.albumService.getByPrimaryKeySeq(c.creds, idSeq)(c.trace)
  }
  val albumByPrimaryKeyFetcher = Fetcher(getByPrimaryKeySeq)

  val albumAlbumIdArg = Argument("albumId", LongType)
  val albumAlbumIdSeqArg = Argument("albumIds", ListInputType(LongType))

  val albumArtistIdArg = Argument("artistId", LongType)
  val albumArtistIdSeqArg = Argument("artistIds", ListInputType(LongType))

  val albumByArtistIdRelation = Relation[Album, Long]("byArtistId", x => Seq(x.artistId))
  val albumByArtistIdFetcher = Fetcher.rel[GraphQLContext, Album, Album, Long](
    getByPrimaryKeySeq, (c, rels) => c.services.mediaServices.albumService.getByArtistIdSeq(c.creds, rels(albumByArtistIdRelation))(c.trace)
  )

  implicit lazy val albumType: sangria.schema.ObjectType[GraphQLContext, Album] = deriveObjectType(
    sangria.macros.derive.AddFields(
      Field(
        name = "tracks",
        fieldType = ListType(TrackSchema.trackType),
        resolve = c => TrackSchema.trackByAlbumIdFetcher.deferRelSeq(
          TrackSchema.trackByAlbumIdRelation, Some(c.value.albumId)
        )
      ),
      Field(
        name = "artistIdRel",
        fieldType = ArtistSchema.artistType,
        resolve = ctx => ArtistSchema.artistByPrimaryKeyFetcher.defer(ctx.value.artistId)
      ),
      Field(
        name = "relatedNotes",
        fieldType = ListType(NoteSchema.noteType),
        resolve = c => c.ctx.app.coreServices.notes.getFor(c.ctx.creds, "album", c.value.albumId)(c.ctx.trace)
      )
    )
  )

  implicit lazy val albumResultType: sangria.schema.ObjectType[GraphQLContext, AlbumResult] = deriveObjectType()

  val queryFields = fields(
    unitField(name = "album", desc = None, t = OptionType(albumType), f = (c, td) => {
      c.ctx.services.mediaServices.albumService.getByPrimaryKey(c.ctx.creds, c.arg(albumAlbumIdArg))(td)
    }, albumAlbumIdArg),
    unitField(name = "albumSeq", desc = None, t = ListType(albumType), f = (c, td) => {
      c.ctx.services.mediaServices.albumService.getByPrimaryKeySeq(c.ctx.creds, c.arg(albumAlbumIdSeqArg))(td)
    }, albumAlbumIdSeqArg),
    unitField(name = "albumSearch", desc = None, t = albumResultType, f = (c, td) => {
      runSearch(c.ctx.services.mediaServices.albumService, c, td).map(toResult)
    }, queryArg, reportFiltersArg, orderBysArg, limitArg, offsetArg),
    unitField(name = "albumByAlbumId", desc = None, t = OptionType(albumType), f = (c, td) => {
      c.ctx.services.mediaServices.albumService.getByAlbumId(c.ctx.creds, c.arg(albumAlbumIdArg))(td).map(_.headOption)
    }, albumAlbumIdArg),
    unitField(name = "albumByAlbumIdSeq", desc = None, t = ListType(albumType), f = (c, td) => {
      c.ctx.services.mediaServices.albumService.getByAlbumIdSeq(c.ctx.creds, c.arg(albumAlbumIdSeqArg))(td)
    }, albumAlbumIdSeqArg),
    unitField(name = "albumsByArtistId", desc = None, t = ListType(albumType), f = (c, td) => {
      c.ctx.services.mediaServices.albumService.getByArtistId(c.ctx.creds, c.arg(albumArtistIdArg))(td)
    }, albumArtistIdArg),
    unitField(name = "albumsByArtistIdSeq", desc = None, t = ListType(albumType), f = (c, td) => {
      c.ctx.services.mediaServices.albumService.getByArtistIdSeq(c.ctx.creds, c.arg(albumArtistIdSeqArg))(td)
    }, albumArtistIdSeqArg)
  )

  val albumMutationType = ObjectType(
    name = "AlbumMutations",
    fields = fields(
      unitField(name = "create", desc = None, t = OptionType(albumType), f = (c, td) => {
        c.ctx.services.mediaServices.albumService.create(c.ctx.creds, c.arg(dataFieldsArg))(td)
      }, dataFieldsArg),
      unitField(name = "update", desc = None, t = OptionType(albumType), f = (c, td) => {
        c.ctx.services.mediaServices.albumService.update(c.ctx.creds, c.arg(albumAlbumIdArg), c.arg(dataFieldsArg))(td).map(_._1)
      }, albumAlbumIdArg, dataFieldsArg),
      unitField(name = "remove", desc = None, t = albumType, f = (c, td) => {
        c.ctx.services.mediaServices.albumService.remove(c.ctx.creds, c.arg(albumAlbumIdArg))(td)
      }, albumAlbumIdArg)
    )
  )

  val mutationFields = fields(unitField(name = "album", desc = None, t = albumMutationType, f = (_, _) => scala.concurrent.Future.successful(())))

  private[this] def toResult(r: GraphQLSchemaHelper.SearchResult[Album]) = {
    AlbumResult(paging = r.paging, filters = r.args.filters, orderBys = r.args.orderBys, totalCount = r.count, results = r.results, durationMs = r.dur)
  }
}
