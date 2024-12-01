package use_case.playlist_user_story.search_song;

import se.michaelthelin.spotify.model_objects.specification.Track;

/**
 * Interface for connecting to Spotify API.
 */
public interface SpotifyConnectionInterface {

    /**
     *
     * @param query
     * @return search results
     */
    Track[] searchTrack(String query);
}