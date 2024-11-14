package use_case.note.playlist_collection;

import entity.User;
import interface_adapter.playlist_collection.PlaylistCollectionPresenter;

/**
 * The "Use Case Interactor" for our playlist collection related use cases of creating
 * a playlist.
 */

public class PlaylistCollectionInteractor implements PlaylistCollectionInputBoundary {

    private final PlaylistCollectionDataAccessInterface playlistCollectionDataAccessInterface;
    private final PlaylistCollectionOutputBoundary playlistCollectionPresenter;

    public PlaylistCollectionInteractor(PlaylistCollectionDataAccessInterface playlistCollectionDataAccessInterface,
                                        PlaylistCollectionOutputBoundary playlistCollectionOutputBoundary) {
        this.playlistCollectionDataAccessInterface = playlistCollectionDataAccessInterface;
        this.playlistCollectionPresenter = playlistCollectionOutputBoundary;
    }

    @Override
    public void switchToPlaylistView();
        PlaylistCollectionPresenter.switchToPlaylistView();
}
