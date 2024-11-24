package use_case.user_profile_user_story.user_profile;

import entity.User;

/**
 * DAO to access logged-in user's username and password.
 */
public interface LoggedInDataAccessInterface {

    void setUsername(String username);

    String getUsername();

    void setPassword(String password);

    String getPassword();
}
