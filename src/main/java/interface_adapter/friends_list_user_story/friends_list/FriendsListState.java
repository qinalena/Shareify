package interface_adapter.friends_list_user_story.friends_list;

import java.util.ArrayList;
import java.util.List;

public class FriendsListState {
    private final List<String> friends = new ArrayList<>();
    private String error;

    public List<String> getFriends() {
        return new ArrayList<>(friends);
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
}
