package use_case.login_user_story.signup;

public interface SignupInputBoundary {
    void execute(SignupInputData data);

    void switchToLoginView();
}
