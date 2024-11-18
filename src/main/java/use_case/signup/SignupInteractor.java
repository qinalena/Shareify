package use_case.signup;

import data_access.DBNoteDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.User;
import entity.UserFactoryInter;
import org.json.JSONObject;
import use_case.note.DataAccessException;

public class SignupInteractor implements SignupInputBoundary{
    private final DBUserDataAccessObject userDataAccessObject;
    private final SignupOutputBoundary userPresenter;
    private final UserFactoryInter userFactory;

    public SignupInteractor(DBUserDataAccessObject signupDataAccessInterface,
                            SignupOutputBoundary signupOutputBoundary,
                            UserFactoryInter userFactory) {
        this.userDataAccessObject = signupDataAccessInterface;
        this.userPresenter = signupOutputBoundary;
        this.userFactory = userFactory;
    }

    @Override
    public void execute(SignupInputData signupInputData) throws DataAccessException {
        if (userDataAccessObject.existsByName(signupInputData.getUsername())) {
            userPresenter.prepareFailView("User already exists.");
        }
        else if (!signupInputData.getPassword().equals(signupInputData.getRepeatPassword())) {
            userPresenter.prepareFailView("Passwords don't match.");
        }
        else {
            final User user = userFactory.createUser(signupInputData.getUsername(), signupInputData.getPassword(), new JSONObject());
            userDataAccessObject.createUser(user);

            final SignupOutputData signupOutputData = new SignupOutputData(user.getName(), false);
            userPresenter.prepareSuccessView(signupOutputData);
        }
    }

    @Override
    public void switchToLoginView() {
        userPresenter.switchToLoginView();
    }
}

