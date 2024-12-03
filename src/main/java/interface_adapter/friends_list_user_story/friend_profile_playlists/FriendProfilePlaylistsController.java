package interface_adapter.friends_list_user_story.friend_profile_playlists;

import use_case.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsInputBoundary;

/**
 * Controller for friendProfilePlaylists.
 */
public class FriendProfilePlaylistsController {
    private final FriendProfilePlaylistsInputBoundary friendProfilePlaylistsInteractor;

    public FriendProfilePlaylistsController(FriendProfilePlaylistsInputBoundary friendProfilePlaylistsInteractor) {
        this.friendProfilePlaylistsInteractor = friendProfilePlaylistsInteractor;
    }

    /**
     * Executes the switch to friends profile view use case.
     * @param selectedFriendName the username.
     * @param password the password.
     */
    public void switchToFriendProfileView(String selectedFriendName, String password) {
        friendProfilePlaylistsInteractor.switchToFriendProfileView(selectedFriendName, password);
    }

    /**
     * Executes the switch to play list view use case.
     * @param playlistName the name of the playlist.
     * @param username the username.
     * @param password the password.
     */
    public void switchToPlaylistView(String playlistName, String username, String password) {
        friendProfilePlaylistsInteractor.switchToPlaylistView(playlistName, username, password);
    }
}
