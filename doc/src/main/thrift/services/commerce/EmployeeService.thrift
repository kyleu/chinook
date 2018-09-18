// Generated File
namespace java services.commerce

include "../../common.thrift"
include "../../result.thrift"
include "../../models/commerce/Employee.thrift"

service EmployeeService {
  Employee.Employee getByPrimaryKey(
    1: common.Credentials creds,
    2: required common.long employeeId
  )
  Employee.Employee getByPrimaryKeySeq(
    1: common.Credentials creds,
    2: required list<common.long> employeeId
  )

  common.int countAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters
  )
  list<Employee.Employee> getAll(
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
  list<Employee.Employee> search(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters,
    4: list<result.OrderBy> orderBys,
    5: common.int limit,
    6: common.int offset
  )
  list<Employee.Employee> searchExact(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )


  Employee.Employee insert(
    1: common.Credentials creds,
    2: required Employee.Employee model
  )
  common.int insertBatch(
    1: common.Credentials creds,
    2: required list<Employee.Employee> models
  )
  Employee.Employee create(
    1: common.Credentials creds,
    2: required  common.long employeeId,
    3: list<common.DataField> fields
  )
  Employee.Employee remove(
    1: common.Credentials creds,
    2: required  common.long employeeId
  )
  Employee.Employee update(
    1: common.Credentials creds,
    2: required  common.long employeeId,
    3: list<common.DataField> fields
  )
}
