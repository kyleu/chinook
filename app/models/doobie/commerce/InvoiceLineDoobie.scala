/* Generated File */
package models.doobie.commerce

import cats.data.NonEmptyList
import models.commerce.InvoiceLine
import models.doobie.DoobieQueries
import services.database.DoobieQueryService.Imports._

object InvoiceLineDoobie extends DoobieQueries[InvoiceLine]("InvoiceLine") {
  override val countFragment = fr"""select count(*) from "InvoiceLine""""
  override val selectFragment = fr"""select "InvoiceLineId", "InvoiceId", "TrackId", "UnitPrice", "Quantity" from "InvoiceLine""""

  override val columns = Seq("InvoiceLineId", "InvoiceId", "TrackId", "UnitPrice", "Quantity")
  override val searchColumns = Seq("InvoiceLineId", "InvoiceId", "TrackId", "UnitPrice", "Quantity")

  override def searchFragment(q: String) = {
    fr""""InvoiceLineId"::text = $q or "InvoiceId"::text = $q or "TrackId"::text = $q or "UnitPrice"::text = $q or "Quantity"::text = $q"""
  }

  def getByPrimaryKey(invoiceLineId: Long) = (selectFragment ++ whereAnd(fr"invoiceLineId = $invoiceLineId")).query[Option[InvoiceLine]].unique
  def getByPrimaryKeySeq(invoiceLineIdSeq: NonEmptyList[Long]) = (selectFragment ++ in(fr"invoiceLineId", invoiceLineIdSeq)).query[InvoiceLine].to[Seq]

  def countByInvoiceId(invoiceId: Long) = (countFragment ++ whereAnd(fr"invoiceId = $invoiceId")).query[Int].unique
  def getByInvoiceId(invoiceId: Long) = (selectFragment ++ whereAnd(fr"invoiceId = $invoiceId")).query[InvoiceLine].to[Seq]
  def getByInvoiceIdSeq(invoiceIdSeq: NonEmptyList[Long]) = (selectFragment ++ whereAnd(in(fr"invoiceId", invoiceIdSeq))).query[InvoiceLine].to[Seq]

  def countByTrackId(trackId: Long) = (countFragment ++ whereAnd(fr"trackId = $trackId")).query[Int].unique
  def getByTrackId(trackId: Long) = (selectFragment ++ whereAnd(fr"trackId = $trackId")).query[InvoiceLine].to[Seq]
  def getByTrackIdSeq(trackIdSeq: NonEmptyList[Long]) = (selectFragment ++ whereAnd(in(fr"trackId", trackIdSeq))).query[InvoiceLine].to[Seq]
}

