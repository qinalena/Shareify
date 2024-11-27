package use_case.playlist_collection_user_story.playlist_collection;

import java.util.List;
import java.util.Map;

import entity.Playlist;

/**
 * The output data for Playlist Collection.
 */
public class PlaylistCollectionOutputData {
    private Playlist playlist;

    public PlaylistCollectionOutputData(Playlist playlist) {
        this.playlist = playlist;
    }

    /**
     * Returns the playlist.
     * @return the playlist
     */
    public Playlist getPlaylist() {
        return this.playlist;
    }
}
