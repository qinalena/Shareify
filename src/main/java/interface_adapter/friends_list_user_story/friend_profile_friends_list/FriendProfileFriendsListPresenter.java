package interface_adapter.friends_list_user_story.friend_profile_friends_list;

import java.util.List;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileState;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileViewModel;
import use_case.friends_list_user_story.friend_profile_friends_list.FriendProfileFriendsListOutputBoundary;

/**
 * Presenter for FriendProfileFriendsList.
 */
public class FriendProfileFriendsListPresenter implements FriendProfileFriendsListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final FriendProfileViewModel friendProfileViewModel;
    private final FriendProfileFriendsListViewModel friendProfileFriendsListViewModel;

    public FriendProfileFriendsListPresenter(FriendProfileViewModel friendProfileViewModel,
                                             ViewManagerModel viewManagerModel,
                                             FriendProfileFriendsListViewModel friendProfileFriendsListViewModel) {
        this.friendProfileViewModel = friendProfileViewModel;
        this.viewManagerModel = viewManagerModel;
        this.friendProfileFriendsListViewModel = friendProfileFriendsListViewModel;
    }

    @Override
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
    public void prepareGetFriendsSuccessView(List<String> friends) {
        friendProfileFriendsListViewModel.getState().setFriends(friends);
        friendProfileFriendsListViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        friendProfileFriendsListViewModel.getState().setError(error);
        friendProfileFriendsListViewModel.firePropertyChanged();
    }
}
