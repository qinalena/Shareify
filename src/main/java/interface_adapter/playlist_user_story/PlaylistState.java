package interface_adapter.playlist_user_story;

import entity.Playlist;

/**
 * The State for a Playlist.
 */
public class PlaylistState {
    private String currentUsername;
    private Playlist currentPlaylist;

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }
}
