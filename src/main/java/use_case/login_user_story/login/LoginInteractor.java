package use_case.login_user_story.login;

import entity.User;
import data_access.DataAccessException;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.getUser(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userDataAccessObject.getUser(loginInputData.getUsername());

                userDataAccessObject.setCurrentUser(user);

                try {
                    final LoginOutputData loginOutputData = new LoginOutputData(user.getUsername(), user.getPassword(), userDataAccessObject.loadNote(userDataAccessObject.getCurrentUser()));
                    loginPresenter.prepareSuccessView(loginOutputData);
                }
                catch (DataAccessException ex) {
                    loginPresenter.prepareFailView(ex.getMessage());
                }
            }
        }
    }
}
