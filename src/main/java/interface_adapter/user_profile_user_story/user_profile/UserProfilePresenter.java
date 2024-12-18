package interface_adapter.user_profile_user_story.user_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.friends_list.FriendsListState;
import interface_adapter.friends_list_user_story.friends_list.FriendsListViewModel;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionState;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;
import interface_adapter.user_profile_user_story.note.NoteState;
import interface_adapter.user_profile_user_story.note.NoteViewModel;
import use_case.user_profile_user_story.user_profile.UserProfileOutputBoundary;

/**
 * The presenter for our User Profile.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary {

    private final UserProfileViewModel userProfileViewModel;
    private final NoteViewModel noteViewModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private final FriendsListViewModel friendsListViewModel;
    private final ViewManagerModel viewManagerModel;

    public UserProfilePresenter(UserProfileViewModel userProfileViewModel,
                                NoteViewModel noteViewModel,
                                PlaylistCollectionViewModel playlistCollectionViewModel,
                                FriendsListViewModel friendsListViewModel,
                                ViewManagerModel viewManagerModel) {
        this.userProfileViewModel = userProfileViewModel;
        this.noteViewModel = noteViewModel;
        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.friendsListViewModel = friendsListViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToNoteView() {
        final NoteState notestate = noteViewModel.getState();
        notestate.setUsername(userProfileViewModel.getState().getCurrentUsername());
        noteViewModel.getState().setNote(userProfileViewModel.getState().getBio());
        this.noteViewModel.setState(notestate);
        this.noteViewModel.firePropertyChanged();

        viewManagerModel.setState(noteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToPlaylistCollectionView() {
        // Fetch playlistCollectionState from ViewModel
        final PlaylistCollectionState playlistCollectionState = playlistCollectionViewModel.getState();

        // Set username from UserProfileViewModel state
        playlistCollectionState.setUsername(userProfileViewModel.getState().getCurrentUsername());

        // Update state in PlaylistCollectionViewModel
        this.playlistCollectionViewModel.setState(playlistCollectionState);

        // Fire property change for PlaylistCollectionViewModel first to ensure it updates correctly
        this.playlistCollectionViewModel.firePropertyChanged();

        // Switch view in the ViewManagerModel
        viewManagerModel.setState(playlistCollectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToFriendsListView(String username, String password) {
        viewManagerModel.setState(friendsListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        final FriendsListState friendsListState = friendsListViewModel.getState();
        friendsListState.setPassword(password);
        friendsListState.setUsername(username);
        this.friendsListViewModel.setState(friendsListState);
        this.friendsListViewModel.firePropertyChanged();
    }

    @Override
    public void switchToChangePasswordView() {
        viewManagerModel.setState("Change Password");
        viewManagerModel.firePropertyChanged();
    }
}
