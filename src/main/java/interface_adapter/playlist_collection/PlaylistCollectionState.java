package interface_adapter.playlist_collection;

import java.util.ArrayList;
import java.util.List;

/**
 * The state for Playlist Collection View Model.
 */

public class PlaylistCollectionState {

    private String playlistError;
    private List<String> playlistList = new ArrayList<>();

    public List<String> getPlaylist() {
        return new ArrayList<>(playlistList);
    }

    public void addPlaylist(String playlist) {
        playlistList.add(playlist);
    }

    public void removePlaylist(String playlist) {
        playlistList.remove(playlist);
    }

    public String getPlaylistError() {
        return playlistError;
    }

    public void setPlaylistError(String playlistError) {
        this.playlistError = playlistError;
    }
}