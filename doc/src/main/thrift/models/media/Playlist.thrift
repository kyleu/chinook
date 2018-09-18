// Generated File
namespace java models.media

include "../../common.thrift"
include "../../result.thrift"

struct Playlist {
  1: required common.long playlistId;
  2: optional string name;
}

struct PlaylistResult {
  1: required list<result.Filter> filters;
  2: required list<result.OrderBy> orderBys;
  3: required common.int totalCount;
  4: required result.PagingOptions paging;
  5: required list<Playlist> results;
  6: required common.int durationMs;
  7: required common.LocalDateTime occurred;
}
