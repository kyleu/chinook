/* Generated File */
package models.queries.commerce

import models.commerce.Customer
import models.database.{DatabaseField, Row}
import models.database.DatabaseFieldType._
import models.queries.BaseQueries
import models.result.ResultFieldHelper
import models.result.data.DataField
import models.result.filter.Filter
import models.result.orderBy.OrderBy

object CustomerQueries extends BaseQueries[Customer]("customer", "Customer") {
  override val fields = Seq(
    DatabaseField(title = "Customer Id", prop = "customerId", col = "CustomerId", typ = LongType),
    DatabaseField(title = "First Name", prop = "firstName", col = "FirstName", typ = StringType),
    DatabaseField(title = "Last Name", prop = "lastName", col = "LastName", typ = StringType),
    DatabaseField(title = "Company", prop = "company", col = "Company", typ = StringType),
    DatabaseField(title = "Address", prop = "address", col = "Address", typ = StringType),
    DatabaseField(title = "City", prop = "city", col = "City", typ = StringType),
    DatabaseField(title = "State", prop = "state", col = "State", typ = StringType),
    DatabaseField(title = "Country", prop = "country", col = "Country", typ = StringType),
    DatabaseField(title = "Postal Code", prop = "postalCode", col = "PostalCode", typ = StringType),
    DatabaseField(title = "Phone", prop = "phone", col = "Phone", typ = StringType),
    DatabaseField(title = "Fax", prop = "fax", col = "Fax", typ = StringType),
    DatabaseField(title = "Email", prop = "email", col = "Email", typ = StringType),
    DatabaseField(title = "Support Rep Id", prop = "supportRepId", col = "SupportRepId", typ = LongType)
  )
  override protected val pkColumns = Seq("CustomerId")
  override protected val searchColumns = Seq("CustomerId", "FirstName", "LastName", "Company", "Email", "SupportRepId")

  def countAll(filters: Seq[Filter] = Nil) = onCountAll(filters)
  def getAll(filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new GetAll(filters, orderBys, limit, offset)
  }

  def search(q: Option[String], filters: Seq[Filter] = Nil, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) = {
    new Search(q, filters, orderBys, limit, offset)
  }
  def searchCount(q: Option[String], filters: Seq[Filter] = Nil) = new SearchCount(q, filters)
  def searchExact(q: String, orderBys: Seq[OrderBy], limit: Option[Int], offset: Option[Int]) = new SearchExact(q, orderBys, limit, offset)

  def getByPrimaryKey(customerId: Long) = new GetByPrimaryKey(Seq(customerId))
  def getByPrimaryKeySeq(customerIdSeq: Seq[Long]) = new ColSeqQuery(column = "CustomerId", values = customerIdSeq)

  final case class CountByCompany(company: String) extends ColCount(column = "Company", values = Seq(company))
  final case class GetByCompany(company: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("Company") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(company)
  )
  final case class GetByCompanySeq(companySeq: Seq[String]) extends ColSeqQuery(column = "Company", values = companySeq)

  final case class CountByCustomerId(customerId: Long) extends ColCount(column = "CustomerId", values = Seq(customerId))
  final case class GetByCustomerId(customerId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("CustomerId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(customerId)
  )
  final case class GetByCustomerIdSeq(customerIdSeq: Seq[Long]) extends ColSeqQuery(column = "CustomerId", values = customerIdSeq)

  final case class CountByEmail(email: String) extends ColCount(column = "Email", values = Seq(email))
  final case class GetByEmail(email: String, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("Email") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(email)
  )
  final case class GetByEmailSeq(emailSeq: Seq[String]) extends ColSeqQuery(column = "Email", values = emailSeq)

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

  final case class CountBySupportRepId(supportRepId: Long) extends ColCount(column = "SupportRepId", values = Seq(supportRepId))
  final case class GetBySupportRepId(supportRepId: Long, orderBys: Seq[OrderBy] = Nil, limit: Option[Int] = None, offset: Option[Int] = None) extends SeqQuery(
    whereClause = Some(quote("SupportRepId") + "  = ?"), orderBy = ResultFieldHelper.orderClause(fields, orderBys: _*),
    limit = limit, offset = offset, values = Seq(supportRepId)
  )
  final case class GetBySupportRepIdSeq(supportRepIdSeq: Seq[Long]) extends ColSeqQuery(column = "SupportRepId", values = supportRepIdSeq)

  def insert(model: Customer) = new Insert(model)
  def insertBatch(models: Seq[Customer]) = new InsertBatch(models)
  def create(dataFields: Seq[DataField]) = new CreateFields(dataFields)

  def removeByPrimaryKey(customerId: Long) = new RemoveByPrimaryKey(Seq[Any](customerId))

  def update(customerId: Long, fields: Seq[DataField]) = new UpdateFields(Seq[Any](customerId), fields)

  override def fromRow(row: Row) = Customer(
    customerId = LongType(row, "CustomerId"),
    firstName = StringType(row, "FirstName"),
    lastName = StringType(row, "LastName"),
    company = StringType.opt(row, "Company"),
    address = StringType.opt(row, "Address"),
    city = StringType.opt(row, "City"),
    state = StringType.opt(row, "State"),
    country = StringType.opt(row, "Country"),
    postalCode = StringType.opt(row, "PostalCode"),
    phone = StringType.opt(row, "Phone"),
    fax = StringType.opt(row, "Fax"),
    email = StringType(row, "Email"),
    supportRepId = LongType.opt(row, "SupportRepId")
  )
}
