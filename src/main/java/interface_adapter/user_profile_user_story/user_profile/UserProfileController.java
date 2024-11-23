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

    // Update when needed
    /**
     * Executes the User Profile related Use Cases.
     */
    public void execute() {

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
     */
    public void switchToFriendsListView() {
        userProfileInteractor.switchToFriendsListView();
    }
}
