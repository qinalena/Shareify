package use_case.add_playlist;

/**
 * The Input Boundary for our AddPlaylist use cases.
 */

public interface AddPlaylistInputBoundary {
    /**
     * Executes Playlist related use cases.
     * @param playlistName output data
     */
    void execute(String playlistName);
}
