package use_case.note.signup;

public interface SignupOutputBoundary {
    void prepareSuccessView(SignupOutputData outputData);

    void prepareFailView(String errorMessage);

    void switchToLoginView();

}
