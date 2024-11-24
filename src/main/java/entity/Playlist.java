package entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Representation of a playlist in our program.
 */
public class Playlist {
    private String name;
    private List<Track> tracks = new ArrayList<>();

    public Playlist(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Track> getTracks() {
        return tracks;
    }

    /**
     * Add a track to a playlist.
     * @param track the track to be added.
     */
    public void addTrack(Track track) {
        tracks.add(track);
    }
}
