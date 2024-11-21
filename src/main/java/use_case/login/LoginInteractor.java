package use_case.login;

import data_access.LoggedInDataAccessObject;
import entity.User;
import use_case.user_profile.LoggedInDataAccessInterface;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoggedInDataAccessInterface loggedInUserDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoggedInDataAccessObject loggedInDataAccessObject, LoginOutputBoundary loginOutputBoundary) {
        this.userDataAccessObject = userDataAccessInterface;
        this.loginPresenter = loginOutputBoundary;
        this.loggedInUserDataAccessObject = loggedInDataAccessObject;
    }

    @Override
    public void execute(LoginInputData loginInputData) {
        final String username = loginInputData.getUsername();
        final String password = loginInputData.getPassword();
        if (!userDataAccessObject.existsByName(username)) {
            loginPresenter.prepareFailView(username + ": Account does not exist.");
        }
        else {
            final String pwd = userDataAccessObject.get(username).getPassword();
            if (!password.equals(pwd)) {
                loginPresenter.prepareFailView("Incorrect password for \"" + username + "\".");
            }
            else {

                final User user = userDataAccessObject.get(loginInputData.getUsername());

                // Update LoggedInUserDataAccessObject with logged-in user's username and password
                loggedInUserDataAccessObject.setUsername(user.getName());
                loggedInUserDataAccessObject.setPassword(user.getPassword());

                // Unnecessary code? Username has not been changed.
                userDataAccessObject.setCurrentUsername(user.getName());

                final LoginOutputData loginOutputData = new LoginOutputData(user.getName(), false);
                loginPresenter.prepareSuccessView(loginOutputData);
            }
        }
    }
}
