// Generated File
namespace java services.media

include "../../common.thrift"
include "../../result.thrift"
include "../../models/media/Playlist.thrift"

service PlaylistService {
  Playlist.Playlist getByPrimaryKey(
    1: common.Credentials creds,
    2: required common.long playlistId
  )
  Playlist.Playlist getByPrimaryKeySeq(
    1: common.Credentials creds,
    2: required list<common.long> playlistId
  )

  common.int countAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters
  )
  list<Playlist.Playlist> getAll(
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
  list<Playlist.Playlist> search(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters,
    4: list<result.OrderBy> orderBys,
    5: common.int limit,
    6: common.int offset
  )
  list<Playlist.Playlist> searchExact(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )


  Playlist.Playlist insert(
    1: common.Credentials creds,
    2: required Playlist.Playlist model
  )
  common.int insertBatch(
    1: common.Credentials creds,
    2: required list<Playlist.Playlist> models
  )
  Playlist.Playlist create(
    1: common.Credentials creds,
    2: required  common.long playlistId,
    3: list<common.DataField> fields
  )
  Playlist.Playlist remove(
    1: common.Credentials creds,
    2: required  common.long playlistId
  )
  Playlist.Playlist update(
    1: common.Credentials creds,
    2: required  common.long playlistId,
    3: list<common.DataField> fields
  )
}
