package use_case.friends_list_user_story.friend_profile_playlists;

public interface FriendProfilePlaylistsInputBoundary {

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
