package use_case.playlist_collection;

import use_case.user_profile.PlaylistCollectionInputBoundary;

/**
 * The "Use Case Interactor" for our playlist collection related use cases of creating
 * a playlist.
 */

public class PlaylistCollectionInteractor {

    private final PlaylistCollectionOutputBoundary playlistCollectionPresenter;

    // Note: Need to add Playlist interface
    public PlaylistCollectionInteractor(PlaylistCollectionOutputBoundary playlistCollectionPresenter) {
        this.playlistCollectionPresenter = playlistCollectionPresenter;
    }
}
