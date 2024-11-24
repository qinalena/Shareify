package interface_adapter.playlist_user_story.playlist;

import use_case.playlist_user_story.playlist.PlaylistInputBoundary;
import use_case.playlist_user_story.playlist.PlaylistInputData;

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
     * @track the selected track
     */
    public void removeTrack(String track) {
        final PlaylistInputData playlistInputData = new PlaylistInputData();
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
