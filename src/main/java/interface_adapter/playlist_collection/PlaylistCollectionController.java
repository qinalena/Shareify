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
     * @param playlist the playlist to be added
     */
    public void execute(List playlist) {
        if (playlist != null) {
            playlistCollectionInteractor.executeCreate(playlist);
        }
        else {
            playlistCollectionInteractor.executeRefresh();
        }
    }
}
