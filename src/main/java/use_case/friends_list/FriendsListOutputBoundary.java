package use_case.friends_list;

public interface FriendsListOutputBoundary {
    void presentFriendAdded(String friendName);
    void presentFriendDeleted(String friendName);
    void presentError(String errorMessage);
    void switchToNoteView();
    void switchToFriendsListView();
    void switchToFriendProfileView(String selectedFriendName, String password);
    void switchToPlaylistCollectionView();
    void switchToAddFriendView();
}