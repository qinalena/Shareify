package use_case.friends_list_user_story.add_friend;

import data_access.DBFriendDataAccessObject;
import entity.User;
import data_access.DataAccessException;

import java.util.ArrayList;
import java.util.List;

public class AddFriendInteractor implements AddFriendInputBoundary {

    private final AddFriendDataAccessInterface addFriendDataAccessInterface;
    private final AddFriendOutputBoundary outputBoundary;
    private final List<String> friendsList = new ArrayList<>();
    private AddFriendOutputBoundary addFriendPresenter;

    public AddFriendInteractor(AddFriendDataAccessInterface addFriendDataAccessInterface,
                               AddFriendOutputBoundary outputBoundary) {
        if (outputBoundary == null) {
            throw new NullPointerException("Output boundary cannot be null");
        }
        this.addFriendDataAccessInterface = addFriendDataAccessInterface;
        this.outputBoundary = outputBoundary;
        this.addFriendPresenter = outputBoundary;
    }

    @Override
    public void execute(String friendName) {
        // Add friend to the friends list
        friendsList.add(friendName);

        // Pass the updated friends list to the output boundary
        outputBoundary.prepareSuccessView(friendsList, friendName);
    }

    @Override
    public void switchToFriendsListView() {
        addFriendPresenter.swtichToFriendsListView();
    }

    @Override
    public void executeGetUserByUserName(String userName) {
        try {
            final String username = addFriendDataAccessInterface.getUserByUsername(userName);
            outputBoundary.prepareGetUserByUserNameSuccessView(username);
        } catch (DataAccessException e) {
            outputBoundary.prepareFailView(e.getMessage());
        }
    }

    @Override
    public void executeAddFriendInDB(User user, String friendName) {
        try {
            addFriendDataAccessInterface.addFriendinDB(user, friendName);
        } catch (DataAccessException e) {
            outputBoundary.prepareFailView(e.getMessage());
        }
    }
}