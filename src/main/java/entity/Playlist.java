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
     * Returns a playlist's list of Songs as a list of Strings.
     * @return the list of Strings.
     */
    public List<String> songsToStrings() {
        final List<String> strings = new ArrayList<>();
        for (Song song : songs) {
            strings.add(song.getName());
        }
        return strings;
    }

}
