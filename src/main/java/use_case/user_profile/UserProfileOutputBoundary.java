package use_case.user_profile;

/**
 * The output boundary for the User Profile.
 */
public interface UserProfileOutputBoundary {
    /**
     * Prepares the success view for the user profile related Use Cases.
     * @param message the output data
     */
    void prepareSuccessView(String message);

    /**
     * Prepares the failure view for the user profile related Use Cases.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Note View.
     */
    void switchToNoteView();

    /**
     * Switches to PlaylistCollection view.
     */
    void switchToPlaylistCollectionView();

    void switchToFriendsListView();
}
