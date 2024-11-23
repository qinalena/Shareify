package use_case.login_user_story.login;

import data_access.LoggedInDataAccessObject;
import entity.User;
import use_case.user_profile_user_story.user_profile.LoggedInDataAccessInterface;

import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


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

                try {
                    CreateUserCSV(username, password);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static void CreateUserCSV(String username, String password) throws IOException {
        File file = new File("login.csv");
        System.out.println(file.getAbsolutePath());
        CSVWriter csvWriter = new CSVWriter(new FileWriter(file));
        String[] user = new String[]{username, password};
        csvWriter.writeNext(user);
        csvWriter.close();
    }
}
