package interface_adapter.friends_list_user_story.friends_list;

import java.util.List;

/**
 * State for Friends List.
 */
public class FriendsListState {
    private List<String> friends;
    private String error;
    private String username;
    private String password;
    private String friendPassword;
    private String friendUsername;

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    /**
     * Gets the most recent friend in the friends list.
     * @return mostRecentFriend.
     */
    public String getMostRecentFriend() {
        String mostRecentFriend = null;
        if (friends != null && !friends.isEmpty()) {
            mostRecentFriend = friends.get(friends.size() - 1);
        }
        return mostRecentFriend;
    }

    /**
     * Adds a friend to our friend list.
     * @param friend the friend to add.
     */
    public void addFriend(String friend) {
        friends.add(friend);
    }

    /**
     * Removes a friend to our friend list.
     * @param friend the friend to remove.
     */
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

    public String getFriendPassword() {
        return friendPassword;
    }

    public void setFriendPassword(String friendPassword) {
        this.friendPassword = friendPassword;
    }

    public String getFriendUsername() {
        return friendUsername;
    }

    public void setFriendUsername(String friendUsername) {
        this.friendUsername = friendUsername;
    }
}
