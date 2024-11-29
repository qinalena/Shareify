package use_case.login_user_story.login;

import data_access.LoggedInDataAccessObject;
import entity.User;
import use_case.user_profile_user_story.note.DataAccessException;

/**
 * The Login Interactor.
 */
public class LoginInteractor implements LoginInputBoundary {
    private final LoginUserDataAccessInterface userDataAccessObject;
    private final LoggedInDataAccessInterface loggedInUserDataAccessObject;
    private final LoginOutputBoundary loginPresenter;

    public LoginInteractor(LoginUserDataAccessInterface userDataAccessInterface,
                           LoggedInDataAccessInterface loggedInDataAccessObject, LoginOutputBoundary loginOutputBoundary) {
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
                final User loggedInUser = new User(user.getName(), user.getPassword());
                loggedInUserDataAccessObject.setLoggedInUser(loggedInUser);

                // Unnecessary code? Username has not been changed.
                userDataAccessObject.setCurrentUsername(user.getName());

                try{
                    final LoginOutputData loginOutputData = new LoginOutputData(user.getName(), user.getPassword(), userDataAccessObject.loadNote(loggedInUser));
                    loginPresenter.prepareSuccessView(loginOutputData);
                }
                catch (DataAccessException ex) {
                    loginPresenter.prepareFailView(ex.getMessage());
                }
            }
        }
    }
}
