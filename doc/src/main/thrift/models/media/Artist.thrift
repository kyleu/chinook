// Generated File
namespace java models.media

include "../../common.thrift"
include "../../result.thrift"

struct Artist {
  1: required common.long artistId;
  2: optional string name;
}

struct ArtistResult {
  1: required list<result.Filter> filters;
  2: required list<result.OrderBy> orderBys;
  3: required common.int totalCount;
  4: required result.PagingOptions paging;
  5: required list<Artist> results;
  6: required common.int durationMs;
  7: required common.LocalDateTime occurred;
}
