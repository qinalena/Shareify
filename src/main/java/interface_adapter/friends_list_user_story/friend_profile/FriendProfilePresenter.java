package interface_adapter.friends_list_user_story.friend_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsState;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsViewModel;
import interface_adapter.user_profile_user_story.note.NoteViewModel;
import interface_adapter.friends_list_user_story.friends_list.FriendsListViewModel;
import use_case.friends_list_user_story.friend_profile.FriendProfileOutputBoundary;

public class FriendProfilePresenter implements FriendProfileOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final FriendProfileViewModel friendProfileViewModel;
    private final NoteViewModel noteViewModel;
    private final FriendsListViewModel friendsListViewModel = new FriendsListViewModel();
    private final FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel;

    public FriendProfilePresenter(FriendProfileViewModel friendProfileViewModel, ViewManagerModel viewManagerModel, NoteViewModel noteViewModel, FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel) {
        this.friendProfileViewModel = friendProfileViewModel;
        this.viewManagerModel = viewManagerModel;
        this.noteViewModel = noteViewModel;
        this.friendProfilePlaylistsViewModel = friendProfilePlaylistsViewModel;
    }

    @Override
    public void prepareSuccessView(String message) {
        friendProfileViewModel.getState().setError(null);
        friendProfileViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        friendProfileViewModel.getState().setError(errorMessage);
        friendProfileViewModel.firePropertyChanged();
    }

    @Override
    public void switchToNoteView() {
        viewManagerModel.setState(noteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToFriendsListView() {
        viewManagerModel.setState(friendsListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToPlaylistCollectionView(String username, String password) {
        viewManagerModel.setState(friendProfilePlaylistsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        final FriendProfilePlaylistsState friendProfilePlaylistsState = friendProfilePlaylistsViewModel.getState();
        friendProfilePlaylistsState.setUsername(username);
        friendProfilePlaylistsState.setPassword(password);
        this.friendProfilePlaylistsViewModel.setState(friendProfilePlaylistsState);
        this.friendProfilePlaylistsViewModel.firePropertyChanged();
    }
}
