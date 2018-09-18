// Generated File
namespace java services.media

include "../../common.thrift"
include "../../result.thrift"
include "../../models/media/PlaylistTrack.thrift"

service PlaylistTrackService {

  common.int countAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters
  )
  list<PlaylistTrack.PlaylistTrack> getAll(
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
  list<PlaylistTrack.PlaylistTrack> search(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters,
    4: list<result.OrderBy> orderBys,
    5: common.int limit,
    6: common.int offset
  )
  list<PlaylistTrack.PlaylistTrack> searchExact(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )


  PlaylistTrack.PlaylistTrack insert(
    1: common.Credentials creds,
    2: required PlaylistTrack.PlaylistTrack model
  )
  common.int insertBatch(
    1: common.Credentials creds,
    2: required list<PlaylistTrack.PlaylistTrack> models
  )
}
