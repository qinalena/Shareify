package interface_adapter.playlist_user_story.search_song;

import entity.Playlist;
import entity.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * The state of Search Song.
 */
public class SearchSongState {
    private Playlist currentPlaylist;
    private List<Song> searchResults = new ArrayList<>();
    private String error;

    public void setCurrentSearchResults(List<Song> searchResults) {
        this.searchResults = searchResults;
    }

    public List<Song> getSearchResults() {
        return searchResults;
    }

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
