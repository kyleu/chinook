/* Generated File */
package models.doobie.media

import cats.data.NonEmptyList
import models.doobie.DoobieQueries
import models.media.PlaylistTrack
import services.database.DoobieQueryService.Imports._

object PlaylistTrackDoobie extends DoobieQueries[PlaylistTrack]("PlaylistTrack") {
  override val countFragment = fr"""select count(*) from "PlaylistTrack""""
  override val selectFragment = fr"""select "PlaylistId", "TrackId" from "PlaylistTrack""""

  override val columns = Seq("PlaylistId", "TrackId")
  override val searchColumns = Seq("PlaylistId", "TrackId")

  override def searchFragment(q: String) = {
    fr""""PlaylistId"::text = $q or "TrackId"::text = $q"""
  }

  // def getByPrimaryKey(playlistId: Long, trackId: Long) = ???

  def countByTrackId(trackId: Long) = (countFragment ++ whereAnd(fr"trackId = $trackId")).query[Int].unique
  def getByTrackId(trackId: Long) = (selectFragment ++ whereAnd(fr"trackId = $trackId")).query[PlaylistTrack].to[Seq]
  def getByTrackIdSeq(trackIdSeq: NonEmptyList[Long]) = (selectFragment ++ whereAnd(in(fr"trackId", trackIdSeq))).query[PlaylistTrack].to[Seq]

  def countByPlaylistId(playlistId: Long) = (countFragment ++ whereAnd(fr"playlistId = $playlistId")).query[Int].unique
  def getByPlaylistId(playlistId: Long) = (selectFragment ++ whereAnd(fr"playlistId = $playlistId")).query[PlaylistTrack].to[Seq]
  def getByPlaylistIdSeq(playlistIdSeq: NonEmptyList[Long]) = (selectFragment ++ whereAnd(in(fr"playlistId", playlistIdSeq))).query[PlaylistTrack].to[Seq]
}

