package use_case.user_profile_user_story.note;

/**
 * The Input Boundary for our note-related use cases. Since they are closely related,
 * we have included them both in the same interface for simplicity.
 */
public interface NoteInputBoundary {

    /**
     * Executes the save note use case.
     */
    void executeSave(NoteInputData noteInputData);

    /**
     * Executes the switch to User Profile use case.
     */
    void switchToUserProfileView();
}
