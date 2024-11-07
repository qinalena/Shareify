package spotifyAPI;

/**
 * interface for spotify access
 */
public interface SpotifyServiceInterface {
    String getSongName(String songName);
    String getSongArtist(String songName);
    String getSongAlbum(String songName);
    String getSongReleaseDate(String songName);
    String getSongTags(String songName);
    String getPreviewUrl(String songName);
}