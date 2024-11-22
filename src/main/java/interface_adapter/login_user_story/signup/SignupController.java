package interface_adapter.login_user_story.signup;

import use_case.user_profile_user_story.note.DataAccessException;
import use_case.login_user_story.signup.SignupInputBoundary;
import use_case.login_user_story.signup.SignupInputData;

public class SignupController {
    private final SignupInputBoundary userSignupUseCaseInteractor;

    public SignupController(SignupInputBoundary userSignupUseCaseInteractor) {
        this.userSignupUseCaseInteractor = userSignupUseCaseInteractor;
    }

    public void execute(String username, String password1, String password2) throws DataAccessException {
        final SignupInputData data = new SignupInputData(username, password1, password2);

        userSignupUseCaseInteractor.execute(data);

    }

    public void switchToLoginView(){
        userSignupUseCaseInteractor.switchToLoginView();
    }
}
