package use_case.add_friend;

public interface AddFriendInputBoundary {
    void execute(String friendName);
    void switchToFriendsListView();
}
