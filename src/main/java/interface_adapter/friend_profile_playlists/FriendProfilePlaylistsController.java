package interface_adapter.friend_profile_playlists;

import use_case.friend_profile_playlists.FriendProfilePlaylistsInputBoundary;

public class FriendProfilePlaylistsController {
    private final FriendProfilePlaylistsInputBoundary friendProfilePlaylistsInteractor;

    public FriendProfilePlaylistsController(FriendProfilePlaylistsInputBoundary friendProfilePlaylistsInteractor) {
        this.friendProfilePlaylistsInteractor = friendProfilePlaylistsInteractor;
    }

    public void addPlaylist(String playlistName) {
        friendProfilePlaylistsInteractor.addPlaylist(playlistName);
    }

    public void removePlaylist(String playlistName) {
        friendProfilePlaylistsInteractor.removePlaylist(playlistName);
    }
}
