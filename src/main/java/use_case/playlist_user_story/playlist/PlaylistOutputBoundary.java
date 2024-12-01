package use_case.playlist_user_story.playlist;

import entity.Playlist;

/**
 * The output boundary for a Playlist.
 */
public interface PlaylistOutputBoundary {

    /**
     * Executes the remove song from playlist Use Case.
     * @param songIndex the index of the song to be removed
     */
    void removeSong(int songIndex);

    /**
     * Switches to Playlist Collection View.
     */
    void switchToPlaylistCollectionView();

    /**
     * Switches to Search Song View.
     * @param currentPlaylist the opened Playlist, so we can remember what playlist we're adding songs to
     */
    void switchToSearchSongView(Playlist currentPlaylist);

    /**
     * Failure view for data access exceptions.
     * @param message failure message to be displayed
     */
    void prepareFailView(String message);
}
