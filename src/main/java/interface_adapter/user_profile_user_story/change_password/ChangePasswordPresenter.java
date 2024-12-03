package interface_adapter.user_profile_user_story.change_password;

import interface_adapter.ViewManagerModel;
import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;
import use_case.user_profile_user_story.change_password.ChangePasswordOutputBoundary;
import use_case.user_profile_user_story.change_password.ChangePasswordOutputData;

/**
 * The Presenter for the Change Password Use Case.
 */
public class ChangePasswordPresenter implements ChangePasswordOutputBoundary {

    private final UserProfileViewModel userProfileViewModel;
    private final ViewManagerModel viewManagerModel;

    public ChangePasswordPresenter(UserProfileViewModel userProfileViewModel, ViewManagerModel viewManagerModel) {
        this.userProfileViewModel = userProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(ChangePasswordOutputData outputData) {
        userProfileViewModel.firePropertyChanged("password");

    }

    @Override
    public void prepareFailView(String error) {
        // note: this use case currently can't fail
    }

    @Override
    public void switchToUserProfileView() {
        userProfileViewModel.firePropertyChanged();
        viewManagerModel.setState(userProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
