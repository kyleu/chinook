// Generated File
namespace java services.commerce

include "../../common.thrift"
include "../../result.thrift"
include "../../models/commerce/Invoice.thrift"

service InvoiceService {
  Invoice.Invoice getByPrimaryKey(
    1: common.Credentials creds,
    2: required common.long invoiceId
  )
  Invoice.Invoice getByPrimaryKeySeq(
    1: common.Credentials creds,
    2: required list<common.long> invoiceId
  )

  common.int countAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters
  )
  list<Invoice.Invoice> getAll(
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
  list<Invoice.Invoice> search(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters,
    4: list<result.OrderBy> orderBys,
    5: common.int limit,
    6: common.int offset
  )
  list<Invoice.Invoice> searchExact(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )


  Invoice.Invoice insert(
    1: common.Credentials creds,
    2: required Invoice.Invoice model
  )
  common.int insertBatch(
    1: common.Credentials creds,
    2: required list<Invoice.Invoice> models
  )
  Invoice.Invoice create(
    1: common.Credentials creds,
    2: required  common.long invoiceId,
    3: list<common.DataField> fields
  )
  Invoice.Invoice remove(
    1: common.Credentials creds,
    2: required  common.long invoiceId
  )
  Invoice.Invoice update(
    1: common.Credentials creds,
    2: required  common.long invoiceId,
    3: list<common.DataField> fields
  )
}
