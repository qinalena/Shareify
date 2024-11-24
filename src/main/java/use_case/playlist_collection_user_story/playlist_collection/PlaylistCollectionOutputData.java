package use_case.playlist_collection_user_story.playlist_collection;

import java.util.List;
import java.util.Map;

import entity.Playlist;

/**
 * The output data for Playlist Collection.
 */
public class PlaylistCollectionOutputData {
    private Map<String, Playlist> playlistCollection;

    public PlaylistCollectionOutputData(Map<String, Playlist> playlistCollection) {
        this.playlistCollection = playlistCollection;
    }

    /**
     * Returns the playlist with the given playlist name.
     * @param playlistName the name of the playlist
     * @return the playlist
     */
    public Playlist getPlaylist(String playlistName) {
        return playlistCollection.get(playlistName);
    }
}
