package interface_adapter.login_user_story.signup;

import data_access.DataAccessException;
import use_case.login_user_story.signup.SignupInputBoundary;
import use_case.login_user_story.signup.SignupInputData;

public class SignupController {
    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    /**
     * Executes the Login Use Case.
     * @param username the username of the user logging in
     * @param password1 the password of the user
     * @param password2 the repeated password of the user
     * @throws DataAccessException if the user already exist.
     */
    public void execute(String username, String password1, String password2) throws DataAccessException {
        final SignupInputData data = new SignupInputData(username, password1, password2);

        userSignupUseCaseInteractor.execute(data);

    }

    /**
     * Switch to the login view.
     */
    public void switchToLoginView() {
        userSignupUseCaseInteractor.switchToLoginView();
    }
}
