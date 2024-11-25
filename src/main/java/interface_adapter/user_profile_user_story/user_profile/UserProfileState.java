package interface_adapter.user_profile_user_story.user_profile;

/**
 * The State for a User Profile.
 */
public class UserProfileState {
    private String username;

    private String error;
    private String usernameError;
    private String passwordError;
    private String repeatPassword = "";
    private String repeatPasswordError;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;

    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public String getRepeatPasswordError() {
        return repeatPasswordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public void setRepeatPasswordError(String repeatPasswordError) {
        this.repeatPasswordError = repeatPasswordError;
    }

}
