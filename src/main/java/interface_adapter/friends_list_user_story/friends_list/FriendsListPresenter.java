package interface_adapter.friends_list_user_story.friends_list;

import use_case.friends_list_user_story.friends_list.FriendsListOutputBoundary;
import interface_adapter.add_friend.AddFriendViewModel;
import interface_adapter.friend_profile.FriendProfileState;
import interface_adapter.friend_profile.FriendProfileViewModel;
import interface_adapter.note.NoteViewModel;
import interface_adapter.playlist_collection.PlaylistCollectionViewModel;
import use_case.friends_list.FriendsListOutputBoundary;
import interface_adapter.ViewManagerModel;

public class FriendsListPresenter implements FriendsListOutputBoundary {

    // THe view manager model is intantioed wrong
    private final ViewManagerModel viewManagerModel;
    private final NoteViewModel noteViewModel = new NoteViewModel();
    private final FriendsListViewModel friendsListViewModel;
    private final AddFriendViewModel addFriendViewModel;
    private final FriendProfileViewModel friendProfileViewModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel = new PlaylistCollectionViewModel();

    public FriendsListPresenter(FriendsListViewModel viewModel, ViewManagerModel viewManagerModel, AddFriendViewModel addFriendViewModel, FriendProfileViewModel friendProfileViewModel) {
        this.friendsListViewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.addFriendViewModel = addFriendViewModel;
        this.friendProfileViewModel = friendProfileViewModel;
    }

    @Override
    public void presentFriendAdded(String friendName) {
        final FriendsListState friendsListState = friendsListViewModel.getState();
        friendsListState.addFriend(friendName);
        friendsListViewModel.setState(friendsListState);
        friendsListViewModel.firePropertyChanged();
    }

    @Override
    public void presentFriendDeleted(String friendName) {
        friendsListViewModel.getState().removeFriend(friendName);
        friendsListViewModel.firePropertyChanged();
    }

    @Override
    public void presentError(String errorMessage) {
        friendsListViewModel.getState().setError(errorMessage);
        friendsListViewModel.firePropertyChanged();
    }

    @Override
    public void switchToNoteView() {
        viewManagerModel.setState(noteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToFriendsListView() {
        viewManagerModel.setState(friendsListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToFriendProfileView(String selectedFriendName, String password) {
        viewManagerModel.setState(friendProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        final FriendProfileState friendProfileState = friendProfileViewModel.getState();
        friendProfileState.setPassword(password);
        friendProfileState.setUsername(selectedFriendName);
        this.friendProfileViewModel.setState(friendProfileState);
        this.friendProfileViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPlaylistCollectionView() {
        viewManagerModel.setState(playlistCollectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToAddFriendView() {
        viewManagerModel.setState(addFriendViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
