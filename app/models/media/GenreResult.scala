/* Generated File */
package models.media

import java.time.LocalDateTime
import models.result.BaseResult
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import models.result.paging.PagingOptions
import util.JsonSerializers._

final case class GenreResult(
    override val filters: Seq[Filter] = Nil,
    override val orderBys: Seq[OrderBy] = Nil,
    override val totalCount: Int = 0,
    override val paging: PagingOptions = PagingOptions(),
    override val results: Seq[Genre] = Nil,
    override val durationMs: Int = 0,
    override val occurred: LocalDateTime = util.DateUtils.now
) extends BaseResult[Genre]

object GenreResult {
  implicit val jsonEncoder: Encoder[GenreResult] = deriveEncoder
  implicit val jsonDecoder: Decoder[GenreResult] = deriveDecoder

  def fromRecords(
    q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None,
    startMs: Long, totalCount: Int, results: Seq[Genre]
  ) = {
    val paging = PagingOptions.from(totalCount, limit, offset)
    val durationMs = (util.DateUtils.nowMillis - startMs).toInt
    GenreResult(paging = paging, filters = filters, orderBys = orderBys, totalCount = totalCount, results = results, durationMs = durationMs)
  }
}
