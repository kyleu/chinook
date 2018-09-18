/* Generated File */
package models.media

import java.time.LocalDateTime
import models.result.BaseResult
import models.result.filter.Filter
import models.result.orderBy.OrderBy
import models.result.paging.PagingOptions
import util.JsonSerializers._

final case class MediaTypeResult(
    override val filters: Seq[Filter] = Nil,
    override val orderBys: Seq[OrderBy] = Nil,
    override val totalCount: Int = 0,
    override val paging: PagingOptions = PagingOptions(),
    override val results: Seq[MediaType] = Nil,
    override val durationMs: Int = 0,
    override val occurred: LocalDateTime = util.DateUtils.now
) extends BaseResult[MediaType]

object MediaTypeResult {
  implicit val jsonEncoder: Encoder[MediaTypeResult] = deriveEncoder
  implicit val jsonDecoder: Decoder[MediaTypeResult] = deriveDecoder

  def fromRecords(
    q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None,
    startMs: Long, totalCount: Int, results: Seq[MediaType]
  ) = {
    val paging = PagingOptions.from(totalCount, limit, offset)
    val durationMs = (util.DateUtils.nowMillis - startMs).toInt
    MediaTypeResult(paging = paging, filters = filters, orderBys = orderBys, totalCount = totalCount, results = results, durationMs = durationMs)
  }
}
