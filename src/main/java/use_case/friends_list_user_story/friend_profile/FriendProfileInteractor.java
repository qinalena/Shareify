package use_case.friends_list_user_story.friend_profile;

import use_case.user_profile_user_story.note.NoteDataAccessInterface;

public class FriendProfileInteractor implements FriendProfileInputBoundary {
    private NoteDataAccessInterface noteDataAccessInterface;
    private FriendProfileOutputBoundary friendProfilePresenter;

    public FriendProfileInteractor(NoteDataAccessInterface noteDataAccessInterface, FriendProfileOutputBoundary friendProfilePresenter) {
        this.noteDataAccessInterface = noteDataAccessInterface;
        this.friendProfilePresenter = friendProfilePresenter;
    }

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
