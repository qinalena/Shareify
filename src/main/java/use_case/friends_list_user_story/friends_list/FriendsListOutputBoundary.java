package use_case.friends_list_user_story.friends_list;

import java.util.List;

public interface FriendsListOutputBoundary {
    void presentFriendAdded(String friendName);
    void presentFriendDeleted(String friendName);
    void presentError(String errorMessage);
    void switchToNoteView();
    void switchToFriendsListView();
    void switchToFriendProfileView(String selectedFriendName, String password);
    void switchToPlaylistCollectionView();
    void switchToAddFriendView();
    void switchToUserProfileView();
    void prepareGetFriendsSuccessView(List<String> friends);
    void prepareFailView(String error);
    void prepareGetFriendPasswordbyUserNameSuccessView(String password);
}