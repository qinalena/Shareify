package use_case.playlist_collection;

/**
 * The output boundary for Playlist Collection.
 */
public interface PlaylistCollectionOutputBoundary {

    /**
     * Prepares success view for playlist collection use cases.
     * @param message ouput data
     */
    void prepareSuccessView(String message);

    /**
     * Prepares failure view for playlist collection use cases.
     * @param error message indicating issue
     */
    void prepareFailView(String error);

    /**
     * Switches to the PlaylistCollen View.
     */
    void switchToPlaylistCollectionView();
}
