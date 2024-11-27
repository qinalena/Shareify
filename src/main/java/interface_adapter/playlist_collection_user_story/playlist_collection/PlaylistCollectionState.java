package interface_adapter.playlist_collection_user_story.playlist_collection;

import entity.Playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * The state for Playlist Collection View Model.
 */

public class PlaylistCollectionState {

    private String playlistError;
    private List<String> playlistList = new ArrayList<>();
    private List<Playlist> playlistCollection = new ArrayList<>();

    public List<Playlist> getPlaylistCollection() {
        return playlistCollection;
    }

    public void addPlaylistToPlaylistCollection(Playlist playlist) {
        playlistCollection.add(playlist);
    }

    public List<String> getPlaylistList() {
        return playlistList;
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