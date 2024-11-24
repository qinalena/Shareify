package interface_adapter.user_profile_user_story.note;

import use_case.user_profile_user_story.note.NoteInputBoundary;

/**
 * Controller for our Note related Use Cases.
 */
public class NoteController {

    private final NoteInputBoundary noteInteractor;

    public NoteController(NoteInputBoundary noteInteractor) {
        this.noteInteractor = noteInteractor;
    }

    /**
     * Executes the Note related Use Cases.
     * @param note the note to be recorded
     */
    public void execute(String note) {
        if (note != null) {
            noteInteractor.executeSave(note);
        }
        else {
            noteInteractor.executeRefresh();
        }
    }

    /**
     * Executes the switch to User Profile view use case.
     */
    public void switchToUserProfileView() {
        noteInteractor.switchToUserProfileView();
    }
}