package interface_adapter.user_profile;

import entity.User;

/**
 * The State for a User Profile.
 */
public class UserProfileState {
    // Hardcode user for testing example.
    private User user = new User("newUserName3", "password123");
    private String error;

    public User getUser() {
        return user;
    }

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
