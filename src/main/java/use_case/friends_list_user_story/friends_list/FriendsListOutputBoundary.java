package use_case.friends_list_user_story.friends_list;

public interface FriendsListOutputBoundary {
    void presentFriendAdded(String friendName);
    void presentFriendDeleted(String friendName);
    void presentError(String errorMessage);
}