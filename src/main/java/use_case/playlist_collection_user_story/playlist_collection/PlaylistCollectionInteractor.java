package use_case.playlist_collection_user_story.playlist_collection;

import entity.User;

/**
 * The "Use Case Interactor" for our playlist collection related use cases of creating
 * a playlist.
 */

public class PlaylistCollectionInteractor implements PlaylistCollectionInputBoundary {
    private final PlaylistCollectionDataAccessInterface playlistCollectionDataAccessInterface;
    private final PlaylistCollectionOutputBoundary playlistCollectionPresenter;
    private final User user;

    public PlaylistCollectionInteractor(PlaylistCollectionDataAccessInterface playlistCollectionDataAccessInterface,
                                        PlaylistCollectionOutputBoundary playlistCollectionPresenter) {
        this.playlistCollectionDataAccessInterface = playlistCollectionDataAccessInterface;
        this.playlistCollectionPresenter = playlistCollectionPresenter;
    }

    /**
     * Execute add playlist use case.
     */
    @Override
    public void executeAddPlaylist() {
        try{
            final String playlist = playlistCollectionDataAccessInterface.loadPlaylist(user);
        }
        if (playlistName == null || playlistName.isEmpty()) {
            playlistCollectionPresenter.prepareFailView("Playlist name cannot be empty.");
            return;
        }
        playlistCollectionPresenter.preparePlaylistAddedView(playlistName);
    }

    @Override
    public void removePlaylist(String playlistName) {
        playlistCollectionPresenter.preparePlaylistRemovedView(playlistName);
    }

    @Override
    public void switchToUserProfileView() {
        playlistCollectionPresenter.switchToUserProfileView();
    }

    @Override
    public void switchToAddPlaylistView() {
        playlistCollectionPresenter.switchToAddPlaylistView();
    }
}
