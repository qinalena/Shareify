package use_case.user_profile;

/**
 * The Input Boundary for our User Profile use cases.
 */
public interface UserProfileInputBoundary {

    /**
     * Executes the switch to Note view use case.
     */
    void switchToNoteView();

    void switchToFriendsListView();
}
