package interface_adapter.playlist_collection_user_story.playlist_collection;

import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionInputBoundary;

import java.util.List;

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
     * @param playlist the playlist to be recorded
     */

    public void execute(String playlist) {
        if (playlist != null) {
            playlistCollectionInteractor.addPlaylist(playlist);
        }
        else {
            playlistCollectionInteractor.removePlaylist(playlist);
        }
    }

    /**
     * Executes the "switch to UserProfile view" Use Case.
     */
    public void switchToUserProfileView() {
        playlistCollectionInteractor.switchToUserProfileView();
    }
}