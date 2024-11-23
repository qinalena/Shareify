package interface_adapter.playlist_user_story;

import java.util.List;

import entity.Track;

/**
 * The State for a Playlist.
 */
public class PlaylistState {
    private String currentUsername;
    private String playlistName;

    // Stores list of songs that user has added to playlist
    private List<Track> tracks;
}
