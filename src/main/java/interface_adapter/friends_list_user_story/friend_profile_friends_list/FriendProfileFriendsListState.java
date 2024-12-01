package interface_adapter.friends_list_user_story.friend_profile_friends_list;

import java.util.ArrayList;
import java.util.List;

public class FriendProfileFriendsListState {
    private String error;
    private String username;
    private String password;
    private List<String> friends = new ArrayList<>();

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
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

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    public String getMostRecentFriend() {
        if (!friends.isEmpty()) {
            return friends.get(friends.size() - 1);
        }
        else {
            return null;
        }
    }
}
