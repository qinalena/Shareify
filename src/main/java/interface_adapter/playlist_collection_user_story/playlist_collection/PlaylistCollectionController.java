package interface_adapter.playlist_collection_user_story.playlist_collection;

import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionInputBoundary;

/**
 * Controller for our Playlist Collection related Use Cases.
 */
public class PlaylistCollectionController {

    private final PlaylistCollectionInputBoundary playlistCollectionInteractor;

    public PlaylistCollectionController(PlaylistCollectionInputBoundary playlistCollectionInteractor) {
        this.playlistCollectionInteractor = playlistCollectionInteractor;
    }

    /**
     * Executes the Playlist Collection related Use Cases.
     * @param playlistName name of playlist
     */
    public void addPlaylist(String playlistName) {
        playlistCollectionInteractor.addPlaylist(playlistName);
    }

    /**
     * Executes the Playlist Collection related Use Cases.
     * @param playlistName name of playlist
     */
    public void removePlaylist(String playlistName) {
        playlistCollectionInteractor.removePlaylist(playlistName);
    }

    /**
     * Switches to selected Playlist View.
     * @param playlistName name of playlist
     */
    public void switchToPlaylistView(String playlistName) {
    }
}