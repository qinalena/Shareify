package interface_adapter.friends_list_user_story.friend_profile;

import use_case.friends_list_user_story.friend_profile.FriendProfileInputBoundary;

public class FriendProfileController {
    private final FriendProfileInputBoundary friendProfileInteractor;

    public FriendProfileController(FriendProfileInputBoundary friendProfileInteractor) {
        this.friendProfileInteractor = friendProfileInteractor;
    }

    /**
     * Executes the User Profile related Use Cases.
     */
    public void execute() {

    }

    /**
     * Executes the "switch to NoteView" Use Case.
     */
    public void switchToNoteView() {
        friendProfileInteractor.switchToNoteView();
    }

    public void switchToFriendsListView(String username, String password) {
        friendProfileInteractor.switchToFriendsListView(username, password);
    }
    public void switchToPlaylistCollectionView(String username, String password) {
        friendProfileInteractor.switchToPlaylistCollectionView(username, password);
    }
    public void switchToAllFriendsView() {
        friendProfileInteractor.switchToAllFriendsView();
    }

}
