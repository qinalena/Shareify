package interface_adapter.friend_profile;

import use_case.friend_profile.FriendProfileInputBoundary;

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
    public void switchToFriendsListView() {
        friendProfileInteractor.switchToFriendsListView();
    }
}
