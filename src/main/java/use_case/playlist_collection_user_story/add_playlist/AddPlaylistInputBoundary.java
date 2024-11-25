package use_case.playlist_collection_user_story.add_playlist;

/**
 * The Input Boundary for our AddPlaylist use cases.
 */

public interface AddPlaylistInputBoundary {
    /**
     * Executes Playlist related use cases.
     * @param playlistName output data
     */
    void execute(String playlistName);

    /**
     * Switch to playlistCollection View.
     */
    void switchToPlaylistCollectionView();
}