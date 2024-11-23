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
     * Executes the "switch to NoteView" Use Case.
     */
    public void switchToNoteView() {
        userProfileInteractor.switchToNoteView();
    }

    /**
     * Executes the "switch to PlaylistCollectionView" User Case.
     */
    public void switchToPlaylistCollectionView() {
        userProfileInteractor.switchToPlaylistCollectionView();
    }

    /**
     * Executes the "switch to FriendsListView" User Case.
     */
    public void switchToFriendsListView() {
        userProfileInteractor.switchToFriendsListView();
    }
}
