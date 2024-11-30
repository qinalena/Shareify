package use_case.friends_list_user_story.friends_list;

import entity.User;

public interface FriendsListInputBoundary {
    void addFriend(String friendName);
    void deleteFriend(String friendName);
    void switchToNoteView();
    void switchToFriendsListView();
    void switchToFriendProfileView(String selectedFriendName, String password);
    void switchToPlaylistCollectionView();
    void switchToAddFriendView();
    void switchToUserProfileView();
    void executeGetFriends(String username);
    void executeRemoveFriendInDB(User user, int idx);
    void executeGetPasswordByUserName(String username);
}

