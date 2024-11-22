package interface_adapter.friends_list;

import java.util.ArrayList;
import java.util.List;

public class FriendsListState {
    private final List<String> friends = new ArrayList<>();
    private String error;
    private String username;
    private String password;

    public List<String> getFriends() {
        return friends;
    }

    public String getMostRecentFriend() {
        if (!friends.isEmpty()) {
            return friends.get(friends.size() - 1);
        }
        else {
            return null;
        }
    }

    public void addFriend(String friend) {
        friends.add(friend);
    }

    public void removeFriend(String friend) {
        friends.remove(friend);
    }

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

}
