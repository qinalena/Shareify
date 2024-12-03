package use_case.friends_list_user_story.friend_profile_friends_list;

import data_access.DataAccessException;

import java.util.List;

public class FriendProfileFriendsListInteractor implements FriendProfileFriendsListInputBoundary {
    private final FriendProfileFriendsListOutputBoundary presenter;
    private final FriendProfileFriendsListDataAccessInterface friendsListDataAccessInterface;

    public FriendProfileFriendsListInteractor(FriendProfileFriendsListOutputBoundary presenter,
                                              FriendProfileFriendsListDataAccessInterface friendsListDataAccessInterface) {
        this.presenter = presenter;
        this.friendsListDataAccessInterface = friendsListDataAccessInterface;
    }

    @Override
    public void switchToFriendProfileView(String selectedFriendName, String password) {
        presenter.switchToFriendProfileView(selectedFriendName, password);
    }

    @Override
    public void executeGetFriends(String username) {
        final List<String> friends;
        try {
            friends = friendsListDataAccessInterface
                    .getFriends(username);
            presenter.prepareGetFriendsSuccessView(friends);
        } catch (DataAccessException e) {
            presenter.prepareFailView(e.getMessage());
        }
    }
}
