package use_case.friends_list_user_story.friend_profile_friends_list;

import java.util.List;

public interface FriendProfileFriendsListOutputBoundary {
    void switchToFriendProfileView(String selectedFriendName, String password);
    void prepareGetFriendsSuccessView(List<String> friends);
    void prepareFailView(String error);
}
