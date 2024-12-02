package interface_adapter.playlist_user_story.playlist;

import java.util.ArrayList;
import java.util.List;

/**
 * The State for a Playlist.
 */
public class PlaylistState {
    private String currentPlaylistName;
    private List<String> songs = new ArrayList<>();
    private String error;

    public void setCurrentPlaylistName(String currentPlaylistName) {
        this.currentPlaylistName = currentPlaylistName;
    }

    public String getCurrentPlaylistName() {
        return currentPlaylistName;
    }

    public void setSongs(List<String> songs) {
        this.songs = songs;
    }

    public List<String> getSongs() {
        return songs;
    }

    /**
     * Adds the selected song to the current playlist.
     * @param song the selected song
     */
    public void addSong(String song) {
        songs.add(song);
    }

    /**
     * Removes the selected song from the current playlist.
     * @param songIndex the selected song index
     */
    public void removeSong(int songIndex) {
        songs.remove(songIndex);
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

}
