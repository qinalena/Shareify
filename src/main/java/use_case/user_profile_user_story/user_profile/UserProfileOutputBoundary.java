package use_case.user_profile_user_story.user_profile;

/**
 * The output boundary for the User Profile.
 */
public interface UserProfileOutputBoundary {

    /**
     * Switches to Note View.
     */
    void switchToNoteView();

    /**
     * Switches to Playlist Collection view.
     */
    void switchToPlaylistCollectionView();

    /**
     * Switches to Friends List View.
     * @param username the username of user.
     * @param password the password of user.
     */
    void switchToFriendsListView(String username, String password);

    /**
     * Switches to Change Password View.
     */
    void switchToChangePasswordView();
}
