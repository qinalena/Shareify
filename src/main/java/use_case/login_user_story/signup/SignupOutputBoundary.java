package use_case.login_user_story.signup;

public interface SignupOutputBoundary {
    /**
     * Switches to Log in view.
     * @param outputData the output data.
     */
    void prepareSuccessView(SignupOutputData outputData);

    /**
     * Pop up error message if signup failed.
     * @param errorMessage the error message.
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to log in view.
     */
    void switchToLoginView();

}
