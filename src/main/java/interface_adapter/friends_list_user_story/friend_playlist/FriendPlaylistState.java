package interface_adapter.friends_list_user_story.friend_playlist;

import entity.Playlist;

public class FriendPlaylistState {
    private Playlist currentPlaylist;

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }
}
