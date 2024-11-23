package interface_adapter.login_user_story.signup;

import interface_adapter.ViewManagerModel;
import interface_adapter.login_user_story.login.LoginState;
import interface_adapter.login_user_story.login.LoginViewModel;
import use_case.login_user_story.signup.SignupOutputBoundary;
import use_case.login_user_story.signup.SignupOutputData;

public class SignupPresenter implements SignupOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final SignupViewModel signupViewModel;
    private final LoginViewModel loginViewModel;

    public SignupPresenter(SignupViewModel signupViewModel,
                           LoginViewModel loginViewModel,
                           ViewManagerModel viewManagerModel) {
        this.signupViewModel = signupViewModel;
        this.loginViewModel = loginViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(SignupOutputData response) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setUsername(response.getUsername());
        this.loginViewModel.setState(loginState);
        loginViewModel.firePropertyChanged();

        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
    public void prepareFailView(String error){
        final SignupState signupState = signupViewModel.getState();
        signupState.setUsernameError(error);
        signupViewModel.firePropertyChanged();
    }

    public void switchToLoginView() {
        viewManagerModel.setState(loginViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}
