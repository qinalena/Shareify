package use_case.friends_list_user_story.friend_profile_playlists;

/**
 * Input boundary for friend profile playlists.
 */
public interface FriendProfilePlaylistsInputBoundary {

    /**
     * Switches to the Friend Profile View for the selected friend.
     *
     * @param selectedFriendName The name of the selected friend to view.
     * @param password The password for the selected friend.
     */
    void switchToFriendProfileView(String selectedFriendName, String password);

    /**
     * Prepares failure view for playlist collection use cases.
     * @param error message indicating issue
     */
    void prepareFailView(String error);

    /**
     * Switches to Playlist View.
     *
     * @param playlistName is the name of the playlist
     */
    void switchToPlaylistView(String playlistName, String username, String password);
}
