package interface_adapter.friends_list_user_story.friend_playlist;

import entity.Playlist;

/**
 * State for FriendPlaylist.
 */
public class FriendPlaylistState {
    private Playlist currentPlaylist;
    private String friendUsername;
    private String friendPassword;

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }

    public String getFriendPassword() {
        return friendPassword;
    }

    public void setFriendPassword(String friendPassword) {
        this.friendPassword = friendPassword;
    }
}
