package use_case.signup;

public interface SignupInputBoundary {
    void execute(SignupInputData data);

    void switchToLoginView();
}
