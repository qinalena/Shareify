package interface_adapter.user_profile_user_story.logout;

import use_case.user_profile_user_story.logout.LogoutInputBoundary;
import use_case.user_profile_user_story.logout.LogoutInputData;

/**
 * The controller for the Logout Use Case.
 */
public class LogoutController {

    private LogoutInputBoundary logoutUseCaseInteractor;

    public LogoutController(LogoutInputBoundary logoutUseCaseInteractor) {
        this.logoutUseCaseInteractor = logoutUseCaseInteractor;
    }

    /**
     * Executes the Logout Use Case.
     * @param username the username of the user logging in
     */
    public void execute(String username) {
        // 1. instantiate the `LogoutInputData`, which should contain the username.
        final LogoutInputData logoutInputData = new LogoutInputData(username);

        // 2. tell the Interactor to execute.
        logoutUseCaseInteractor.execute(logoutInputData);
    }
}
