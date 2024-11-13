package interface_adapter.user_profile;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the NoteView.
 */
public class UserProfileViewModel extends ViewModel<UserProfileState> {
    public UserProfileViewModel() {
        super("userProfile");
        setState(new UserProfileState());
    }
}
