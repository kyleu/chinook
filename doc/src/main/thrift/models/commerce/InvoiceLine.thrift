// Generated File
namespace java models.commerce

include "../../common.thrift"
include "../../result.thrift"

struct InvoiceLine {
  1: required common.long invoiceLineId;
  2: required common.long invoiceId;
  3: required common.long trackId;
  4: required common.BigDecimal unitPrice;
  5: required common.long quantity;
}

struct InvoiceLineResult {
  1: required list<result.Filter> filters;
  2: required list<result.OrderBy> orderBys;
  3: required common.int totalCount;
  4: required result.PagingOptions paging;
  5: required list<InvoiceLine> results;
  6: required common.int durationMs;
  7: required common.LocalDateTime occurred;
}
