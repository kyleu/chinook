// Generated File
namespace java services.media

include "../../common.thrift"
include "../../result.thrift"
include "../../models/media/Track.thrift"

service TrackService {
  Track.Track getByPrimaryKey(
    1: common.Credentials creds,
    2: required common.long trackId
  )
  Track.Track getByPrimaryKeySeq(
    1: common.Credentials creds,
    2: required list<common.long> trackId
  )

  common.int countAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters
  )
  list<Track.Track> getAll(
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
  list<Track.Track> search(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters,
    4: list<result.OrderBy> orderBys,
    5: common.int limit,
    6: common.int offset
  )
  list<Track.Track> searchExact(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )


  Track.Track insert(
    1: common.Credentials creds,
    2: required Track.Track model
  )
  common.int insertBatch(
    1: common.Credentials creds,
    2: required list<Track.Track> models
  )
  Track.Track create(
    1: common.Credentials creds,
    2: required  common.long trackId,
    3: list<common.DataField> fields
  )
  Track.Track remove(
    1: common.Credentials creds,
    2: required  common.long trackId
  )
  Track.Track update(
    1: common.Credentials creds,
    2: required  common.long trackId,
    3: list<common.DataField> fields
  )
}
