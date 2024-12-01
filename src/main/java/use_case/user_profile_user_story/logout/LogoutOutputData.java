package use_case.user_profile_user_story.logout;

/**
 * Output Data for the Logout Use Case.
 */
public class LogoutOutputData {

    private String username;

    public LogoutOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
