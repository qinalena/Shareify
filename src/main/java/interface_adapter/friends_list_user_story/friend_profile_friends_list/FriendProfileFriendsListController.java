package interface_adapter.friends_list_user_story.friend_profile_friends_list;

import use_case.friends_list_user_story.friend_profile_friends_list.FriendProfileFriendsListInputBoundary;

/**
 * Controller for FriendProfileFriendsList.
 */
public class FriendProfileFriendsListController {
    private final FriendProfileFriendsListInputBoundary interactor;

    public FriendProfileFriendsListController(FriendProfileFriendsListInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the switch to friends profile view use case.
     * @param selectedFriendName the name of the friend that was selected.
     * @param password the password of the selected friend.
     */
    public void switchToFriendProfileView(String selectedFriendName, String password) {
        interactor.switchToFriendProfileView(selectedFriendName, password);
    }

    /**
     * Executes getFriends method to get the friends of your friend from the DB.
     * @param username the friend's username.
     */
    public void executeGetFriends(String username) {
        interactor.executeGetFriends(username);
    }

}
