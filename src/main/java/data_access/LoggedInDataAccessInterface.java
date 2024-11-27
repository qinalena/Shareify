package data_access;

import entity.User;

/**
 * DAO to access logged-in user's username and password.
 */
public interface LoggedInDataAccessInterface {

    /**
     * Set the logged-in user of the program.
     * @param user the logged-in user
     */
    void setLoggedInUser(User user);

    /**
     * Get the logged-in user of the program.
     * @return the logged-in user
     */
    User getLoggedInUser();
}
