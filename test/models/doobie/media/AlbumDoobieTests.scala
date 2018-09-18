/* Generated File */
package models.doobie.media

import models.doobie.DoobieTestHelper
import models.media.Album
import org.scalatest._
import services.database.DoobieQueryService.Imports._

class AlbumDoobieTest extends FlatSpec with Matchers {
  import DoobieTestHelper.yolo._

  "Doobie queries for [Album]" should "typecheck" in {
    AlbumDoobie.countFragment.query[Long].check.unsafeRunSync
    AlbumDoobie.selectFragment.query[Album].check.unsafeRunSync
    (AlbumDoobie.selectFragment ++ whereAnd(AlbumDoobie.searchFragment("..."))).query[Album].check.unsafeRunSync
  }
}
