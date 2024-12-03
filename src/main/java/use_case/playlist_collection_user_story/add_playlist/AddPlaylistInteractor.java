package use_case.playlist_collection_user_story.add_playlist;

import java.util.List;

/**
 * The "Use Case Interactor" for our adding playlist related use cases.
 */
public class AddPlaylistInteractor implements AddPlaylistInputBoundary {

    private final AddPlaylistOutputBoundary addPlaylistOutputBoundary;
    private final AddPlaylistOutputBoundary addPlaylistPresenter;
    private entity.User user;

    public AddPlaylistInteractor(AddPlaylistOutputBoundary addPlaylistOutputBoundary) {
        if (addPlaylistOutputBoundary == null) {
            throw new NullPointerException("Output boundary cannot be null!");
        }
        this.addPlaylistOutputBoundary = addPlaylistOutputBoundary;
        this.addPlaylistPresenter = addPlaylistOutputBoundary;
    }

    /**
     * Executes the save playlist use case.
     * @param playlistName inputted name of playlist
     */
    @Override
    public void executeCreatePlaylist(String playlistName) {
        if (this.addPlaylistOutputBoundary == null) {
            System.err.println("Outputboundary cannot be null!");
        }
        else {
            final List<String> playlists = user.getInfo();
            if (playlists.contains(playlistName)) {
                addPlaylistOutputBoundary.prepareFailureView("Playlist already exists!");
            }
            else {
                playlists.add(playlistName);
                addPlaylistOutputBoundary.prepareSuccessView(playlists);
            }
        }
    }

    @Override
    public void switchToPlaylistCollectionView() {
        addPlaylistPresenter.switchToPlaylistCollectionView();
    }
}
