package interface_adapter.playlist_user_story;

import use_case.playlist_user_story.PlaylistInputBoundary;

/**
 * Controller for our Playlist related Use Cases.
 */
public class PlaylistController {

    private final PlaylistInputBoundary playlistInteractor;

    public PlaylistController(PlaylistInputBoundary playlistInteractor) {
        this.playlistInteractor = playlistInteractor;
    }

    /**
     * Executes the remove track from playlist Use Case.
     */
    public void removeTrack() {
        playlistInteractor.removeTrack();
    }

    /**
     * Switches to Playlist Collection View.
     */
    public void switchToPlaylistCollectionView() {
        playlistInteractor.switchToPlaylistCollectionView();
    }

    /**
     * Switches to Search Tracks View.
     */
    public void switchToSearchTracksView() {
        playlistInteractor.switchToSearchTracksView();
    }

}
