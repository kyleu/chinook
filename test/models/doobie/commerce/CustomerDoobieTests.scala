/* Generated File */
package models.doobie.commerce

import models.commerce.Customer
import models.doobie.DoobieTestHelper
import org.scalatest._
import services.database.DoobieQueryService.Imports._

class CustomerDoobieTest extends FlatSpec with Matchers {
  import DoobieTestHelper.yolo._

  "Doobie queries for [Customer]" should "typecheck" in {
    CustomerDoobie.countFragment.query[Long].check.unsafeRunSync
    CustomerDoobie.selectFragment.query[Customer].check.unsafeRunSync
    (CustomerDoobie.selectFragment ++ whereAnd(CustomerDoobie.searchFragment("..."))).query[Customer].check.unsafeRunSync
  }
}
