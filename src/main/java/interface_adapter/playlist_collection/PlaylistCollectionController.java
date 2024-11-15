package interface_adapter.playlist_collection;

import use_case.user_profile.PlaylistCollectionInputBoundary;

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
     */
    public void execute() {
        // To be implemented
    }
    // need to add switch to playlist view
}
