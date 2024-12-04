package use_case.friends_list_user_story.friend_profile;

/**
 * Input boundary for the FriendProfile use case. This interface defines the methods that must be implemented
 * to handle the navigation and switching between different views related to a friend's profile.
 */
public interface FriendProfileInputBoundary {

    /**
     * Executes the operation to switch to the Note view. This method is part of the friend profile navigation.
     */
    void switchToNoteView();

    /**
     * Switches to the Friends List view, requiring the username and password for authentication.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    void switchToFriendsListView(String username, String password);

    /**
     * Switches to the Playlist Collection view, requiring the username and password for authentication.
     *
     * @param username The username of the user.
     * @param password The password of the user.
     */
    void switchToPlaylistCollectionView(String username, String password);

    /**
     * Switches to the All Friends view, displaying a list of all friends.
     */
    void switchToAllFriendsView();

    /**
     * Switches to the Chat view for a specific friend, identified by their username.
     *
     * @param friendUsername The username of the friend to initiate a chat with.
     */
    void switchToChatView(String friendUsername);
}