package use_case.playlist_user_story.search_song;
import java.util.List;

/**
 * The output boundary for Search Track.
 */
public interface SearchSongOutputBoundary {

    /**
     * Executes the search song Use Case.
     *
     * @param displaySearchResults the output data
     */
    void searchSong(List<String> displaySearchResults);

    /**
     * Switches to Playlist View.
     */
    void switchToPlaylistView();

    /**
     * Executes the add song Use case.
     * @param selectedSong the selected Song.
     */
    void addSong(String selectedSong);

    /**
     * Failure view for data access exceptions.
     * @param message failure message to be displayed
     */
    void prepareFailView(String message);
}
