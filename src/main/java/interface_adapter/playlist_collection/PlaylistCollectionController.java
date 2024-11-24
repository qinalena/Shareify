package interface_adapter.playlist_collection;

import use_case.playlist_collection.PlaylistCollectionInputBoundary;

/**
 * Controller for our Playlist Collection related Use Cases.
 */
public class PlaylistCollectionController {

    private final PlaylistCollectionInputBoundary playlistCollectionInteractor;

    public PlaylistCollectionController(PlaylistCollectionInputBoundary playlistCollectionInteractor) {
        this.playlistCollectionInteractor = playlistCollectionInteractor;
    }

    /**
     * Executes the User Profile related Use Cases.
     */

    public void execute() {

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
     * Executes the "switch to UserProfile view" Use Case.
     */
    public void switchToUserProfileView() {
        playlistCollectionInteractor.switchToUserProfileView();
    }

    /**
     * Executes the "switch to AddPlaylist view" use case.
     */
    public void switchToAddPlaylistView() {
        playlistCollectionInteractor.switchToAddPlaylistView();
    }
}