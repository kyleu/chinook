/* Generated File */
package models.doobie.media

import models.doobie.DoobieTestHelper
import models.media.PlaylistTrack
import org.scalatest._
import services.database.DoobieQueryService.Imports._

class PlaylistTrackDoobieTest extends FlatSpec with Matchers {
  import DoobieTestHelper.yolo._

  "Doobie queries for [PlaylistTrack]" should "typecheck" in {
    PlaylistTrackDoobie.countFragment.query[Long].check.unsafeRunSync
    PlaylistTrackDoobie.selectFragment.query[PlaylistTrack].check.unsafeRunSync
    (PlaylistTrackDoobie.selectFragment ++ whereAnd(PlaylistTrackDoobie.searchFragment("..."))).query[PlaylistTrack].check.unsafeRunSync
  }
}
