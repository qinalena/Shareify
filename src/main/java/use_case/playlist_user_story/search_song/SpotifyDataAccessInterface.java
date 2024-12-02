package use_case.playlist_user_story.search_song;

import entity.Song;

import java.util.List;

/**
 * Interface for connecting to Spotify API.
 */
public interface SpotifyDataAccessInterface {

    /**
     * Returns the search results in a list of Songs.
     * @param query the search query
     * @return search results
     */
    List<Song> searchTrack(String query);
}