package use_case.login_user_story.signup;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData outputData);

    void prepareFailView(String errorMessage);

    void switchToLoginView();

}
