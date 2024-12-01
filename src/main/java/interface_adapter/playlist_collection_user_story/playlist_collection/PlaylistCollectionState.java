package interface_adapter.playlist_collection_user_story.playlist_collection;

import java.util.ArrayList;
import java.util.List;

/**
 * The state for Playlist Collection View Model.
 */

public class PlaylistCollectionState {
    private final List<String> playlists = new ArrayList<>();
    private String playlistError;
    private String username;
    private String password;

    public List<String> getPlaylist() {
        return playlists;
    }

    /**
     * Adds playlist to list of playlists.
     * @param playlist the playlist
     */
    public void addPlaylist(String playlist) {
        playlists.add(playlist);
    }

    /**
     * Remove playlist from list lof playlists.
     * @param playlist the playlist
     */
    public void removePlaylist(String playlist) {
        playlists.remove(playlist);
    }

    public String getPlaylistError() {
        return playlistError;
    }

    public void setPlaylistError(String playlistError) {
        this.playlistError = playlistError;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}