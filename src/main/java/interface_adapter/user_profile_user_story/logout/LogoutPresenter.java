package interface_adapter.user_profile_user_story.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_user_story.login.LoginState;
import interface_adapter.login_user_story.login.LoginViewModel;
import interface_adapter.user_profile_user_story.user_profile.UserProfileState;
import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;
import use_case.user_profile_user_story.logout.LogoutOutputBoundary;
import use_case.user_profile_user_story.logout.LogoutOutputData;

/**
 * The Presenter for the Logout Use Case.
 */
public class LogoutPresenter implements LogoutOutputBoundary {

    private UserProfileViewModel userProfileViewModel;
    private ViewManagerModel viewManagerModel;
    private LoginViewModel loginViewModel;

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           UserProfileViewModel UserprofileViewModel,
                           LoginViewModel loginViewModel) {
        this.userProfileViewModel = UserprofileViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(LogoutOutputData response) {

        final UserProfileState currentState = userProfileViewModel.getState();
        userProfileViewModel.setState(currentState);
        userProfileViewModel.firePropertyChanged();

        final LoginState currentLoginState = loginViewModel.getState();
        currentLoginState.setUsername("");
        currentLoginState.setPassword("");
        loginViewModel.setState(currentLoginState);
        loginViewModel.firePropertyChanged();

        // This code tells the View Manager to switch to the LoginView.
        this.viewManagerModel.setState(loginViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }
}
