package use_case.friends_list_user_story.friend_profile_friends_list;

import data_access.DBNoteDataAccessObject;
import use_case.user_profile_user_story.note.DataAccessException;

import java.util.List;

public class FriendProfileFriendsListInteractor implements FriendProfileFriendsListInputBoundary {
    private final FriendProfileFriendsListOutputBoundary presenter;
    private final DBNoteDataAccessObject dbNoteDataAccessObject;

    public FriendProfileFriendsListInteractor(FriendProfileFriendsListOutputBoundary presenter,
                                              DBNoteDataAccessObject dbNoteDataAccessObject) {
        if (presenter == null) {
            throw new NullPointerException("Presenter cannot be null");
        }
        this.presenter = presenter;
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;
    }

    @Override
    public void switchToFriendProfileView(String selectedFriendName, String password) {
        presenter.switchToFriendProfileView(selectedFriendName, password);
    }

    @Override
    public void executeGetFriends(String username) {
        final List<String> friends;
        try {
            friends = dbNoteDataAccessObject
                    .getFriends(username);
            presenter.prepareGetFriendsSuccessView(friends);
        } catch (DataAccessException e) {
            presenter.prepareFailView(e.getMessage());
        }
    }
}
