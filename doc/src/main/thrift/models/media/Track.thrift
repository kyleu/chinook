// Generated File
namespace java models.media

include "../../common.thrift"
include "../../result.thrift"

struct Track {
  1: required common.long trackId;
  2: required string name;
  3: optional common.long albumId;
  4: required common.long mediaTypeId;
  5: optional common.long genreId;
  6: optional string composer;
  7: required common.long milliseconds;
  8: optional common.long bytes;
  9: required common.BigDecimal unitPrice;
}

struct TrackResult {
  1: required list<result.Filter> filters;
  2: required list<result.OrderBy> orderBys;
  3: required common.int totalCount;
  4: required result.PagingOptions paging;
  5: required list<Track> results;
  6: required common.int durationMs;
  7: required common.LocalDateTime occurred;
}
