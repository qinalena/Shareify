package use_case.user_profile_user_story.change_password;

/**
 * Output Data for the Change Password Use Case.
 */
public class ChangePasswordOutputData {

    private final String username;


    public ChangePasswordOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
