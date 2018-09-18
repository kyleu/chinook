// Generated File
namespace java services.commerce

include "../../common.thrift"
include "../../result.thrift"
include "../../models/commerce/InvoiceLine.thrift"

service InvoiceLineService {
  InvoiceLine.InvoiceLine getByPrimaryKey(
    1: common.Credentials creds,
    2: required common.long invoiceLineId
  )
  InvoiceLine.InvoiceLine getByPrimaryKeySeq(
    1: common.Credentials creds,
    2: required list<common.long> invoiceLineId
  )

  common.int countAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters
  )
  list<InvoiceLine.InvoiceLine> getAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )

  common.int searchCount(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters
  )
  list<InvoiceLine.InvoiceLine> search(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters,
    4: list<result.OrderBy> orderBys,
    5: common.int limit,
    6: common.int offset
  )
  list<InvoiceLine.InvoiceLine> searchExact(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )


  InvoiceLine.InvoiceLine insert(
    1: common.Credentials creds,
    2: required InvoiceLine.InvoiceLine model
  )
  common.int insertBatch(
    1: common.Credentials creds,
    2: required list<InvoiceLine.InvoiceLine> models
  )
  InvoiceLine.InvoiceLine create(
    1: common.Credentials creds,
    2: required  common.long invoiceLineId,
    3: list<common.DataField> fields
  )
  InvoiceLine.InvoiceLine remove(
    1: common.Credentials creds,
    2: required  common.long invoiceLineId
  )
  InvoiceLine.InvoiceLine update(
    1: common.Credentials creds,
    2: required  common.long invoiceLineId,
    3: list<common.DataField> fields
  )
}
