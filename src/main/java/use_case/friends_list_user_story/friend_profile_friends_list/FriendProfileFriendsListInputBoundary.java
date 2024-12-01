package use_case.friends_list_user_story.friend_profile_friends_list;

public interface FriendProfileFriendsListInputBoundary {
    void switchToFriendProfileView(String selectedFriendName, String password);
    void executeGetFriends(String username);
}
