package data_access;

import entity.User;
import use_case.user_profile_user_story.user_profile.LoggedInDataAccessInterface;

/**
 * In-memory storage of the logged-in user.
 * Allows us to remember what user we to search for in the DB.
 */
public class LoggedInDataAccessObject implements LoggedInDataAccessInterface {
    // User has a username and a password that can be accessed by getters!
    private User loggedInUser;

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }
}
