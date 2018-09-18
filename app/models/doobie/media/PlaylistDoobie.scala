/* Generated File */
package models.doobie.media

import cats.data.NonEmptyList
import models.doobie.DoobieQueries
import models.media.Playlist
import services.database.DoobieQueryService.Imports._

object PlaylistDoobie extends DoobieQueries[Playlist]("Playlist") {
  override val countFragment = fr"""select count(*) from "Playlist""""
  override val selectFragment = fr"""select "PlaylistId", "Name" from "Playlist""""

  override val columns = Seq("PlaylistId", "Name")
  override val searchColumns = Seq("PlaylistId", "Name")

  override def searchFragment(q: String) = {
    fr""""PlaylistId"::text = $q or "Name"::text = $q"""
  }

  def getByPrimaryKey(playlistId: Long) = (selectFragment ++ whereAnd(fr"playlistId = $playlistId")).query[Option[Playlist]].unique
  def getByPrimaryKeySeq(playlistIdSeq: NonEmptyList[Long]) = (selectFragment ++ in(fr"playlistId", playlistIdSeq)).query[Playlist].to[Seq]
}

