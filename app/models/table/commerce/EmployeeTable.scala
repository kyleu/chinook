/* Generated File */
package models.table.commerce

import java.time.LocalDateTime
import services.database.SlickQueryService.imports._

object EmployeeTable {
  val query = TableQuery[EmployeeTable]

  def getByPrimaryKey(employeeId: Long) = query.filter(_.employeeId === employeeId).result.headOption
  def getByPrimaryKeySeq(employeeIdSeq: Seq[Long]) = query.filter(_.employeeId.inSet(employeeIdSeq)).result

  def getByReportsTo(reportsTo: Long) = query.filter(_.reportsTo === reportsTo).result
  def getByReportsToSeq(reportsToSeq: Seq[Long]) = query.filter(_.reportsTo.inSet(reportsToSeq)).result
}

class EmployeeTable(tag: Tag) extends Table[models.commerce.Employee](tag, "Employee") {
  val employeeId = column[Long]("EmployeeId")
  val lastName = column[String]("LastName")
  val firstName = column[String]("FirstName")
  val title = column[Option[String]]("Title")
  val reportsTo = column[Option[Long]]("ReportsTo")
  val birthDate = column[Option[LocalDateTime]]("BirthDate")
  val hireDate = column[Option[LocalDateTime]]("HireDate")
  val address = column[Option[String]]("Address")
  val city = column[Option[String]]("City")
  val state = column[Option[String]]("State")
  val country = column[Option[String]]("Country")
  val postalCode = column[Option[String]]("PostalCode")
  val phone = column[Option[String]]("Phone")
  val fax = column[Option[String]]("Fax")
  val email = column[Option[String]]("Email")

  val modelPrimaryKey = primaryKey("pk_Employee", employeeId)

  override val * = (employeeId, lastName, firstName, title, reportsTo, birthDate, hireDate, address, city, state, country, postalCode, phone, fax, email) <> (
    (models.commerce.Employee.apply _).tupled,
    models.commerce.Employee.unapply
  )
}

