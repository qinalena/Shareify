package spotify_api;

/**
 * Interface for connecting to Spotify API.
 */
public interface SpotifyConnectionInterface {

    /**
     *
     * @param songName
     * @return
     */
    String getSongName(String songName);

    /**
     *
     * @param songName
     * @return
     */
    String getSongArtist(String songName);

    /**
     *
     * @param songName
     * @return
     */
    String getSongAlbum(String songName);

    /**
     *
     * @param songName
     * @return
     */
    String getSongReleaseDate(String songName);

    /**
     *
     * @param songName
     * @return
     */
    String getSongTags(String songName);

    /**
     *
     * @param songName
     * @return
     */
    String getPreviewUrl(String songName);
}