package use_case.friends_list;


public class FriendsListInteractor implements FriendsListInputBoundary {
    private final FriendsListOutputBoundary presenter;

    public FriendsListInteractor(FriendsListOutputBoundary presenter) {
        this.presenter = presenter;
    }

    @Override
    public void addFriend(String friendName) {
        if (friendName == null || friendName.isEmpty()) {
            presenter.presentError("Friend name cannot be empty.");
            return;
        }
        presenter.presentFriendAdded(friendName);
    }

    @Override
    public void deleteFriend(String friendName) {
        presenter.presentFriendDeleted(friendName);
    }

    @Override
    public void switchToNoteView() {
        presenter.switchToNoteView();
    }

    @Override
    public void switchToFriendsListView() {
        presenter.switchToFriendsListView();
    }

    @Override
    public void switchToFriendProfileView() {
        presenter.switchToFriendProfileView();
    }

    @Override
    public void switchToPlaylistCollectionView() {
        presenter.switchToPlaylistCollectionView();
    }

    @Override
    public void switchToAddFriendView() {
        presenter.switchToAddFriendView();
    }
}

