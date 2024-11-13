package interface_adapter.user_profile;

/**
 * The State for a User Profile.
 */
public class UserProfileState {

    private String error;

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
