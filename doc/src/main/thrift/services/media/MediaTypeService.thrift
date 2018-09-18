// Generated File
namespace java services.media

include "../../common.thrift"
include "../../result.thrift"
include "../../models/media/MediaType.thrift"

service MediaTypeService {
  MediaType.MediaType getByPrimaryKey(
    1: common.Credentials creds,
    2: required common.long mediaTypeId
  )
  MediaType.MediaType getByPrimaryKeySeq(
    1: common.Credentials creds,
    2: required list<common.long> mediaTypeId
  )

  common.int countAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters
  )
  list<MediaType.MediaType> getAll(
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
  list<MediaType.MediaType> search(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters,
    4: list<result.OrderBy> orderBys,
    5: common.int limit,
    6: common.int offset
  )
  list<MediaType.MediaType> searchExact(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )


  MediaType.MediaType insert(
    1: common.Credentials creds,
    2: required MediaType.MediaType model
  )
  common.int insertBatch(
    1: common.Credentials creds,
    2: required list<MediaType.MediaType> models
  )
  MediaType.MediaType create(
    1: common.Credentials creds,
    2: required  common.long mediaTypeId,
    3: list<common.DataField> fields
  )
  MediaType.MediaType remove(
    1: common.Credentials creds,
    2: required  common.long mediaTypeId
  )
  MediaType.MediaType update(
    1: common.Credentials creds,
    2: required  common.long mediaTypeId,
    3: list<common.DataField> fields
  )
}
