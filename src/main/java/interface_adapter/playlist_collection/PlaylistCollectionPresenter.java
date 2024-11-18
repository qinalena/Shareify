package interface_adapter.playlist_collection;

import interface_adapter.ViewManagerModel;
import use_case.playlist_collection.PlaylistCollectionOutputBoundary;

import java.awt.*;

/**
 * The Presenter for Playlist Collection Use Case.
 */
public class PlaylistCollectionPresenter implements PlaylistCollectionOutputBoundary {

    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private final ViewManagerModel viewManagerModel;

    public PlaylistCollectionPresenter(PlaylistCollectionViewModel playlistCollectionViewModel,
                                       ViewManagerModel viewManagerModel) {

        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the PlaylistCollection related Use Cases.
     * @param note the output data
     */
    @Override
    public void prepareSuccessView(String note) {
        playlistCollectionViewModel.getState().setPlaylistError(null);
        playlistCollectionViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        playlistCollectionViewModel.getState().setPlaylistError(errorMessage);
        playlistCollectionViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPlaylistCollectionView() {
        viewManagerModel.setState(playlistCollectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}