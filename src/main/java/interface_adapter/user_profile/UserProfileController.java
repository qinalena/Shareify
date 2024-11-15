package interface_adapter.user_profile;

import use_case.user_profile.PlaylistCollectionInputBoundary;

/**
 * Controller for our User Profile related Use Cases.
 */
public class UserProfileController {

    private final PlaylistCollectionInputBoundary userProfileInteractor;

    public UserProfileController(PlaylistCollectionInputBoundary userProfileInteractor) {
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
    public void switchToPlaylistView() {
        userProfileInteractor.switchToPlaylistCollectionView();
    }
}
