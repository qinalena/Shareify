package use_case.friends_list_user_story.friends_list;

public interface FriendsListInputBoundary {
    void switchToNoteView();
    void switchToFriendsListView();
    void switchToFriendProfileView(String selectedFriendName, String password);
    void switchToPlaylistCollectionView();
    void switchToAddFriendView();
    void switchToUserProfileView();
    void executeGetFriends(String username);
    void executeRemoveFriendInDB(String username, String password, int idx);
    void executeGetPasswordByUserName(String username);
}

