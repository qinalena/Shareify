package use_case.playlist_user_story.playlist;

/**
 * The output boundary for a Playlist.
 */
public interface PlaylistOutputBoundary {

    /**
     * Executes the remove track from playlist Use Case.
     */
    void removeSong(int songIndex);

    /**
     * Switches to Playlist Collection View.
     */
    void switchToPlaylistCollectionView();

    /**
     * Switches to Search Tracks View.
     */
    void switchToSearchTracksView();
}
