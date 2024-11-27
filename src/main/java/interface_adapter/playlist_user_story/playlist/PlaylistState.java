package interface_adapter.playlist_user_story.playlist;

import entity.Playlist;
import entity.Song;

/**
 * The State for a Playlist.
 */
public class PlaylistState {
    private Playlist currentPlaylist;

    public void setCurrentPlaylist(Playlist currentPlaylist) {
        this.currentPlaylist = currentPlaylist;
    }

    public Playlist getCurrentPlaylist() {
        return currentPlaylist;
    }

    /**
     * Adds the selected song to the current playlist.
     * @param song the selected song
     */
    public void addSong(Song song) {
        currentPlaylist.addSong(song);
    }

    /**
     * Removes the selected song from the current playlist.
     * @param songIndex the selected song index
     */
    public void removeSong(int songIndex) {
        currentPlaylist.removeSong(songIndex);
    }
}
