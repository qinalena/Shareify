package use_case.user_profile_user_story.note;

import data_access.DBUserDataAccessObject;
import entity.User;
import interface_adapter.user_profile_user_story.note.NoteState;
import interface_adapter.user_profile_user_story.note.NoteViewModel;

/**
 * The "Use Case Interactor" for our two note-related use cases of refreshing
 * the contents of the note and saving the contents of the note. Since they
 * are closely related, we have combined them here for simplicity.
 */
public class NoteInteractor implements NoteInputBoundary {

    private final NoteDataAccessInterface noteDataAccessInterface;
    private final NoteOutputBoundary notePresenter;
    private User user;

    public NoteInteractor(DBUserDataAccessObject dbUserDataAccessObject, NoteDataAccessInterface noteDataAccessInterface, NoteOutputBoundary notePresenter) {
        this.noteDataAccessInterface = noteDataAccessInterface;
        this.notePresenter = notePresenter;
    }

    /**
     * Executes the save note use case.
     *
     * @param note the input data
     */
    @Override
    public void executeSave(String note, String username) {
        try {
            user = noteDataAccessInterface.get(username);
            String updatedNote = noteDataAccessInterface.saveNote(user, note);
            user.setNote(updatedNote);
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
