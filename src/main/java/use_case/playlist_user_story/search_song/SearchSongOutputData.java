package use_case.playlist_user_story.search_song;

import entity.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * The output data for Search Track.
 */
public class SearchSongOutputData {
    private List<Song> searchResults = new ArrayList<Song>();

    /**
     * Add Song to searchResults.
     * @param song the track to be added
     */
    public void addSong(Song song) {
        searchResults.add(song);
    }
}
