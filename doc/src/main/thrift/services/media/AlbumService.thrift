// Generated File
namespace java services.media

include "../../common.thrift"
include "../../result.thrift"
include "../../models/media/Album.thrift"

service AlbumService {
  Album.Album getByPrimaryKey(
    1: common.Credentials creds,
    2: required common.long albumId
  )
  Album.Album getByPrimaryKeySeq(
    1: common.Credentials creds,
    2: required list<common.long> albumId
  )

  common.int countAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters
  )
  list<Album.Album> getAll(
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
  list<Album.Album> search(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters,
    4: list<result.OrderBy> orderBys,
    5: common.int limit,
    6: common.int offset
  )
  list<Album.Album> searchExact(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )


  Album.Album insert(
    1: common.Credentials creds,
    2: required Album.Album model
  )
  common.int insertBatch(
    1: common.Credentials creds,
    2: required list<Album.Album> models
  )
  Album.Album create(
    1: common.Credentials creds,
    2: required  common.long albumId,
    3: list<common.DataField> fields
  )
  Album.Album remove(
    1: common.Credentials creds,
    2: required  common.long albumId
  )
  Album.Album update(
    1: common.Credentials creds,
    2: required  common.long albumId,
    3: list<common.DataField> fields
  )
}
