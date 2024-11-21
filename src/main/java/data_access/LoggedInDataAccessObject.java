package data_access;

import use_case.user_profile.LoggedInDataAccessInterface;

/**
 * In-memory storage of the logged-in user's username and password.
 * Allows us to remember what user we to search for in the DB.
 */
public class LoggedInDataAccessObject implements LoggedInDataAccessInterface {
    private String username;
    private String password;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
