package interface_adapter.friends_list_user_story.friend_profile;

/**
 * State for Friend Profile.
 */
public class FriendProfileState {
    private String error;
    private String username;
    private String password;

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }

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
}
