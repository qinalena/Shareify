package use_case.user_profile;

import entity.User;
import use_case.note.NoteDataAccessInterface;

/**
 * The "Use Case Interactor" for our two note-related use cases of refreshing
 * the contents of the note and saving the contents of the note. Since they
 * are closely related, we have combined them here for simplicity.
 */
public class UserProfileInteractor implements UserProfileInputBoundary {

    private final NoteDataAccessInterface noteDataAccessInterface;
    private final UserProfileOutputBoundary userProfilePresenter;
    private final User user = new User("newUserName3", "password123");

    public UserProfileInteractor(NoteDataAccessInterface noteDataAccessInterface, UserProfileOutputBoundary userProfilePresenter) {
        this.noteDataAccessInterface = noteDataAccessInterface;
        this.userProfilePresenter = userProfilePresenter;

        // Example usage: Create a new user if needed
//        User newUser = new User("new_user_name_1", "password123");
//        executeCreateUser(newUser);
    }

    @Override
    public void switchToNoteView() {
        userProfilePresenter.switchToNoteView();
    }

//    /**
//     * Executes the refresh note use case.
//     *
//     */
//    @Override
//    public void executeRefresh() {
//        try {
//            final String note = noteDataAccessInterface.loadNote(user);
//            userProfileOutputBoundary.prepareSuccessView(note);
//        }
//        catch (DataAccessException ex) {
//            userProfileOutputBoundary.prepareFailView(ex.getMessage());
//        }
//    }
//
//    /**
//     * Executes the save note use case.
//     *
//     * @param note the input data
//     */
//    @Override
//    public void executeSave(String note) {
//        try {
//            final String updatedNote = noteDataAccessInterface.saveNote(user, note);
//            userProfileOutputBoundary.prepareSuccessView(updatedNote);
//        }
//        catch (DataAccessException ex) {
//            userProfileOutputBoundary.prepareFailView(ex.getMessage());
//        }
//    }

    // Method to create a new user
//    public void executeCreateUser(User user) {
//        try {
//            DBNoteDataAccessObject.createUser(user);
//            userProfileOutputBoundary.prepareSuccessView("User created successfully");
//        } catch (DataAccessException ex) {
//            userProfileOutputBoundary.prepareFailView(ex.getMessage());
//        }
//    }
}
