/* Generated File */
package models.queries.commerce

import java.time.LocalDateTime
import models.commerce.Employee
import models.database.{DatabaseField, Row}
import models.database.DatabaseFieldType._
import models.queries.BaseQueries
import models.result.ResultFieldHelper
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy

object EmployeeQueries extends BaseQueries[Employee]("employee", "Employee") {
  override val fields = Seq(
    DatabaseField(title = "Employee Id", prop = "employeeId", col = "EmployeeId", typ = LongType),
    DatabaseField(title = "Last Name", prop = "lastName", col = "LastName", typ = StringType),
    DatabaseField(title = "First Name", prop = "firstName", col = "FirstName", typ = StringType),
    DatabaseField(title = "Title", prop = "title", col = "Title", typ = StringType),
    DatabaseField(title = "Reports To", prop = "reportsTo", col = "ReportsTo", typ = LongType),
    DatabaseField(title = "Birth Date", prop = "birthDate", col = "BirthDate", typ = TimestampType),
    DatabaseField(title = "Hire Date", prop = "hireDate", col = "HireDate", typ = TimestampType),
    DatabaseField(title = "Address", prop = "address", col = "Address", typ = StringType),
    DatabaseField(title = "City", prop = "city", col = "City", typ = StringType),
    DatabaseField(title = "State", prop = "state", col = "State", typ = StringType),
    DatabaseField(title = "Country", prop = "country", col = "Country", typ = StringType),
    DatabaseField(title = "Postal Code", prop = "postalCode", col = "PostalCode", typ = StringType),
    DatabaseField(title = "Phone", prop = "phone", col = "Phone", typ = StringType),
    DatabaseField(title = "Fax", prop = "fax", col = "Fax", typ = StringType),
    DatabaseField(title = "Email", prop = "email", col = "Email", typ = StringType)
  )
  override protected val pkColumns = Seq("EmployeeId")
  override protected val searchColumns = Seq("EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "Email")

  def countAll(filters: Seq[Filter] = Nil) = onCountAll(filters)
  def getAll(filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new GetAll(filters, orderBys, limit, offset)
  }

  def search(q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new Search(q, filters, orderBys, limit, offset)
  }
  def searchCount(q: Option[String], filters: Seq[Filter] = Nil) = new SearchCount(q, filters)
  def searchExact(q: String, orderBys: Seq[OrderBy], limit: Option[Int], offset: Option[Int]) = new SearchExact(q, orderBys, limit, offset)

  def getByPrimaryKey(employeeId: Long) = new GetByPrimaryKey(Seq(employeeId))
  def getByPrimaryKeySeq(employeeIdSeq: Seq[Long]) = new ColSeqQuery(column = "EmployeeId", values = employeeIdSeq)

  final case class CountByEmail(email: String) extends ColCount(column = "Email", values = Seq(email))
  final case class GetByEmail(email: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("Email") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(email)
  )
  final case class GetByEmailSeq(emailSeq: Seq[String]) extends ColSeqQuery(column = "Email", values = emailSeq)

  final case class CountByEmployeeId(employeeId: Long) extends ColCount(column = "EmployeeId", values = Seq(employeeId))
  final case class GetByEmployeeId(employeeId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("EmployeeId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(employeeId)
  )
  final case class GetByEmployeeIdSeq(employeeIdSeq: Seq[Long]) extends ColSeqQuery(column = "EmployeeId", values = employeeIdSeq)

  final case class CountByFirstName(firstName: String) extends ColCount(column = "FirstName", values = Seq(firstName))
  final case class GetByFirstName(firstName: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("FirstName") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(firstName)
  )
  final case class GetByFirstNameSeq(firstNameSeq: Seq[String]) extends ColSeqQuery(column = "FirstName", values = firstNameSeq)

  final case class CountByLastName(lastName: String) extends ColCount(column = "LastName", values = Seq(lastName))
  final case class GetByLastName(lastName: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("LastName") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(lastName)
  )
  final case class GetByLastNameSeq(lastNameSeq: Seq[String]) extends ColSeqQuery(column = "LastName", values = lastNameSeq)

  final case class CountByReportsTo(reportsTo: Long) extends ColCount(column = "ReportsTo", values = Seq(reportsTo))
  final case class GetByReportsTo(reportsTo: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("ReportsTo") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(reportsTo)
  )
  final case class GetByReportsToSeq(reportsToSeq: Seq[Long]) extends ColSeqQuery(column = "ReportsTo", values = reportsToSeq)

  final case class CountByTitle(title: String) extends ColCount(column = "Title", values = Seq(title))
  final case class GetByTitle(title: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("Title") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(title)
  )
  final case class GetByTitleSeq(titleSeq: Seq[String]) extends ColSeqQuery(column = "Title", values = titleSeq)

  def insert(model: Employee) = new Insert(model)
  def insertBatch(models: Seq[Employee]) = new InsertBatch(models)
  def create(dataFields: Seq[DataField]) = new CreateFields(dataFields)

  def removeByPrimaryKey(employeeId: Long) = new RemoveByPrimaryKey(Seq[Any](employeeId))

  def update(employeeId: Long, fields: Seq[DataField]) = new UpdateFields(Seq[Any](employeeId), fields)

  override def fromRow(row: Row) = Employee(
    employeeId = LongType(row, "EmployeeId"),
    lastName = StringType(row, "LastName"),
    firstName = StringType(row, "FirstName"),
    title = StringType.opt(row, "Title"),
    reportsTo = LongType.opt(row, "ReportsTo"),
    birthDate = TimestampType.opt(row, "BirthDate"),
    hireDate = TimestampType.opt(row, "HireDate"),
    address = StringType.opt(row, "Address"),
    city = StringType.opt(row, "City"),
    state = StringType.opt(row, "State"),
    country = StringType.opt(row, "Country"),
    postalCode = StringType.opt(row, "PostalCode"),
    phone = StringType.opt(row, "Phone"),
    fax = StringType.opt(row, "Fax"),
    email = StringType.opt(row, "Email")
  )
}
