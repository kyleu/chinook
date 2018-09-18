// Generated File
namespace java services.commerce

include "../../common.thrift"
include "../../result.thrift"
include "../../models/commerce/Customer.thrift"

service CustomerService {
  Customer.Customer getByPrimaryKey(
    1: common.Credentials creds,
    2: required common.long customerId
  )
  Customer.Customer getByPrimaryKeySeq(
    1: common.Credentials creds,
    2: required list<common.long> customerId
  )

  common.int countAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters
  )
  list<Customer.Customer> getAll(
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
  list<Customer.Customer> search(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters,
    4: list<result.OrderBy> orderBys,
    5: common.int limit,
    6: common.int offset
  )
  list<Customer.Customer> searchExact(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )


  Customer.Customer insert(
    1: common.Credentials creds,
    2: required Customer.Customer model
  )
  common.int insertBatch(
    1: common.Credentials creds,
    2: required list<Customer.Customer> models
  )
  Customer.Customer create(
    1: common.Credentials creds,
    2: required  common.long customerId,
    3: list<common.DataField> fields
  )
  Customer.Customer remove(
    1: common.Credentials creds,
    2: required  common.long customerId
  )
  Customer.Customer update(
    1: common.Credentials creds,
    2: required  common.long customerId,
    3: list<common.DataField> fields
  )
}
