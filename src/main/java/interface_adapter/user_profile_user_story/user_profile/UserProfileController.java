package interface_adapter.user_profile_user_story.user_profile;

import use_case.user_profile_user_story.user_profile.UserProfileInputBoundary;

/**
 * Controller for our User Profile related Use Cases.
 */
public class UserProfileController {

    private final UserProfileInputBoundary userProfileInteractor;

    public UserProfileController(UserProfileInputBoundary userProfileInteractor) {
        this.userProfileInteractor = userProfileInteractor;
    }

    /**
     * Switches to Note View.
     */
    public void switchToNoteView() {
        userProfileInteractor.switchToNoteView();
    }

    /**
     * Switches to Playlist Collection View.
     */
    public void switchToPlaylistCollectionView() {
        userProfileInteractor.switchToPlaylistCollectionView();
    }

    /**
     * Switches to Friends List View.
     * Executes the "switch to FriendsListView" User Case.
     * @param password password of logged in user
     * @param username username of logged in user
     */
    public void switchToFriendsListView(String username, String password) {
        userProfileInteractor.switchToFriendsListView(username, password);
    }

    public void switchToChangePasswordView() {
        userProfileInteractor.switchToChangePasswordView();
    }
}
