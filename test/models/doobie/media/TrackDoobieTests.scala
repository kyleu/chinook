/* Generated File */
package models.doobie.media

import models.doobie.DoobieTestHelper
import models.media.Track
import org.scalatest._
import services.database.DoobieQueryService.Imports._

class TrackDoobieTest extends FlatSpec with Matchers {
  import DoobieTestHelper.yolo._

  "Doobie queries for [Track]" should "typecheck" in {
    TrackDoobie.countFragment.query[Long].check.unsafeRunSync
    TrackDoobie.selectFragment.query[Track].check.unsafeRunSync
    (TrackDoobie.selectFragment ++ whereAnd(TrackDoobie.searchFragment("..."))).query[Track].check.unsafeRunSync
  }
}
