package use_case.friends_list_user_story.add_friend;

import java.util.List;

/**
 * Output boundary for the AddFriend use case. This interface defines the methods that must be implemented
 * to handle the output of adding a friend, including success and failure scenarios.
 */
public interface AddFriendOutputBoundary {

    /**
     * Prepares the success view after a friend has been successfully added. This method updates the friends list view
     * with the new list of friends and displays the name of the newly added friend.
     *
     * @param updatedFriendsList The updated list of friends after the addition.
     * @param friendName The name of the friend that was just added.
     */
    void prepareSuccessView(List<String> updatedFriendsList, String friendName);

    /**
     * Prepares the failure view when an error occurs during the friend addition process. This method displays an error message
     * to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches the view to the friends list view. This method is called after the friend addition operation is completed.
     */
    void swtichToFriendsListView();

    /**
     * Prepares the success view for getting a user by their username. This method displays the username of the user that was retrieved.
     *
     * @param username The username of the retrieved user.
     */
    void prepareGetUserByUserNameSuccessView(String username);
}