/* Generated File */
package models.doobie.commerce

import models.commerce.Employee
import models.doobie.DoobieTestHelper
import org.scalatest._
import services.database.DoobieQueryService.Imports._

class EmployeeDoobieTest extends FlatSpec with Matchers {
  import DoobieTestHelper.yolo._

  "Doobie queries for [Employee]" should "typecheck" in {
    EmployeeDoobie.countFragment.query[Long].check.unsafeRunSync
    EmployeeDoobie.selectFragment.query[Employee].check.unsafeRunSync
    (EmployeeDoobie.selectFragment ++ whereAnd(EmployeeDoobie.searchFragment("..."))).query[Employee].check.unsafeRunSync
  }
}
