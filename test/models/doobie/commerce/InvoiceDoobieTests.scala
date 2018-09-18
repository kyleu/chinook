/* Generated File */
package models.doobie.commerce

import models.commerce.Invoice
import models.doobie.DoobieTestHelper
import org.scalatest._
import services.database.DoobieQueryService.Imports._

class InvoiceDoobieTest extends FlatSpec with Matchers {
  import DoobieTestHelper.yolo._

  "Doobie queries for [Invoice]" should "typecheck" in {
    InvoiceDoobie.countFragment.query[Long].check.unsafeRunSync
    InvoiceDoobie.selectFragment.query[Invoice].check.unsafeRunSync
    (InvoiceDoobie.selectFragment ++ whereAnd(InvoiceDoobie.searchFragment("..."))).query[Invoice].check.unsafeRunSync
  }
}
