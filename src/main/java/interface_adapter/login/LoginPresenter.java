package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final UserProfileViewModel UserProfileViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          UserProfileViewModel userProfileViewModel,
                          LoginViewModel loginViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.UserProfileViewModel = userProfileViewModel;
        this.loginViewModel = loginViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.

        UserProfileState UserProfileState = new UserProfileState();
        this.UserProfileViewModel.setState(UserProfileState);
        this.UserProfileViewModel.firePropertyChanged();

        this.viewManagerModel.setState(UserProfileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }
}
