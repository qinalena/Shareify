package use_case.note;

import data_access.DBNoteDataAccessObject;
import entity.User;

/**
 * The "Use Case Interactor" for our two note-related use cases of refreshing
 * the contents of the note and saving the contents of the note. Since they
 * are closely related, we have combined them here for simplicity.
 */
public class NoteInteractor implements NoteInputBoundary {

    private final NoteDataAccessInterface noteDataAccessInterface;
    private final NoteOutputBoundary notePresenter;
    private final User user = new User("newUserName3", "password123");

    public NoteInteractor(NoteDataAccessInterface noteDataAccessInterface, NoteOutputBoundary notePresenter) {
        this.noteDataAccessInterface = noteDataAccessInterface;
        this.notePresenter = notePresenter;

    }

    /**
     * Executes the refresh note use case.
     *
     */
    @Override
    public void executeRefresh() {
        try {
            final String note = noteDataAccessInterface.loadNote(user);
            notePresenter.prepareSuccessView(note);
        }
        catch (DataAccessException ex) {
            notePresenter.prepareFailView(ex.getMessage());
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
            notePresenter.prepareSuccessView(updatedNote);
        }
        catch (DataAccessException ex) {
            notePresenter.prepareFailView(ex.getMessage());
        }
    }

    @Override
    public void switchToUserProfileView() {
        notePresenter.switchToUserProfileView();
    }

}
