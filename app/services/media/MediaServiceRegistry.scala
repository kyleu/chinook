/* Generated File */
package services.media

@javax.inject.Singleton
class MediaServiceRegistry @javax.inject.Inject() (
    val albumService: services.media.AlbumService,
    val artistService: services.media.ArtistService,
    val genreService: services.media.GenreService,
    val mediaTypeService: services.media.MediaTypeService,
    val playlistService: services.media.PlaylistService,
    val playlistTrackService: services.media.PlaylistTrackService,
    val trackService: services.media.TrackService
)
