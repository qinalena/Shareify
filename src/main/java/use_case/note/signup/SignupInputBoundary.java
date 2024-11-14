package use_case.note.signup;

public interface SignupInputBoundary {
    void execute(SignupInputData data);

    void switchToLoginView();
}
