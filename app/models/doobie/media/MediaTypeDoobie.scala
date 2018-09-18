/* Generated File */
package models.doobie.media

import cats.data.NonEmptyList
import models.doobie.DoobieQueries
import models.media.MediaType
import services.database.DoobieQueryService.Imports._

object MediaTypeDoobie extends DoobieQueries[MediaType]("MediaType") {
  override val countFragment = fr"""select count(*) from "MediaType""""
  override val selectFragment = fr"""select "MediaTypeId", "Name" from "MediaType""""

  override val columns = Seq("MediaTypeId", "Name")
  override val searchColumns = Seq("MediaTypeId", "Name")

  override def searchFragment(q: String) = {
    fr""""MediaTypeId"::text = $q or "Name"::text = $q"""
  }

  def getByPrimaryKey(mediaTypeId: Long) = (selectFragment ++ whereAnd(fr"mediaTypeId = $mediaTypeId")).query[Option[MediaType]].unique
  def getByPrimaryKeySeq(mediaTypeIdSeq: NonEmptyList[Long]) = (selectFragment ++ in(fr"mediaTypeId", mediaTypeIdSeq)).query[MediaType].to[Seq]
}

