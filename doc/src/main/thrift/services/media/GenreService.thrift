// Generated File
namespace java services.media

include "../../common.thrift"
include "../../result.thrift"
include "../../models/media/Genre.thrift"

service GenreService {
  Genre.Genre getByPrimaryKey(
    1: common.Credentials creds,
    2: required common.long genreId
  )
  Genre.Genre getByPrimaryKeySeq(
    1: common.Credentials creds,
    2: required list<common.long> genreId
  )

  common.int countAll(
    1: common.Credentials creds,
    2: list<result.Filter> filters
  )
  list<Genre.Genre> getAll(
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
  list<Genre.Genre> search(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.Filter> filters,
    4: list<result.OrderBy> orderBys,
    5: common.int limit,
    6: common.int offset
  )
  list<Genre.Genre> searchExact(
    1: common.Credentials creds,
    2: required string q,
    3: list<result.OrderBy> orderBys,
    4: common.int limit,
    5: common.int offset
  )


  Genre.Genre insert(
    1: common.Credentials creds,
    2: required Genre.Genre model
  )
  common.int insertBatch(
    1: common.Credentials creds,
    2: required list<Genre.Genre> models
  )
  Genre.Genre create(
    1: common.Credentials creds,
    2: required  common.long genreId,
    3: list<common.DataField> fields
  )
  Genre.Genre remove(
    1: common.Credentials creds,
    2: required  common.long genreId
  )
  Genre.Genre update(
    1: common.Credentials creds,
    2: required  common.long genreId,
    3: list<common.DataField> fields
  )
}
