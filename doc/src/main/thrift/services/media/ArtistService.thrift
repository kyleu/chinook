// Generated File
namespace java services.media

include "../../common.thrift"
include "../../result.thrift"
include "../../models/media/Artist.thrift"

service ArtistService {
  Artist.Artist getByPrimaryKey(
    1: common.Credentials creds,
    2: required common.long artistId
  )
  Artist.Artist getByPrimaryKeySeq(
    1: common.Credentials creds,
    2: required list<common.long> artistId
  )

  common.int countAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters
  )
  list<Artist.Artist> getAll(
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
  list<Artist.Artist> search(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters,
    4: list<result.OrderBy> orderBys,
    5: common.int limit,
    6: common.int offset
  )
  list<Artist.Artist> searchExact(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )


  Artist.Artist insert(
    1: common.Credentials creds,
    2: required Artist.Artist model
  )
  common.int insertBatch(
    1: common.Credentials creds,
    2: required list<Artist.Artist> models
  )
  Artist.Artist create(
    1: common.Credentials creds,
    2: required  common.long artistId,
    3: list<common.DataField> fields
  )
  Artist.Artist remove(
    1: common.Credentials creds,
    2: required  common.long artistId
  )
  Artist.Artist update(
    1: common.Credentials creds,
    2: required  common.long artistId,
    3: list<common.DataField> fields
  )
}
