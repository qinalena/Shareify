package interface_adapter.friend_profile;

public class FriendProfileState {
    private String error;

    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
