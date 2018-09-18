// Generated File
namespace java models.commerce

include "../../common.thrift"
include "../../result.thrift"

struct Customer {
  1: required common.long customerId;
  2: required string firstName;
  3: required string lastName;
  4: optional string company;
  5: optional string address;
  6: optional string city;
  7: optional string state;
  8: optional string country;
  9: optional string postalCode;
  10: optional string phone;
  11: optional string fax;
  12: required string email;
  13: optional common.long supportRepId;
}

struct CustomerResult {
  1: required list<result.Filter> filters;
  2: required list<result.OrderBy> orderBys;
  3: required common.int totalCount;
  4: required result.PagingOptions paging;
  5: required list<Customer> results;
  6: required common.int durationMs;
  7: required common.LocalDateTime occurred;
}
