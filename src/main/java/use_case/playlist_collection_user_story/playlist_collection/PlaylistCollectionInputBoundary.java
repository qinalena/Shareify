package use_case.playlist_collection_user_story.playlist_collection;

import entity.User;

/**
 * The Input Boundary for our Playlist Collection use cases.
 */

public interface PlaylistCollectionInputBoundary {

    /**
     * Executes the add playlist use case.
     * @param user user info
     * @param playlistName key of playlist (playlistName)
     */
    void addPlaylist(User user, String playlistName);

    /**
     * Executes the delete playlist use case.
     * @param user user info
     * @param playlistName key of playlist (playlistName)
     */
    void removePlaylist(User user, String playlistName);

    /**
     * Switches to playlist view.
     * @param playlistName the name of the selected playlist
     */
    void switchToPlaylistView(String playlistName);

    /**
     * Executes switch to UserProfile view use case.
     */
    void switchToUserProfileView();

    /**
     * Executes switch to AddPlaylist view use case.
     */
    void switchToAddPlaylistView();
}
