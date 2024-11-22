package use_case.friends_list;

public interface FriendsListInputBoundary {
    void addFriend(String friendName);
    void deleteFriend(String friendName);
    void switchToNoteView();
    void switchToFriendsListView();
    void switchToFriendProfileView();
}

