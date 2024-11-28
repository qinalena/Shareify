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
     * Executes the adding playlist related Use Cases.
     * @param playlistName the playlist to be recorded
     */
    public void addPlaylist(String playlistName) {
        playlistCollectionInteractor.addPlaylist(playlistName);
    }

    /**
     * Executes removing playlist related use cases.
     * @param playlistName playlist to be removed
     */
    public void removePlaylist(String playlistName) {
        playlistCollectionInteractor.removePlaylist(playlistName);
    }

    /**
     * Switches to selected Playlist View.
     * @param playlistName name of playlist
     */
    public void switchToPlaylistView(String playlistName) {
        playlistCollectionInteractor.switchToPlaylistView(playlistName);
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
