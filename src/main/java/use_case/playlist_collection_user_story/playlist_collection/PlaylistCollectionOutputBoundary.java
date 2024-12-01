package use_case.playlist_collection_user_story.playlist_collection;

/**
 * The output boundary for Playlist Collection.
 */
public interface PlaylistCollectionOutputBoundary {

    /**
     * Prepares success view for playlist collection use cases.
     * @param playlistName output data
     */
    void preparePlaylistAddedView(String playlistName);

    /**
     * Prepare remove playlist case.
     * @param playlistName output data
     */
    void preparePlaylistRemovedView(String playlistName);

    /**
     * Prepares failure view for playlist collection use cases.
     * @param error message indicating issue
     */
    void prepareFailView(String error);

    /**
     * Switches to Playlist View.
     *
     * @param playlistCollectionOutputData output data
     */
    void switchToPlaylistView(PlaylistCollectionOutputData playlistCollectionOutputData);

    /**
     * Switches to UserProfileView - for back button in Playlist Collection View.
     */
    void switchToUserProfileView();

    /**
     * Switches to AddPlaylistView.
     */
    void switchToAddPlaylistView();
}
