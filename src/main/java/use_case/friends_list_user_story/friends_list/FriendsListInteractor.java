package use_case.friends_list_user_story.friends_list;


import data_access.DBNoteDataAccessObject;
import entity.User;
import use_case.user_profile_user_story.note.DataAccessException;

import java.util.List;

public class FriendsListInteractor implements FriendsListInputBoundary {
    private final FriendsListOutputBoundary presenter;
    private DBNoteDataAccessObject dbNoteDataAccessObject;

    public FriendsListInteractor(FriendsListOutputBoundary presenter, DBNoteDataAccessObject dbNoteDataAccessObject) {
        this.presenter = presenter;
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;
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
    public void switchToFriendProfileView(String selectedFriendName, String password) {
        presenter.switchToFriendProfileView(selectedFriendName, password);
    }

    @Override
    public void switchToPlaylistCollectionView() {
        presenter.switchToPlaylistCollectionView();
    }

    @Override
    public void switchToAddFriendView() {
        presenter.switchToAddFriendView();
    }

    @Override
    public void switchToUserProfileView() {
        presenter.switchToUserProfileView();
    }

    @Override
    public void executeGetFriends(String username) {
        final List<String> friends;
        try {
            friends = dbNoteDataAccessObject
                    .getFriends(username);
            presenter.prepareGetFriendsSuccessView(friends);
        }
        catch (DataAccessException ext) {
            presenter.prepareFailView(ext.getMessage());
        }
    }

    @Override
    public void executeRemoveFriendInDB(User user, int idx) {
        try {
            dbNoteDataAccessObject.removeFriendinDB(user, idx);
        }
        catch (DataAccessException ext) {
            presenter.prepareFailView(ext.getMessage());
        }
    }

    @Override
    public void executeGetPasswordByUserName(String username) {
        try {
            final String friendPassword = dbNoteDataAccessObject.getPasswordByUserName(username);
            presenter.prepareGetFriendPasswordbyUserNameSuccessView(friendPassword);
        }
        catch (DataAccessException ext) {
            presenter.prepareFailView(ext.getMessage());
        }
    }
}

