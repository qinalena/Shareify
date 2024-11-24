package interface_adapter.friends_list_user_story.friends_list;

import use_case.friends_list_user_story.friends_list.FriendsListInputBoundary;

public class FriendsListController {
    private final FriendsListInputBoundary interactor;

    public FriendsListController(FriendsListInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void addFriend(String friendName) {
        interactor.addFriend(friendName);
    }

    public void deleteFriend(String friendName) {
        interactor.deleteFriend(friendName);
    }

    /**
     * Executes the "switch to NoteView" Use Case.
     */
    public void switchToNoteView() {
        interactor.switchToNoteView();
    }
    public void switchToFriendsListView() {
        interactor.switchToFriendsListView();
    }

    public void switchToFriendProfileView(String selectedFriendName, String password) {
        interactor.switchToFriendProfileView(selectedFriendName, password);
    }

    public void switchToPlaylistCollectionView() {
        interactor.switchToPlaylistCollectionView();
    }

    public void switchToAddFriendView() {
        interactor.switchToAddFriendView();
    }

    public void switchToUserProfileView() {
        interactor.switchToUserProfileView();
    }
}
