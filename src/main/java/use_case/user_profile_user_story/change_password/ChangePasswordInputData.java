package use_case.user_profile_user_story.change_password;

/**
 * The input data for the Change Password Use Case.
 */
public class ChangePasswordInputData {

    private final String password;
    private final String username;

    public ChangePasswordInputData(String password, String username) {
        this.password = password;
        this.username = username;
    }

    public String getUserName() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
