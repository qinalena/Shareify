package use_case.playlist_user_story.playlist;

/**
 * The Input Boundary for our Playlist use cases.
 */
public interface PlaylistInputBoundary {

    /**
     * Executes the remove track from playlist Use Case.
     *
     * @param currentPlaylistName
     * @param songIndex
     */
    void removeSong(String currentPlaylistName, int songIndex);

    /**
     * Switches to Search Song View.
     *
     * @param currentPlaylistName the opened Playlist, so we can remember what playlist we're adding songs to
     */
    void switchToSearchSongView(String currentPlaylistName);

    /**
     * Switches to Playlist Collection View.
     */
    void switchToPlaylistCollectionView();
}
