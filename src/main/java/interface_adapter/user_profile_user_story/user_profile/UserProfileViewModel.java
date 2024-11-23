package interface_adapter.user_profile_user_story.user_profile;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the User Profile.
 */
public class UserProfileViewModel extends ViewModel<UserProfileState> {
    public UserProfileViewModel() {
        super("user profile");
        setState(new UserProfileState());
    }
}
