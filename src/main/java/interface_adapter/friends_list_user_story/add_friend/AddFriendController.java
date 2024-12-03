package interface_adapter.friends_list_user_story.add_friend;

import use_case.friends_list_user_story.add_friend.AddFriendInputBoundary;

/**
 * Controller for our Add Friend related Use Cases.
 */
public class AddFriendController {
    private final AddFriendInputBoundary interactor;

    public AddFriendController(AddFriendInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the add friend use case.
     *
     * @param friendName the name of the friend.
     */
    public void addFriend(String friendName) {
        interactor.execute(friendName);
    }

    /**
     * Executes the switch to friends list view use case.
     */
    public void switchToFriendsListView() {
        interactor.switchToFriendsListView();
    }

    /**
     * Executes the get user by the username to check if a user exists in the DB.
     * @param username the username.
     */
    public void executeGetUserByUsername(String username) {
        interactor.executeGetUserByUserName(username);
    }

    /**
     * Executes the add friend in DB use case.
     * @param username the username of the user.
     * @param password the password of the user.
     * @param friendName the name of the friend you want to add.
     */
    public void executeAddFriendinDB(String username, String password, String friendName) {
        interactor.executeAddFriendInDB(username, password, friendName);
    }
}

