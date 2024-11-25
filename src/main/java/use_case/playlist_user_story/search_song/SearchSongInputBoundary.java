package use_case.playlist_user_story.search_song;

/**
 * The Input Boundary for SearchTrack.
 */
public interface SearchSongInputBoundary {

    /**
     * Executes the search track Use Case.
     * @param query the text inputted by the user
     */
    void searchSong(String query);

    /**
     * Switches to Playlist View.
     */
    void switchToPlaylistView();
}
