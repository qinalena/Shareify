package interface_adapter.user_profile_user_story.user_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.friends_list.FriendsListViewModel;
import interface_adapter.user_profile_user_story.note.NoteState;
import interface_adapter.user_profile_user_story.note.NoteViewModel;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;
import use_case.user_profile_user_story.user_profile.UserProfileOutputBoundary;
import use_case.user_profile_user_story.user_profile.UserProfileOutputData;

/**
 * The presenter for our User Profile.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary {

    private final UserProfileViewModel userProfileViewModel;
    private final NoteViewModel noteViewModel;
    private final ViewManagerModel viewManagerModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel = new PlaylistCollectionViewModel();
    private final FriendsListViewModel friendsListViewModel = new FriendsListViewModel();

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
     * @param userProfileOutputData the output data
     */
    @Override
    public void prepareSuccessView(UserProfileOutputData userProfileOutputData) {

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
        final NoteState notestate = noteViewModel.getState();
        notestate.setUsername(userProfileViewModel.getState().getUsername());
        this.noteViewModel.setState(notestate);
        this.noteViewModel.firePropertyChanged();

        viewManagerModel.setState(noteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToPlaylistCollectionView() {
        viewManagerModel.setState(playlistCollectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToFriendsListView() {
        viewManagerModel.setState(friendsListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
