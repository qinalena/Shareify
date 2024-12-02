package interface_adapter.friends_list_user_story.friend_profile_playlists;

import use_case.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsInputBoundary;

public class FriendProfilePlaylistsController {
    private final FriendProfilePlaylistsInputBoundary friendProfilePlaylistsInteractor;

    public FriendProfilePlaylistsController(FriendProfilePlaylistsInputBoundary friendProfilePlaylistsInteractor) {
        this.friendProfilePlaylistsInteractor = friendProfilePlaylistsInteractor;
    }

    public void switchToFriendProfileView(String selectedFriendName, String password) {
        friendProfilePlaylistsInteractor.switchToFriendProfileView(selectedFriendName, password);
    }

    public void switchToPlaylistView(String playlistName, String username, String password) {
        friendProfilePlaylistsInteractor.switchToPlaylistView(playlistName, username, password);
    }
}
