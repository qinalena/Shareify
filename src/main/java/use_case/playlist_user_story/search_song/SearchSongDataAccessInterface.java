package use_case.playlist_user_story.search_song;

import se.michaelthelin.spotify.model_objects.specification.Track;

/**
 * Data Access Interface for Search Track.
 */
public interface SearchSongDataAccessInterface {

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
     * @param query
     * @return search results
     */
    Track[] searchTrack(String query);
}
