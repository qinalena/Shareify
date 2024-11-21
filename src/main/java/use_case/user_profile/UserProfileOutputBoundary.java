package use_case.user_profile;

/**
 * The output boundary for the User Profile.
 */
public interface UserProfileOutputBoundary {
    /**
     * Prepares the success view for the user profile related Use Cases.
     * @param userProfileOutputData the output data
     */
    void prepareSuccessView(UserProfileOutputData userProfileOutputData );

    /**
     * Prepares the failure view for the user profile related Use Cases.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to Note View.
     */
    void switchToNoteView();

    /**
     * Switches to FriendsList View.
     */
    void switchToFriendsListView();
}
