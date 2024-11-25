package use_case.playlist_collection_user_story.add_playlist;

import java.util.List;

/**
 * Output Boundary for addPlaylist.
 */

public interface AddPlaylistOutputBoundary {

    /**
     * Prepares success view for taking a list of playlists.
     * @param updatePlaylist output data
     */
    void prepareSuccessView(List<String> updatePlaylist);

    /**
     * Prepares failure view.
     * @param errorMessage error
     */
    void prepareFailureView(String errorMessage);

    /**
     * Switches to playlist collection view.
     */
    void switchToPlaylistCollectionView();
}