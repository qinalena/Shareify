package interface_adapter.add_playlist;

import use_case.add_playlist.AddPlaylistInputBoundary;

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
}
