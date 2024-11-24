package interface_adapter.friends_list_user_story.friend_profile_friends_list;

import interface_adapter.friends_list_user_story.friend_profile.FriendProfileState;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileViewModel;
import interface_adapter.ViewManagerModel;
import use_case.friends_list_user_story.friend_profile_friends_list.FriendProfileFriendsListOutputBoundary;

public class FriendProfileFriendsListPresenter implements FriendProfileFriendsListOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final FriendProfileViewModel friendProfileViewModel;

    public FriendProfileFriendsListPresenter(FriendProfileViewModel friendProfileViewModel, ViewManagerModel viewManagerModel) {
        this.friendProfileViewModel = friendProfileViewModel;
        this.viewManagerModel = viewManagerModel;
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
}
