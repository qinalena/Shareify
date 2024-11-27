package use_case.login_user_story.signup;

import use_case.DataAccessException;

public interface SignupInputBoundary {
    void execute(SignupInputData data) throws DataAccessException;

    void switchToLoginView();
}
