package use_case.playlist_collection_user_story.playlist_collection;

/**
 * The Input Boundary for our Playlist Collection use cases.
 */

public interface PlaylistCollectionInputBoundary {

    /**
     * Executes the add playlist use case.
     * @param playlist output data
     */
    void addPlaylist(String playlist);

    /**
     * Executes the delete playlist use case.
     * @param playlist output data
     */
    void removePlaylist(String playlist);

    /**
     * Switches to playlist view.
     * @param playlistName the name of the selected playlist
     */
    void switchToPlaylistView(String playlistName);
}