package use_case.user_profile;

/**
 * The Input Boundary for our User Profile use cases.
 */
public interface PlaylistCollectionInputBoundary {
    /**
     * Executes the switch to Note view use case.
     */
    void switchToNoteView();

    /**
     * Executes the switch to playlist view use case.
     */
    void switchToPlaylistCollectionView();

}
