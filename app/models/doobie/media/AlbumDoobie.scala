/* Generated File */
package models.doobie.media

import cats.data.NonEmptyList
import models.doobie.DoobieQueries
import models.media.Album
import services.database.DoobieQueryService.Imports._

object AlbumDoobie extends DoobieQueries[Album]("Album") {
  override val countFragment = fr"""select count(*) from "Album""""
  override val selectFragment = fr"""select "AlbumId", "Title", "ArtistId" from "Album""""

  override val columns = Seq("AlbumId", "Title", "ArtistId")
  override val searchColumns = Seq("AlbumId", "Title", "ArtistId")

  override def searchFragment(q: String) = {
    fr""""AlbumId"::text = $q or "Title"::text = $q or "ArtistId"::text = $q"""
  }

  def getByPrimaryKey(albumId: Long) = (selectFragment ++ whereAnd(fr"albumId = $albumId")).query[Option[Album]].unique
  def getByPrimaryKeySeq(albumIdSeq: NonEmptyList[Long]) = (selectFragment ++ in(fr"albumId", albumIdSeq)).query[Album].to[Seq]

  def countByArtistId(artistId: Long) = (countFragment ++ whereAnd(fr"artistId = $artistId")).query[Int].unique
  def getByArtistId(artistId: Long) = (selectFragment ++ whereAnd(fr"artistId = $artistId")).query[Album].to[Seq]
  def getByArtistIdSeq(artistIdSeq: NonEmptyList[Long]) = (selectFragment ++ whereAnd(in(fr"artistId", artistIdSeq))).query[Album].to[Seq]
}

