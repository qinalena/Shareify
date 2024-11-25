package interface_adapter.playlist_user_story.search_song;

import use_case.playlist_user_story.search_song.SearchSongInputBoundary;

/**
 * Controller for our Search Track related Use Cases.
 */
public class SearchSongController {

    private final SearchSongInputBoundary searchTrackInteractor;

    public SearchSongController(SearchSongInputBoundary searchTrackInteractor) {
        this.searchTrackInteractor = searchTrackInteractor;
    }

    /**
     * Executes the search track Use Case.
     * @param query the text inputted by the user
     */
    public void searchSong(String query) {
        searchTrackInteractor.searchSong(query);
    }

    /**
     * Switches to Playlist View.
     */
    public void switchToPlaylistView() {
        searchTrackInteractor.switchToPlaylistView();
    }
}
