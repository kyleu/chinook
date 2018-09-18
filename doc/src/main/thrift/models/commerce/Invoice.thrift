// Generated File
namespace java models.commerce

include "../../common.thrift"
include "../../result.thrift"

struct Invoice {
  1: required common.long invoiceId;
  2: required common.long customerId;
  3: required common.LocalDateTime invoiceDate;
  4: optional string billingAddress;
  5: optional string billingCity;
  6: optional string billingState;
  7: optional string billingCountry;
  8: optional string billingPostalCode;
  9: required common.BigDecimal total;
}

struct InvoiceResult {
  1: required list<result.Filter> filters;
  2: required list<result.OrderBy> orderBys;
  3: required common.int totalCount;
  4: required result.PagingOptions paging;
  5: required list<Invoice> results;
  6: required common.int durationMs;
  7: required common.LocalDateTime occurred;
}
