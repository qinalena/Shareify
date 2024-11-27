package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a playlist in our program.
 */
public class Playlist {
    private String name;
    private List<Song> songs = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return songs;
    }

    /**
     * Add a song to a playlist.
     * @param song the song to be added
     */
    public void addSong(Song song) {
        songs.add(song);
    }

    /**
     * Remove a song from a playlist.
     *
     * @param songIndex the song to be removed
     */
    public void removeSong(int songIndex) {
        songs.remove(songIndex);
    }
}
