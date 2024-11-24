package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a playlist for our program.
 */
public class Playlist implements PlaylistInter {
    private final String name;
    private List<String> songs;
    private int likes;
    private List<String> comments;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<String>();
        this.likes = 0;
        this.comments = new ArrayList<String>();
    }

    public String getName() {
        return name;
    }

    public List<String> getSongs() {
        return songs;
    }

    public int getLikes() {
        return likes;
    }

    public List<String> getComments() {
        return comments;
    }
}
