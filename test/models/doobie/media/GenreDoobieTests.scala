/* Generated File */
package models.doobie.media

import models.doobie.DoobieTestHelper
import models.media.Genre
import org.scalatest._
import services.database.DoobieQueryService.Imports._

class GenreDoobieTest extends FlatSpec with Matchers {
  import DoobieTestHelper.yolo._

  "Doobie queries for [Genre]" should "typecheck" in {
    GenreDoobie.countFragment.query[Long].check.unsafeRunSync
    GenreDoobie.selectFragment.query[Genre].check.unsafeRunSync
    (GenreDoobie.selectFragment ++ whereAnd(GenreDoobie.searchFragment("..."))).query[Genre].check.unsafeRunSync
  }
}
