package interface_adapter.playlist_collection_user_story.add_playlist;

import use_case.playlist_collection_user_story.add_playlist.AddPlaylistInputBoundary;

/**
 * Controller for AddPlaylist related use cases.
 */
public class AddPlaylistController {

    private final AddPlaylistInputBoundary addPlaylistInteractor;

    public AddPlaylistController(AddPlaylistInputBoundary addPlaylistInteractor) {
        this.addPlaylistInteractor = addPlaylistInteractor;
    }

    /**
     * Executes the AddPlaylist related Use Cases.
     * @param playlistName name of playlist
     */
    public void addPlaylist(String playlistName) {
        addPlaylistInteractor.execute(playlistName);
    }

    /**
     * Executes the switch to playlist collection view use case.
     */
    public void switchToPlaylistCollectionView() {
        addPlaylistInteractor.switchToPlaylistCollectionView();
    }
}
