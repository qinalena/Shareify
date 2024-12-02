package interface_adapter.friends_list_user_story.friends_list;

import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;
import use_case.friends_list_user_story.friends_list.FriendsListOutputBoundary;
import interface_adapter.friends_list_user_story.add_friend.AddFriendViewModel;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileState;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileViewModel;
import interface_adapter.user_profile_user_story.note.NoteViewModel;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;
import interface_adapter.ViewManagerModel;

import java.util.List;

public class FriendsListPresenter implements FriendsListOutputBoundary {

    // THe view manager model is intantioed wrong
    private final ViewManagerModel viewManagerModel;
    private final NoteViewModel noteViewModel = new NoteViewModel();
    private final FriendsListViewModel friendsListViewModel;
    private final AddFriendViewModel addFriendViewModel;
    private final FriendProfileViewModel friendProfileViewModel;
    private final UserProfileViewModel userProfileViewModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel = new PlaylistCollectionViewModel();

    public FriendsListPresenter(FriendsListViewModel viewModel, ViewManagerModel viewManagerModel,
                                AddFriendViewModel addFriendViewModel,
                                FriendProfileViewModel friendProfileViewModel,
                                UserProfileViewModel userProfileViewModel) {
        this.friendsListViewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
        this.addFriendViewModel = addFriendViewModel;
        this.friendProfileViewModel = friendProfileViewModel;
        this.userProfileViewModel = userProfileViewModel;
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

    @Override
    public void switchToUserProfileView() {
        viewManagerModel.setState(userProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareGetFriendsSuccessView(List<String> friends) {
        friendsListViewModel.getState().setFriends(friends);
        friendsListViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        friendsListViewModel.getState().setError(error);
        friendsListViewModel.firePropertyChanged();
    }

    @Override
    public void prepareGetFriendPasswordbyUserNameSuccessView(String password) {
        friendsListViewModel.getState().setFriendPassword(password);
        friendsListViewModel.firePropertyChanged();
    }
}
