package interface_adapter.user_profile;

import use_case.user_profile.UserProfileInputBoundary;

/**
 * Controller for our Note related Use Cases.
 */
public class UserProfileController {

    private final UserProfileInputBoundary userProfileInteractor;

    public UserProfileController(UserProfileInputBoundary userProfileInteractor) {
        this.userProfileInteractor = userProfileInteractor;
    }

    /**
     * Executes the User Profile related Use Cases.
     * @param note the note to be recorded
     */
    public void execute(String note) {
        }

    /**
     * Executes the "switch to NoteView" Use Case.
     */
    public void switchToNoteView() {
        userProfileInteractor.switchToNoteView();
    }
}
