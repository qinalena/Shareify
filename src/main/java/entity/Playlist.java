package entity;

import java.util.List;

/**
 * Representation of a playlist in our program.
 */
public class Playlist {
    private String playlistName;
    private List<Track> tracks;

    public Playlist(String playlistName, List<Track> tracks) {
        this.playlistName = playlistName;
        this.tracks = tracks;
    }

    public String getPlaylistName() {
        return playlistName;
    }
}
