/* Generated File */
package models.doobie.media

import models.doobie.DoobieTestHelper
import models.media.Artist
import org.scalatest._
import services.database.DoobieQueryService.Imports._

class ArtistDoobieTest extends FlatSpec with Matchers {
  import DoobieTestHelper.yolo._

  "Doobie queries for [Artist]" should "typecheck" in {
    ArtistDoobie.countFragment.query[Long].check.unsafeRunSync
    ArtistDoobie.selectFragment.query[Artist].check.unsafeRunSync
    (ArtistDoobie.selectFragment ++ whereAnd(ArtistDoobie.searchFragment("..."))).query[Artist].check.unsafeRunSync
  }
}
