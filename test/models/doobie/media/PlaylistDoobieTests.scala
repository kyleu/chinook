/* Generated File */
package models.doobie.media

import models.doobie.DoobieTestHelper
import models.media.Playlist
import org.scalatest._
import services.database.DoobieQueryService.Imports._

class PlaylistDoobieTest extends FlatSpec with Matchers {
  import DoobieTestHelper.yolo._

  "Doobie queries for [Playlist]" should "typecheck" in {
    PlaylistDoobie.countFragment.query[Long].check.unsafeRunSync
    PlaylistDoobie.selectFragment.query[Playlist].check.unsafeRunSync
    (PlaylistDoobie.selectFragment ++ whereAnd(PlaylistDoobie.searchFragment("..."))).query[Playlist].check.unsafeRunSync
  }
}
