package use_case.login_user_story.login;

import entity.User;
import data_access.DataAccessException;

/**
 * DAO for the Login Use Case.
 */
public interface LoginUserDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param username the username to look for
     * @return true if a user with the given username exists; false otherwise
     */
    boolean existsByName(String username);

    /**
     * Saves the user.
     * @param user the user to save
     */
    void createUser(User user);

    /**
     * Returns the user with the given username.
     * @param username the username to look up
     * @return the user with the given username
     */
    User getUser(String username);

    /**
     * Returns the current user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    User getCurrentUser();

    /**
     * Sets the current user of the application.
     * @param user the new current user; null to indicate that no one is currently logged into the application.
     */
    void setCurrentUser(User user);

    public String loadNote(User user) throws DataAccessException;
}
