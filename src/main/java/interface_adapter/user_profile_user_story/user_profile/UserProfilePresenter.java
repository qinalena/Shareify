package interface_adapter.user_profile_user_story.user_profile;

import entity.Playlist;
import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.friends_list.FriendsListViewModel;
import interface_adapter.user_profile_user_story.note.NoteState;
import interface_adapter.user_profile_user_story.note.NoteViewModel;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;
import use_case.user_profile_user_story.user_profile.UserProfileOutputBoundary;

import java.util.List;

/**
 * The presenter for our User Profile.
 */
public class UserProfilePresenter implements UserProfileOutputBoundary {

    private final UserProfileViewModel userProfileViewModel;
    private final NoteViewModel noteViewModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private final FriendsListViewModel friendsListViewModel = new FriendsListViewModel();
    private final ViewManagerModel viewManagerModel;

    public UserProfilePresenter(UserProfileViewModel userProfileViewModel,
                                NoteViewModel noteViewModel,
                                PlaylistCollectionViewModel playlistCollectionViewModel, ViewManagerModel viewManagerModel) {
        this.userProfileViewModel = userProfileViewModel;
        this.noteViewModel = noteViewModel;
        this.viewManagerModel = viewManagerModel;
        this.playlistCollectionViewModel = playlistCollectionViewModel;
    }

    @Override
    public void switchToNoteView() {
        final NoteState notestate = noteViewModel.getState();
        notestate.setUsername(userProfileViewModel.getState().getCurrentUsername());
        this.noteViewModel.setState(notestate);
        this.noteViewModel.firePropertyChanged();

        viewManagerModel.setState(noteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToPlaylistCollectionView(List<Playlist> playlistCollection) {
        for (Playlist playlist : playlistCollection) {
            playlistCollectionViewModel.getState().addPlaylistToPlaylistCollection(playlist);
        }
        playlistCollectionViewModel.firePropertyChanged();

        viewManagerModel.setState(playlistCollectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToFriendsListView() {
        viewManagerModel.setState(friendsListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToChangePasswordView() {
        viewManagerModel.setState("Change Password");
        viewManagerModel.firePropertyChanged();
    }
}
