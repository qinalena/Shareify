package interface_adapter.user_profile_user_story.note;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the NoteView.
 */
public class NoteViewModel extends ViewModel<NoteState> {
    public NoteViewModel() {
        super("note");
        setState(new NoteState());
    }
}
