package interface_adapter.user_profile_user_story.user_profile;

/**
 * The State for a User Profile.
 */
public class UserProfileState {
    private String currentUsername;
    private String error;

    public void setCurrentUsername(String currentUsername) {
        this.currentUsername = currentUsername;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }

}
