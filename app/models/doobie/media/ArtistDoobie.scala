/* Generated File */
package models.doobie.media

import cats.data.NonEmptyList
import models.doobie.DoobieQueries
import models.media.Artist
import services.database.DoobieQueryService.Imports._

object ArtistDoobie extends DoobieQueries[Artist]("Artist") {
  override val countFragment = fr"""select count(*) from "Artist""""
  override val selectFragment = fr"""select "ArtistId", "Name" from "Artist""""

  override val columns = Seq("ArtistId", "Name")
  override val searchColumns = Seq("ArtistId", "Name")

  override def searchFragment(q: String) = {
    fr""""ArtistId"::text = $q or "Name"::text = $q"""
  }

  def getByPrimaryKey(artistId: Long) = (selectFragment ++ whereAnd(fr"artistId = $artistId")).query[Option[Artist]].unique
  def getByPrimaryKeySeq(artistIdSeq: NonEmptyList[Long]) = (selectFragment ++ in(fr"artistId", artistIdSeq)).query[Artist].to[Seq]
}

