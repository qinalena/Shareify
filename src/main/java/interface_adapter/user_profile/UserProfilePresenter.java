package interface_adapter.user_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.note.NoteViewModel;
import interface_adapter.playlist_collection.PlaylistCollectionViewModel;
import use_case.user_profile.UserProfileOutputBoundary;

/**
 * The presenter for our User Profile.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary {

    private final UserProfileViewModel userProfileViewModel;
    private final NoteViewModel noteViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel = new PlaylistCollectionViewModel();

    public UserProfilePresenter(UserProfileViewModel userProfileViewModel,
                                NoteViewModel noteViewModel,
                                ViewManagerModel viewManagerModel) {
        this.userProfileViewModel = userProfileViewModel;
        this.noteViewModel = noteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the User Profile related Use Cases.
     *
     * @param note the output data
     */
    @Override
    public void prepareSuccessView(String note) {
        userProfileViewModel.getState().setError(null);
        userProfileViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the User Profile related Use Cases.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        userProfileViewModel.getState().setError(errorMessage);
        userProfileViewModel.firePropertyChanged();
    }

    @Override
    public void switchToNoteView() {
        viewManagerModel.setState(noteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToPlaylistCollectionView() {
        viewManagerModel.setState(playlistCollectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
