package use_case.friends_list_user_story.friend_profile_playlists;

import entity.Playlist;

/**
 * The output boundary interface for the Friend Profile Playlists use case.
 * It defines methods for updating the views based on the results of playlist-related actions.
 */
public interface FriendProfilePlaylistsOutputBoundary {

    /**
     * Prepares the view for when a playlist has been successfully added.
     *
     * @param playlistName The name of the playlist that was added.
     */
    void preparePlaylistAddedView(String playlistName);

    /**
     * Prepares the view for when a playlist has been successfully removed.
     *
     * @param playlistName The name of the playlist that was removed.
     */
    void preparePlaylistRemovedView(String playlistName);

    /**
     * Prepares the failure view when an error occurs in playlist-related actions.
     * This method provides an error message to be displayed in the view.
     *
     * @param error A string message describing the error.
     */
    void prepareFailView(String error);

    /**
     * Switches to the Friend Profile View for the selected friend.
     *
     * @param selectedFriendName The name of the selected friend.
     * @param password The password of the selected friend.
     */
    void switchToFriendProfileView(String selectedFriendName, String password);

    /**
     * Switches to the Playlist View, displaying the specified playlist.
     *
     * @param playlist The playlist data to display.
     * @param username The username of the user viewing the playlist.
     * @param password The password of the user viewing the playlist.
     */
    void switchToPlaylistView(Playlist playlist, String username, String password);
}
