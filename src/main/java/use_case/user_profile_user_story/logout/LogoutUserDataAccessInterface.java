package use_case.user_profile_user_story.logout;

import entity.User;

/**
 * DAO for the Logout Use Case.
 */
public interface LogoutUserDataAccessInterface {

    /**
     * Sets the user indicating who is the current user of the application.
     * @param user the new current username
     */
    void setCurrentUser(User user);
}
