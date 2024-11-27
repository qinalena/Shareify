package interface_adapter.playlist_user_story.playlist;

import use_case.playlist_user_story.playlist.PlaylistInputBoundary;

/**
 * Controller for our Playlist related Use Cases.
 */
public class PlaylistController {

    private final PlaylistInputBoundary playlistInteractor;

    public PlaylistController(PlaylistInputBoundary playlistInteractor) {
        this.playlistInteractor = playlistInteractor;
    }

    /**
     * Executes the remove song from playlist Use Case.
     * @param songIndex  the selected song
     */
    public void removeSong(int songIndex) {
        // Need index of the song so we remember which song to delete if we have multiple songs that are the same
        playlistInteractor.removeSong(songIndex);
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
