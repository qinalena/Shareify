package interface_adapter.user_profile_user_story.user_profile;

/**
 * The State for a User Profile.
 */
public class UserProfileState {
    private String username;
    private String password;

    private String error;
    private String usernameError;
    private String repeatPasswordError;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }


}
