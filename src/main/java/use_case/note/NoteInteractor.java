package use_case.note;

import data_access.DBCommentDataAccessObject;
import entity.User;

/**
 * The "Use Case Interactor" for our two note-related use cases of refreshing
 * the contents of the note and saving the contents of the note. Since they
 * are closely related, we have combined them here for simplicity.
 */
public class NoteInteractor implements NoteInputBoundary {

    private final NoteDataAccessInterface noteDataAccessInterface;
    private final NoteOutputBoundary noteOutputBoundary;
    private final User user = new User("newUserName3", "password123");

    public NoteInteractor(NoteDataAccessInterface noteDataAccessInterface, NoteOutputBoundary noteOutputBoundary) {
        this.noteDataAccessInterface = noteDataAccessInterface;
        this.noteOutputBoundary = noteOutputBoundary;

        // Example usage: Create a new user if needed
//        User newUser = new User("new_user_name_1", "password123");
//        executeCreateUser(newUser);
    }

    /**
     * Executes the refresh note use case.
     *
     */
    @Override
    public void executeRefresh() {
        try {
            final String note = noteDataAccessInterface.loadNote(user);
            noteOutputBoundary.prepareSuccessView(note);
        }
        catch (DataAccessException ex) {
            noteOutputBoundary.prepareFailView(ex.getMessage());
        }
    }

    /**
     * Executes the save note use case.
     *
     * @param note the input data
     */
    @Override
    public void executeSave(String note) {
        try {
            final String updatedNote = noteDataAccessInterface.saveNote(user, note);
            noteOutputBoundary.prepareSuccessView(updatedNote);
        }
        catch (DataAccessException ex) {
            noteOutputBoundary.prepareFailView(ex.getMessage());
        }
    }

    // Method to create a new user
    public void executeCreateUser(User user) {
        try {
            DBCommentDataAccessObject.createUser(user);
            noteOutputBoundary.prepareSuccessView("User created successfully");
        } catch (DataAccessException ex) {
            noteOutputBoundary.prepareFailView(ex.getMessage());
        }
    }
}
