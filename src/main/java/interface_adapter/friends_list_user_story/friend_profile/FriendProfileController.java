package interface_adapter.friends_list_user_story.friend_profile;

import use_case.friends_list_user_story.friend_profile.FriendProfileInputBoundary;

/**
 * Controller for friend Profile.
 */
public class FriendProfileController {
    private final FriendProfileInputBoundary friendProfileInteractor;

    public FriendProfileController(FriendProfileInputBoundary friendProfileInteractor) {
        this.friendProfileInteractor = friendProfileInteractor;
    }

    /**
     * Executes the "switch to NoteView" Use Case.
     */
    public void switchToNoteView() {
        friendProfileInteractor.switchToNoteView();
    }

    /**
     * Executes the switch to friends list view use case.
     * @param username the username.
     * @param password the password.
     */
    public void switchToFriendsListView(String username, String password) {
        friendProfileInteractor.switchToFriendsListView(username, password);
    }

    /**
     * Executes the switch to playlist collection view.
     * @param username the username.
     * @param password the password.
     */
    public void switchToPlaylistCollectionView(String username, String password) {
        friendProfileInteractor.switchToPlaylistCollectionView(username, password);
    }

    /**
     * Executes the switch to chat view.
     * @param friendUsername the  username of your friend
     */
    public void switchToChatView(String friendUsername) {
        friendProfileInteractor.switchToChatView(friendUsername);
    }

    /**
     * Executes the switch back to the view of all your friends.
     */
    public void switchToAllFriendsView() {
        friendProfileInteractor.switchToAllFriendsView();
    }

}
