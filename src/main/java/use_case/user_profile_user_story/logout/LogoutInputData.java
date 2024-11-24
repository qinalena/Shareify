package use_case.user_profile_user_story.logout;

/**
 * The Input Data for the Logout Use Case.
 */
public class LogoutInputData {

    private final String username;

    public LogoutInputData(String username) {
        // TODO: save the current username in an instance variable and add a getter.
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
