package interface_adapter.playlist_user_story.search_song;

import java.util.ArrayList;
import java.util.List;

/**
 * The state of Search Song.
 */
public class SearchSongState {
    private String currentPlaylistName;
    private List<String> searchResults = new ArrayList<>();
    private String error;

    public void setCurrentSearchResults(List<String> searchResults) {
        this.searchResults = searchResults;
    }

    public List<String> getSearchResults() {
        return searchResults;
    }

    public void setCurrentPlaylistName(String currentPlaylistName) {
        this.currentPlaylistName = currentPlaylistName;
    }

    public String getCurrentPlaylistName() {
        return currentPlaylistName;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
