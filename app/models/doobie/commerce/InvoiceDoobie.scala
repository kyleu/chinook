/* Generated File */
package models.doobie.commerce

import cats.data.NonEmptyList
import models.commerce.Invoice
import models.doobie.DoobieQueries
import services.database.DoobieQueryService.Imports._

object InvoiceDoobie extends DoobieQueries[Invoice]("Invoice") {
  override val countFragment = fr"""select count(*) from "Invoice""""
  override val selectFragment = fr"""select "InvoiceId", "CustomerId", "InvoiceDate", "BillingAddress", "BillingCity", "BillingState", "BillingCountry", "BillingPostalCode", "Total" from "Invoice""""

  override val columns = Seq("InvoiceId", "CustomerId", "InvoiceDate", "BillingAddress", "BillingCity", "BillingState", "BillingCountry", "BillingPostalCode", "Total")
  override val searchColumns = Seq("InvoiceId", "CustomerId", "InvoiceDate", "Total")

  override def searchFragment(q: String) = {
    fr""""InvoiceId"::text = $q or "CustomerId"::text = $q or "InvoiceDate"::text = $q or "BillingAddress"::text = $q or "BillingCity"::text = $q or "BillingState"::text = $q or "BillingCountry"::text = $q or "BillingPostalCode"::text = $q or "Total"::text = $q"""
  }

  def getByPrimaryKey(invoiceId: Long) = (selectFragment ++ whereAnd(fr"invoiceId = $invoiceId")).query[Option[Invoice]].unique
  def getByPrimaryKeySeq(invoiceIdSeq: NonEmptyList[Long]) = (selectFragment ++ in(fr"invoiceId", invoiceIdSeq)).query[Invoice].to[Seq]

  def countByCustomerId(customerId: Long) = (countFragment ++ whereAnd(fr"customerId = $customerId")).query[Int].unique
  def getByCustomerId(customerId: Long) = (selectFragment ++ whereAnd(fr"customerId = $customerId")).query[Invoice].to[Seq]
  def getByCustomerIdSeq(customerIdSeq: NonEmptyList[Long]) = (selectFragment ++ whereAnd(in(fr"customerId", customerIdSeq))).query[Invoice].to[Seq]
}

