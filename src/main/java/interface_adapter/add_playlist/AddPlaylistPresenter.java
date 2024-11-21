package interface_adapter.add_playlist;

import use_case.add_playlist.AddPlaylistOutputBoundary;

import java.util.List;

public class AddPlaylistPresenter implements AddPlaylistOutputBoundary {

    private final AddPlaylistViewModel addPlaylistViewModel;

    public AddPlaylistPresenter(AddPlaylistViewModel addPlaylistViewModel) {
        this.addPlaylistViewModel = addPlaylistViewModel;
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
}
