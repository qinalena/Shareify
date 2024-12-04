package use_case.friends_list_user_story.add_friend;

/**
 * Input boundary for the AddFriend use case. This interface defines the methods that must be implemented
 * to handle the addition of a friend and related operations.
 */
public interface AddFriendInputBoundary {

    /**
     * Executes the operation to add a friend based on the provided friend's name.
     *
     * @param friendName The name of the friend to be added.
     */
    void execute(String friendName);

    /**
     * Switches the view to the friends list view after completing the friend addition operation.
     */
    void switchToFriendsListView();

    /**
     * Retrieves a user by their username. This method is part of the process to add a friend.
     *
     * @param userName The username of the user to be retrieved.
     */
    void executeGetUserByUserName(String userName);

    /**
     * Adds a friend to the database. This method includes the necessary details such as the username,
     * password, and the friend's name.
     *
     * @param username The username of the current user.
     * @param password The password of the current user (if required for authentication).
     * @param friendName The name of the friend to be added.
     */
    void executeAddFriendInDB(String username, String password, String friendName);
}
