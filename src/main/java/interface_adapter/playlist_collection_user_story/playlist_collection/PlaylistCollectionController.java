package interface_adapter.playlist_collection_user_story.playlist_collection;

import entity.User;
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
     * @param user the user's info
     * @param playlistName the playlist to be recorded
     */
    public void addPlaylist(User user, String playlistName) {
        this.playlistCollectionInteractor.addPlaylist(user, playlistName);
    }

    /**
     * Executes removing playlist related use cases.
     * @param user the user's info
     * @param playlistName playlist to be removed
     */
    public void removePlaylist(User user, String playlistName) {
        playlistCollectionInteractor.removePlaylist(user, playlistName);
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
