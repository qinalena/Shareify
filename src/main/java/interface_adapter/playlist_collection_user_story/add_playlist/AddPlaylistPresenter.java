package interface_adapter.playlist_collection_user_story.add_playlist;

import java.util.List;

import interface_adapter.ViewManagerModel;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;
import use_case.playlist_collection_user_story.add_playlist.AddPlaylistOutputBoundary;

/**
 * Presenter for add playlist use case.
 */
public class AddPlaylistPresenter implements AddPlaylistOutputBoundary {

    private final AddPlaylistViewModel addPlaylistViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel;

    public AddPlaylistPresenter(AddPlaylistViewModel addPlaylistViewModel,
                                ViewManagerModel viewManagerModel,
                                PlaylistCollectionViewModel playlistCollectionViewModel) {
        this.addPlaylistViewModel = addPlaylistViewModel;
        this.viewManagerModel = viewManagerModel;
        this.playlistCollectionViewModel = playlistCollectionViewModel;
    }

    /**
     * Prepare success view for addPlaylist use cases.
     * @param updatePlaylist output data
     */
    @Override
    public void prepareSuccessView(List<String> updatePlaylist) {
        // Directly sets updated list of playlists in AddPlaylistState
        final AddPlaylistState addPlaylistState = new AddPlaylistState();
        addPlaylistState.setPlaylists(updatePlaylist);
        addPlaylistState.setErrorMessage(null);

        addPlaylistViewModel.setState(addPlaylistState);
        addPlaylistViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailureView(String errorMessage) {
        final AddPlaylistState addPlaylistState = new AddPlaylistState();
        addPlaylistState.setErrorMessage(errorMessage);
        addPlaylistViewModel.setState(addPlaylistState);
        addPlaylistViewModel.firePropertyChanged();
    }

    /**
     * Executes the switch back to playlist collection after adding playlist to the DB.
     */
    @Override
    public void switchToPlaylistCollectionView() {
        viewManagerModel.setState(playlistCollectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}