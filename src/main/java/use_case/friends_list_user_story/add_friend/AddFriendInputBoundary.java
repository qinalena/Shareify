package use_case.friends_list_user_story.add_friend;

import entity.User;

public interface AddFriendInputBoundary {
    void execute(String friendName);
    void switchToFriendsListView();
    void executeGetUserByUserName(String userName);
    void executeAddFriendInDB(String username, String password, String friendName);
}
