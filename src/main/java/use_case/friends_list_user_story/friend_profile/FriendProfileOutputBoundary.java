package use_case.friends_list_user_story.friend_profile;

/**
 * Output boundary for the FriendProfile use case. This interface defines the methods that must be implemented
 * to handle the output of friend profile-related operations, including success and failure scenarios, and view switching.
 */
public interface FriendProfileOutputBoundary {

    /**
     * Prepares the success view for user profile-related use cases. This method displays a success message to the user.
     *
     * @param message The output data or success message to be displayed.
     */
    void prepareSuccessView(String message);

    /**
     * Prepares the failure view for user profile-related use cases. This method displays an error message to the user.
     *
     * @param errorMessage The explanation of the failure or error message to be displayed.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Note View. This method navigates the user to the note-related view.
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