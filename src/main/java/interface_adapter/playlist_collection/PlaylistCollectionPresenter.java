package interface_adapter.playlist_collection;

import interface_adapter.ViewManagerModel;
import interface_adapter.playlist.PlaylistViewModel;
import use_case.note.playlist_collection.PlaylistCollectionOutputBoundary;

/**
 * The Presenter for Playlist Collection Use Case.
 */
public class PlaylistCollectionPresenter implements PlaylistCollectionOutputBoundary {

    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private final PlaylistViewModel playlistViewModel;
    private final ViewManagerModel viewManagerModel;

    //    private final ViewManagerModel viewManagerModel;

    public PlaylistCollectionPresenter(ViewManagerModel viewManagerModel,
                                       PlaylistCollectionViewModel playlistCollectionViewModel,
                                       PlaylistViewModel playlistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.playlistViewModel = playlistViewModel;
    }

    @Override
    public void prepareSuccessView(PlaylistCollectionOutputBoundary response) {
        // On success, switch to the playlist collection in view.

        final PlaylistCollectionState playlistCollectionState = playlistCollectionViewModel.getState();
        this.playlistCollectionViewModel.setState(playlistCollectionState);
        this.playlistCollectionViewModel.firePropertyChanged();

        this.viewManagerModel.setState(playlistCollectionViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

     @Override
     public void prepareFailView(String errorMessage) {
        final PlaylistCollectionState playlistCollectionState = playlistCollectionViewModel.getState();
        playlistCollectionState.setPlaylistError(errorMessage);
        playlistCollectionViewModel.firePropertyChanged();
     }
}
