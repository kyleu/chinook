// Generated File
namespace java models.media

include "../../common.thrift"
include "../../result.thrift"

struct Album {
  1: required common.long albumId;
  2: required string title;
  3: required common.long artistId;
}

struct AlbumResult {
  1: required list<result.Filter> filters;
  2: required list<result.OrderBy> orderBys;
  3: required common.int totalCount;
  4: required result.PagingOptions paging;
  5: required list<Album> results;
  6: required common.int durationMs;
  7: required common.LocalDateTime occurred;
}
