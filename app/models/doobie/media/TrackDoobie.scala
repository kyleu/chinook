/* Generated File */
package models.doobie.media

import cats.data.NonEmptyList
import models.doobie.DoobieQueries
import models.media.Track
import services.database.DoobieQueryService.Imports._

object TrackDoobie extends DoobieQueries[Track]("Track") {
  override val countFragment = fr"""select count(*) from "Track""""
  override val selectFragment = fr"""select "TrackId", "Name", "AlbumId", "MediaTypeId", "GenreId", "Composer", "Milliseconds", "Bytes", "UnitPrice" from "Track""""

  override val columns = Seq("TrackId", "Name", "AlbumId", "MediaTypeId", "GenreId", "Composer", "Milliseconds", "Bytes", "UnitPrice")
  override val searchColumns = Seq("TrackId", "Name", "AlbumId", "MediaTypeId", "GenreId")

  override def searchFragment(q: String) = {
    fr""""TrackId"::text = $q or "Name"::text = $q or "AlbumId"::text = $q or "MediaTypeId"::text = $q or "GenreId"::text = $q or "Composer"::text = $q or "Milliseconds"::text = $q or "Bytes"::text = $q or "UnitPrice"::text = $q"""
  }

  def getByPrimaryKey(trackId: Long) = (selectFragment ++ whereAnd(fr"trackId = $trackId")).query[Option[Track]].unique
  def getByPrimaryKeySeq(trackIdSeq: NonEmptyList[Long]) = (selectFragment ++ in(fr"trackId", trackIdSeq)).query[Track].to[Seq]

  def countByAlbumId(albumId: Long) = (countFragment ++ whereAnd(fr"albumId = $albumId")).query[Int].unique
  def getByAlbumId(albumId: Long) = (selectFragment ++ whereAnd(fr"albumId = $albumId")).query[Track].to[Seq]
  def getByAlbumIdSeq(albumIdSeq: NonEmptyList[Long]) = (selectFragment ++ whereAnd(in(fr"albumId", albumIdSeq))).query[Track].to[Seq]

  def countByMediaTypeId(mediaTypeId: Long) = (countFragment ++ whereAnd(fr"mediaTypeId = $mediaTypeId")).query[Int].unique
  def getByMediaTypeId(mediaTypeId: Long) = (selectFragment ++ whereAnd(fr"mediaTypeId = $mediaTypeId")).query[Track].to[Seq]
  def getByMediaTypeIdSeq(mediaTypeIdSeq: NonEmptyList[Long]) = (selectFragment ++ whereAnd(in(fr"mediaTypeId", mediaTypeIdSeq))).query[Track].to[Seq]

  def countByGenreId(genreId: Long) = (countFragment ++ whereAnd(fr"genreId = $genreId")).query[Int].unique
  def getByGenreId(genreId: Long) = (selectFragment ++ whereAnd(fr"genreId = $genreId")).query[Track].to[Seq]
  def getByGenreIdSeq(genreIdSeq: NonEmptyList[Long]) = (selectFragment ++ whereAnd(in(fr"genreId", genreIdSeq))).query[Track].to[Seq]
}

