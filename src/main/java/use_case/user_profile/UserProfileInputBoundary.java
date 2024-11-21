package use_case.user_profile;

/**
 * The Input Boundary for our User Profile use cases.
 */
public interface UserProfileInputBoundary {

    /**
     * Executes the UserProfile use cases of displaying the logged-in user's username and note.
     */
    void execute();

    /**
     * Executes the switch to Note View use case.
     */
    void switchToNoteView();

    /**
     * Executes the switch to FriendsList View use case.
     */
    void switchToFriendsListView();
}
