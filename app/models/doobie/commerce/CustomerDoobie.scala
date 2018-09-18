/* Generated File */
package models.doobie.commerce

import cats.data.NonEmptyList
import models.commerce.Customer
import models.doobie.DoobieQueries
import services.database.DoobieQueryService.Imports._

object CustomerDoobie extends DoobieQueries[Customer]("Customer") {
  override val countFragment = fr"""select count(*) from "Customer""""
  override val selectFragment = fr"""select "CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId" from "Customer""""

  override val columns = Seq("CustomerId", "FirstName", "LastName", "Company", "Address", "City", "State", "Country", "PostalCode", "Phone", "Fax", "Email", "SupportRepId")
  override val searchColumns = Seq("CustomerId", "FirstName", "LastName", "Company", "Email", "SupportRepId")

  override def searchFragment(q: String) = {
    fr""""CustomerId"::text = $q or "FirstName"::text = $q or "LastName"::text = $q or "Company"::text = $q or "Address"::text = $q or "City"::text = $q or "State"::text = $q or "Country"::text = $q or "PostalCode"::text = $q or "Phone"::text = $q or "Fax"::text = $q or "Email"::text = $q or "SupportRepId"::text = $q"""
  }

  def getByPrimaryKey(customerId: Long) = (selectFragment ++ whereAnd(fr"customerId = $customerId")).query[Option[Customer]].unique
  def getByPrimaryKeySeq(customerIdSeq: NonEmptyList[Long]) = (selectFragment ++ in(fr"customerId", customerIdSeq)).query[Customer].to[Seq]

  def countBySupportRepId(supportRepId: Long) = (countFragment ++ whereAnd(fr"supportRepId = $supportRepId")).query[Int].unique
  def getBySupportRepId(supportRepId: Long) = (selectFragment ++ whereAnd(fr"supportRepId = $supportRepId")).query[Customer].to[Seq]
  def getBySupportRepIdSeq(supportRepIdSeq: NonEmptyList[Long]) = (selectFragment ++ whereAnd(in(fr"supportRepId", supportRepIdSeq))).query[Customer].to[Seq]
}

