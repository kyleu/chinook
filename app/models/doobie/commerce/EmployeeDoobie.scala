/* Generated File */
package models.doobie.commerce

import cats.data.NonEmptyList
import models.commerce.Employee
import models.doobie.DoobieQueries
import services.database.DoobieQueryService.Imports._

object EmployeeDoobie extends DoobieQueries[Employee]("Employee") {
  override val countFragment = fr"""select count(*) from "Employee""""
  override val selectFragment = fr"""select "EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email" from "Employee""""

  override val columns = Seq("EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "BirthDate", "HireDate", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email")
  override val searchColumns = Seq("EmployeeId", "LastName", "FirstName", "Title", "ReportsTo", "Email")

  override def searchFragment(q: String) = {
    fr""""EmployeeId"::text = $q or "LastName"::text = $q or "FirstName"::text = $q or "Title"::text = $q or "ReportsTo"::text = $q or "BirthDate"::text = $q or "HireDate"::text = $q or "Address"::text = $q or "City"::text = $q or "State"::text = $q or "Country"::text = $q or "PostalCode"::text = $q or "Phone"::text = $q or "Fax"::text = $q or "Email"::text = $q"""
  }

  def getByPrimaryKey(employeeId: Long) = (selectFragment ++ whereAnd(fr"employeeId = $employeeId")).query[Option[Employee]].unique
  def getByPrimaryKeySeq(employeeIdSeq: NonEmptyList[Long]) = (selectFragment ++ in(fr"employeeId", employeeIdSeq)).query[Employee].to[Seq]

  def countByReportsTo(reportsTo: Long) = (countFragment ++ whereAnd(fr"reportsTo = $reportsTo")).query[Int].unique
  def getByReportsTo(reportsTo: Long) = (selectFragment ++ whereAnd(fr"reportsTo = $reportsTo")).query[Employee].to[Seq]
  def getByReportsToSeq(reportsToSeq: NonEmptyList[Long]) = (selectFragment ++ whereAnd(in(fr"reportsTo", reportsToSeq))).query[Employee].to[Seq]
}

