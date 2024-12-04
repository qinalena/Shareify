package use_case.login_user_story.signup;

public interface SignupInputBoundary {
    /**
     * Executes the login use case.
     * @param signupInputData the input data
     */
    void execute(SignupInputData signupInputData);

    /**
     * Switch to log in view.
     */
    void switchToLoginView();
}
