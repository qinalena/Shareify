package use_case.user_profile_user_story.note;

import data_access.DataAccessException;
import entity.User;

/**
 * The "Use Case Interactor" for our two note-related use cases of refreshing
 * the contents of the note and saving the contents of the note. Since they
 * are closely related, we have combined them here for simplicity.
 */
public class NoteInteractor implements NoteInputBoundary {

    private final NoteDataAccessInterface noteDataAccessInterface;
    private final NoteOutputBoundary notePresenter;
    private User user;

    public NoteInteractor(NoteDataAccessInterface noteDataAccessInterface, NoteOutputBoundary notePresenter) {
        this.noteDataAccessInterface = noteDataAccessInterface;
        this.notePresenter = notePresenter;
    }

    @Override
    public void executeSave(NoteInputData noteInputData) {
        try {
            final String username = noteInputData.getUsername();
            final String note = noteInputData.getNote();
            user = noteDataAccessInterface.getUser(username);
            final String updatedNote = noteDataAccessInterface.saveNote(user, note);
            user.setNote(updatedNote);
            final NoteOutputData outputData = new NoteOutputData(username, note);
            notePresenter.prepareSuccessView(outputData);
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
