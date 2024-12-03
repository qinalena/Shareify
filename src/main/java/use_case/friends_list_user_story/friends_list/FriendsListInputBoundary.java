package use_case.friends_list_user_story.friends_list;

/**
 * The input boundary interface for the Friends List use case.
 * It defines methods for transitioning between views and performing actions
 * related to managing a user's friends list.
 */
public interface FriendsListInputBoundary {

    /**
     * Switches to the Note View, where the user can view or edit notes.
     */
    void switchToNoteView();

    /**
     * Switches to the Friends List View, where the user can view their friends.
     */
    void switchToFriendsListView();

    /**
     * Switches to the Friend Profile View for a selected friend.
     *
     * @param selectedFriendName The name of the selected friend.
     * @param password The password of the selected friend.
     */
    void switchToFriendProfileView(String selectedFriendName, String password);

    /**
     * Switches to the Playlist Collection View, where the user can manage their playlists.
     */
    void switchToPlaylistCollectionView();

    /**
     * Switches to the Add Friend View, where the user can add a new friend.
     */
    void switchToAddFriendView();

    /**
     * Switches to the User Profile View, where the user can manage their own profile.
     */
    void switchToUserProfileView();

    /**
     * Executes the action of retrieving the list of friends for a specific user.
     *
     * @param username The username of the user whose friends list is to be retrieved.
     */
    void executeGetFriends(String username);

    /**
     * Executes the action of removing a friend from the user's friends list in the database.
     *
     * @param username The username of the user requesting the removal.
     * @param password The password of the user requesting the removal.
     * @param idx The index of the friend to be removed.
     */
    void executeRemoveFriendInDB(String username, String password, int idx);

    /**
     * Executes the action of retrieving a friend's password by their username.
     *
     * @param username The username of the friend whose password is to be retrieved.
     */
    void executeGetPasswordByUserName(String username);
}
