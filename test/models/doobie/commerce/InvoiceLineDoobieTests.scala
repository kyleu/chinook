/* Generated File */
package models.doobie.commerce

import models.commerce.InvoiceLine
import models.doobie.DoobieTestHelper
import org.scalatest._
import services.database.DoobieQueryService.Imports._

class InvoiceLineDoobieTest extends FlatSpec with Matchers {
  import DoobieTestHelper.yolo._

  "Doobie queries for [InvoiceLine]" should "typecheck" in {
    InvoiceLineDoobie.countFragment.query[Long].check.unsafeRunSync
    InvoiceLineDoobie.selectFragment.query[InvoiceLine].check.unsafeRunSync
    (InvoiceLineDoobie.selectFragment ++ whereAnd(InvoiceLineDoobie.searchFragment("..."))).query[InvoiceLine].check.unsafeRunSync
  }
}
