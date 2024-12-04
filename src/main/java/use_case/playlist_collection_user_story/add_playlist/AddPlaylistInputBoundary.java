package use_case.playlist_collection_user_story.add_playlist;

/**
 * The Input Boundary for our AddPlaylist use cases.
 */

public interface AddPlaylistInputBoundary {
    /**
     * Executes add Playlist related use cases.
     * @param playlistName output data
     */
    void executeCreatePlaylist(String playlistName);

    /**
     * Switch to playlistCollection View.
     */
    void switchToPlaylistCollectionView();
}
