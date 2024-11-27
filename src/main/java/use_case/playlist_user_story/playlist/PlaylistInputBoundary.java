package use_case.playlist_user_story.playlist;

import entity.Playlist;

/**
 * The Input Boundary for our Playlist use cases.
 */
public interface PlaylistInputBoundary {

    /**
     * Executes the remove track from playlist Use Case.
     * @param songIndex
     */
    void removeSong(int songIndex);

    /**
     * Switches to Search Song View.
     * @param currentPlaylist the opened Playlist, so we can remember what playlist we're adding songs to
     */
    void switchToSearchSongView(Playlist currentPlaylist);

    /**
     * Switches to Playlist Collection View.
     */
    void switchToPlaylistCollectionView();
}
