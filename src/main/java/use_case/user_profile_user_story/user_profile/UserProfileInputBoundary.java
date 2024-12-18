package use_case.user_profile_user_story.user_profile;

/**
 * The Input Boundary for our User Profile use cases.
 */
public interface UserProfileInputBoundary {

    /**
     * Switches to Note View.
     */
    void switchToNoteView();

    /**
     * Switches to Playlist Collection view.
     */
    void switchToPlaylistCollectionView();

    /**
     * Switches to Friends List view.
     * @param username the username of friend.
     * @param password the password of friend.
     */
    void switchToFriendsListView(String username, String password);

    /**
     * Switches to Change Password View.
     */
    void switchToChangePasswordView();
}
