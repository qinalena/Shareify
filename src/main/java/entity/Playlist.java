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

    public List<Song> getTracks() {
        return songs;
    }

    /**
     * Add a track to a playlist.
     * @param song the track to be added.
     */
    public void addTrack(Song song) {
        songs.add(song);
    }
}
