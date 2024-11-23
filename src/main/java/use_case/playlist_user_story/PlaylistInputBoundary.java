package use_case.playlist_user_story;

/**
 * The Input Boundary for our Playlist use cases.
 */
public interface PlaylistInputBoundary {

    /**
     * Executes the remove track from playlist Use Case.
     */
    void removeTrack();

    /**
     * Switches to Search Tracks View.
     */
    void switchToSearchTracksView();

    /**
     * Switches to Playlist Collection View.
     */
    void switchToPlaylistCollectionView();
}
