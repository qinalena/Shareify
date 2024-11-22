package use_case.friend_profile;

import use_case.note.NoteDataAccessInterface;
import use_case.user_profile.UserProfileInputBoundary;

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

    public void switchToFriendsListView() {
        friendProfilePresenter.switchToFriendsListView();
    }

    @Override
    public void switchToPlaylistCollectionView() {
        friendProfilePresenter.switchToPlaylistCollectionView();
    }
}
