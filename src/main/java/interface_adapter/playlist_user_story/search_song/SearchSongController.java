package interface_adapter.playlist_user_story.search_song;

import entity.Playlist;
import use_case.playlist_user_story.search_song.SearchSongInputBoundary;
import use_case.playlist_user_story.search_song.SearchSongInputData;

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

    /**
     * Add selected song to playlist.
     *
     * @param currentPlaylist the current playlist that we are adding the song to
     * @param selectedString the song that was selected in the list of search results
     */
    public void addSong(Playlist currentPlaylist, String selectedString) {
        if (selectedString != null) {
            final SearchSongInputData searchSongInputData = new SearchSongInputData(currentPlaylist, selectedString);
            searchTrackInteractor.addSong(searchSongInputData);
        }

    }
}
