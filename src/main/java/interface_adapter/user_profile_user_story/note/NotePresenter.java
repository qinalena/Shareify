package interface_adapter.user_profile_user_story.note;

import interface_adapter.ViewManagerModel;
import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;
import use_case.user_profile_user_story.note.NoteOutputBoundary;
import use_case.user_profile_user_story.note.NoteOutputData;

/**
 * The presenter for our Note viewing and editing program.
 */
public class NotePresenter implements NoteOutputBoundary {

    private final NoteViewModel noteViewModel;
    private final UserProfileViewModel userProfileViewModel;
    private final ViewManagerModel viewManagerModel;

    public NotePresenter(NoteViewModel noteViewModel, UserProfileViewModel userProfileViewModel, ViewManagerModel viewManagerModel) {
        this.noteViewModel = noteViewModel;
        this.userProfileViewModel = userProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(NoteOutputData outputData) {
        String note = outputData.getNote();
        noteViewModel.getState().setNote(note);
        noteViewModel.getState().setError(null);
        noteViewModel.firePropertyChanged();

    }

    /**
     * Prepares the failure view for the Note related Use Cases.
     *
     * @param errorMessage the explanation of the failure
     */
    @Override
    public void prepareFailView(String errorMessage) {
        noteViewModel.getState().setError(errorMessage);
        noteViewModel.firePropertyChanged();
    }

    @Override
    public void switchToUserProfileView() {
        userProfileViewModel.getState().setBio(noteViewModel.getState().getNote());
        userProfileViewModel.firePropertyChanged();
        viewManagerModel.setState(userProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
