// Generated File
namespace java models.commerce

include "../../common.thrift"
include "../../result.thrift"

struct Employee {
  1: required common.long employeeId;
  2: required string lastName;
  3: required string firstName;
  4: optional string title;
  5: optional common.long reportsTo;
  6: optional common.LocalDateTime birthDate;
  7: optional common.LocalDateTime hireDate;
  8: optional string address;
  9: optional string city;
  10: optional string state;
  11: optional string country;
  12: optional string postalCode;
  13: optional string phone;
  14: optional string fax;
  15: optional string email;
}

struct EmployeeResult {
  1: required list<result.Filter> filters;
  2: required list<result.OrderBy> orderBys;
  3: required common.int totalCount;
  4: required result.PagingOptions paging;
  5: required list<Employee> results;
  6: required common.int durationMs;
  7: required common.LocalDateTime occurred;
}
