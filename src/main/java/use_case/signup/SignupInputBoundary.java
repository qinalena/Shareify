package use_case.signup;

import use_case.note.DataAccessException;

public interface SignupInputBoundary {
    void execute(SignupInputData data) throws DataAccessException;

    void switchToLoginView();
}
