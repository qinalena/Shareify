package use_case.friends_list_user_story.friend_profile;

/**
 * Interactor for the Friend Profile.
 */
public class FriendProfileInteractor implements FriendProfileInputBoundary {
    private FriendProfileOutputBoundary friendProfilePresenter;

    public FriendProfileInteractor(FriendProfileOutputBoundary friendProfilePresenter) {
        this.friendProfilePresenter = friendProfilePresenter;
    }

    @Override
    public void switchToNoteView() {
        friendProfilePresenter.switchToNoteView();
    }

    @Override
    public void switchToFriendsListView(String username, String password) {
        friendProfilePresenter.switchToFriendsListView(username, password);
    }

    @Override
    public void switchToPlaylistCollectionView(String username, String password) {
        friendProfilePresenter.switchToPlaylistCollectionView(username, password);
    }

    @Override
    public void switchToChatView(String friendUsername) {
        friendProfilePresenter.switchToChatView(friendUsername);
    }

    @Override
    public void switchToAllFriendsView() {
        friendProfilePresenter.switchToAllFriendsView();
    }
}
