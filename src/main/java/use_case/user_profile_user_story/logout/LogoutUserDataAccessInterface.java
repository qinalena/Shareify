package use_case.user_profile_user_story.logout;

/**
 * DAO for the Logout Use Case.
 */
public interface LogoutUserDataAccessInterface {

    /**
     * Returns the username of the curren user of the application.
     * @return the username of the current user
     */
    String getCurrentUsername();

    /**
     * Sets the username indicating who is the current user of the application.
     * @param username the new current username
     */
    void setCurrentUsername(String username);
}
