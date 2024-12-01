package interface_adapter.user_profile_user_story.logout;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_user_story.login.LoginState;
import interface_adapter.login_user_story.login.LoginViewModel;
import interface_adapter.login_user_story.signup.SignupState;
import interface_adapter.login_user_story.signup.SignupViewModel;
import interface_adapter.login_user_story.welcome.WelcomeViewModel;
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
    private WelcomeViewModel welcomeViewModel = new WelcomeViewModel();
    private SignupViewModel signupViewModel = new SignupViewModel();

    public LogoutPresenter(ViewManagerModel viewManagerModel,
                           UserProfileViewModel UserprofileViewModel,
                           LoginViewModel loginViewModel, SignupViewModel signupViewModel) {
        this.userProfileViewModel = UserprofileViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
        this.signupViewModel = signupViewModel;
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

        final SignupState currentSignupState = signupViewModel.getState();
        currentSignupState.setUsername("");
        currentSignupState.setPassword("");
        currentSignupState.setRepeatPassword("");
        signupViewModel.setState(currentSignupState);
        signupViewModel.firePropertyChanged();

        this.viewManagerModel.setState(welcomeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        // No need to add code here. We'll assume that logout can't fail.
        // Thought question: is this a reasonable assumption?
    }
}
