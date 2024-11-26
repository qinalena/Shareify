package use_case.playlist_user_story.search_song;

/**
 * The output boundary for Search Track.
 */
public interface SearchSongOutputBoundary {

    /**
     * Executes the search song Use Case.
     * @param searchSongOutputData the output data
     */
    void searchSong(SearchSongOutputData searchSongOutputData);

    /**
     * Switches to Playlist View.
     */
    void switchToPlaylistView();

    /**
     * Executes the add song Use case.
     * @param selectedSong the selected Song.
     */
    void addSong(SearchSongInputData selectedSong);
}
