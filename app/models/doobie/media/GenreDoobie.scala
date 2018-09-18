/* Generated File */
package models.doobie.media

import cats.data.NonEmptyList
import models.doobie.DoobieQueries
import models.media.Genre
import services.database.DoobieQueryService.Imports._

object GenreDoobie extends DoobieQueries[Genre]("Genre") {
  override val countFragment = fr"""select count(*) from "Genre""""
  override val selectFragment = fr"""select "GenreId", "Name" from "Genre""""

  override val columns = Seq("GenreId", "Name")
  override val searchColumns = Seq("GenreId", "Name")

  override def searchFragment(q: String) = {
    fr""""GenreId"::text = $q or "Name"::text = $q"""
  }

  def getByPrimaryKey(genreId: Long) = (selectFragment ++ whereAnd(fr"genreId = $genreId")).query[Option[Genre]].unique
  def getByPrimaryKeySeq(genreIdSeq: NonEmptyList[Long]) = (selectFragment ++ in(fr"genreId", genreIdSeq)).query[Genre].to[Seq]
}

