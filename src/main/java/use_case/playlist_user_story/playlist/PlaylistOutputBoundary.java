package use_case.playlist_user_story.playlist;

import entity.Playlist;

/**
 * The output boundary for a Playlist.
 */
public interface PlaylistOutputBoundary {

    /**
     * Executes the remove track from playlist Use Case.
     * @param songIndex
     */
    void removeSong(int songIndex);

    /**
     * Switches to Playlist Collection View.
     */
    void switchToPlaylistCollectionView();

    /**
     * Switches to Search Tracks View.
     * @param currentPlaylist the opened Playlist, so we can remember what playlist we're adding songs to
     */
    void switchToSearchSongView(Playlist currentPlaylist);
}
