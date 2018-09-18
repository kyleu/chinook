/* Generated File */
package models.doobie.media

import models.doobie.DoobieTestHelper
import models.media.MediaType
import org.scalatest._
import services.database.DoobieQueryService.Imports._

class MediaTypeDoobieTest extends FlatSpec with Matchers {
  import DoobieTestHelper.yolo._

  "Doobie queries for [MediaType]" should "typecheck" in {
    MediaTypeDoobie.countFragment.query[Long].check.unsafeRunSync
    MediaTypeDoobie.selectFragment.query[MediaType].check.unsafeRunSync
    (MediaTypeDoobie.selectFragment ++ whereAnd(MediaTypeDoobie.searchFragment("..."))).query[MediaType].check.unsafeRunSync
  }
}
