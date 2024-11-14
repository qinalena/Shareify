package interface_adapter.playlist_collection;


import use_case.note.NoteInputBoundary;

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
     * Executes the PlayistCollection related Use Cases.
     */
    public void execute() {
        // To be implemented
    }
}
