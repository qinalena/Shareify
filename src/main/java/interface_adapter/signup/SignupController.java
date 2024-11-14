package interface_adapter.signup;

import use_case.note.DataAccessException;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInputData;

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
