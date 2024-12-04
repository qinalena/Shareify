package interface_adapter.friends_list_user_story.friends_list;

import use_case.friends_list_user_story.friends_list.FriendsListInputBoundary;

/**
 * Controller for the friends list.
 */
public class FriendsListController {
    private final FriendsListInputBoundary interactor;

    public FriendsListController(FriendsListInputBoundary interactor) {
        this.interactor = interactor;
    }

    /**
     * Executes the "switch to NoteView" Use Case.
     */
    public void switchToNoteView() {
        interactor.switchToNoteView();
    }

    /**
     * Executes the "switch to FriendsListView" Use Case.
     */
    public void switchToFriendsListView() {
        interactor.switchToFriendsListView();
    }

    /**
     * Executes the "switch to FriendProfileView" Use Case.
     * @param selectedFriendName your friend's username.
     * @param password your friend's password.
     */
    public void switchToFriendProfileView(String selectedFriendName, String password) {
        interactor.switchToFriendProfileView(selectedFriendName, password);
    }

    /**
     * Executes the "switch to PlaylistCollectionView" Use Case.
     */
    public void switchToPlaylistCollectionView() {
        interactor.switchToPlaylistCollectionView();
    }

    /**
     * Executes the "switch to AddFriendView" Use Case.
     */
    public void switchToAddFriendView() {
        interactor.switchToAddFriendView();
    }

    /**
     * Executes the "switch to User Profile View" Use Case.
     */
    public void switchToUserProfileView() {
        interactor.switchToUserProfileView();
    }

    /**
     * Executes the get friends use case to call friends of `username` to the DB.
     * @param username the logged-in user's username.
     */
    public void executeGetFriends(String username) {
        interactor.executeGetFriends(username);
    }

    /**
     * Executes the removeFriendInDB to delete a friend from your friends list.
     * @param username the username.
     * @param password the password.
     * @param idx the index that the friend is stored in its list.
     */
    public void executeRemoveFriendInDB(String username, String password, int idx) {
        interactor.executeRemoveFriendInDB(username, password, idx);
    }

    /**
     * Executes the get password by username use case to get the credentials needed for chat and comment in
     * later user stories that are connected to this.
     * @param username the friend's username.
     */
    public void executeGetPasswordByUserName(String username) {
        interactor.executeGetPasswordByUserName(username);
    }
}
