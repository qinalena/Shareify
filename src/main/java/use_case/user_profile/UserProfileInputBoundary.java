package use_case.user_profile;

/**
 * The Input Boundary for our note-related use cases. Since they are closely related,
 * we have included them both in the same interface for simplicity.
 */
public interface UserProfileInputBoundary {

    /**
     * Executes the switch to Note view use case.
     */
    void switchToNoteView();

//    /**
//     * Executes the refresh note use case.
//     */
//    void executeRefresh();
//
//    /**
//     * Executes the save note use case.
//     * @param message the input data
//     */
//    void executeSave(String message);
}
