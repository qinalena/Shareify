package interface_adapter.friends_list_user_story.friend_profile_friends_list;

import use_case.friends_list_user_story.friend_profile_friends_list.FriendProfileFriendsListInputBoundary;

public class FriendProfileFriendsListController {
    private final FriendProfileFriendsListInputBoundary interactor;

    public FriendProfileFriendsListController(FriendProfileFriendsListInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void switchToFriendProfileView(String selectedFriendName, String password) {
        interactor.switchToFriendProfileView(selectedFriendName, password);
    }

    public void executeGetFriends(String username) {
        interactor.executeGetFriends(username);
    }

}
