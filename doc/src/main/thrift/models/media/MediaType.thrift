// Generated File
namespace java models.media

include "../../common.thrift"
include "../../result.thrift"

struct MediaType {
  1: required common.long mediaTypeId;
  2: optional string name;
}

struct MediaTypeResult {
  1: required list<result.Filter> filters;
  2: required list<result.OrderBy> orderBys;
  3: required common.int totalCount;
  4: required result.PagingOptions paging;
  5: required list<MediaType> results;
  6: required common.int durationMs;
  7: required common.LocalDateTime occurred;
}
