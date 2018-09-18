/* Generated File */
package models.media

import models.result.data.{DataField, DataFieldModel, DataSummary}
import util.JsonSerializers._

object Genre {
  implicit val jsonEncoder: Encoder[Genre] = deriveEncoder
  implicit val jsonDecoder: Decoder[Genre] = deriveDecoder

  def empty(genreId: Long = 0L, name: Option[String] = None) = {
    Genre(genreId, name)
  }
}

final case class Genre(
    genreId: Long,
    name: Option[String]
) extends DataFieldModel {
  override def toDataFields = Seq(
    DataField("genreId", Some(genreId.toString)),
    DataField("name", name)
  )

  def toSummary = DataSummary(model = "genre", pk = Seq(genreId.toString), title = s"$name ($genreId)")
}
