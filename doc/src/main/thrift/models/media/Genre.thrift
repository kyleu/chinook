// Generated File
namespace java models.media

include "../../common.thrift"
include "../../result.thrift"

struct Genre {
  1: required common.long genreId;
  2: optional string name;
}

struct GenreResult {
  1: required list<result.Filter> filters;
  2: required list<result.OrderBy> orderBys;
  3: required common.int totalCount;
  4: required result.PagingOptions paging;
  5: required list<Genre> results;
  6: required common.int durationMs;
  7: required common.LocalDateTime occurred;
}
