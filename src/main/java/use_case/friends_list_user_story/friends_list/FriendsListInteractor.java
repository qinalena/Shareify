package use_case.friends_list_user_story.friends_list;


import data_access.DBFriendDataAccessObject;
import entity.User;
import data_access.DataAccessException;

import java.util.List;

public class FriendsListInteractor implements FriendsListInputBoundary {
    private final FriendsListOutputBoundary presenter;
    private FriendsListDataAccessInterface friendsListDataAccessInterface;

    public FriendsListInteractor(FriendsListOutputBoundary presenter,
                                 FriendsListDataAccessInterface friendsListDataAccessInterface) {
        this.presenter = presenter;
        this.friendsListDataAccessInterface = friendsListDataAccessInterface;
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
            friends = friendsListDataAccessInterface
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
            friendsListDataAccessInterface.removeFriendinDB(user, idx);
        }
        catch (DataAccessException ext) {
            presenter.prepareFailView(ext.getMessage());
        }
    }

    @Override
    public void executeGetPasswordByUserName(String username) {
        try {
            final String friendPassword = friendsListDataAccessInterface.getPasswordByUserName(username);
            presenter.prepareGetFriendPasswordbyUserNameSuccessView(friendPassword);
        }
        catch (DataAccessException ext) {
            presenter.prepareFailView(ext.getMessage());
        }
    }
}

