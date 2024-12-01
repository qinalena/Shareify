package use_case.friends_list_user_story.add_friend;

import data_access.DBNoteDataAccessObject;
import entity.User;
import interface_adapter.friends_list_user_story.add_friend.AddFriendPresenter;
import use_case.friends_list_user_story.add_friend.AddFriendOutputBoundary;
import use_case.user_profile_user_story.note.DataAccessException;

import java.util.ArrayList;
import data_access.DBFriendDataAccessObject;

import java.util.List;

public class AddFriendInteractor implements AddFriendInputBoundary {

    private final DBFriendDataAccessObject dbFriendDataAccessObject;
    private final AddFriendOutputBoundary outputBoundary;
    private final List<String> friendsList = new ArrayList<>();
    private AddFriendOutputBoundary addFriendPresenter;

    public AddFriendInteractor(DBFriendDataAccessObject dbFriendDataAccessObject,
                               AddFriendOutputBoundary outputBoundary) {
        if (outputBoundary == null) {
            throw new NullPointerException("Output boundary cannot be null");
        }
        this.dbFriendDataAccessObject = dbFriendDataAccessObject;
        this.outputBoundary = outputBoundary;
        this.addFriendPresenter = outputBoundary;
    }

    @Override
    public void execute(String friendName) {
        if (this.outputBoundary == null) {
            // Handle the case where outputBoundary is null
            System.err.println("Output boundary is null");
            return;
        }

        try {
            // Check if the user exists in the database
            String foundUsername = dbFriendDataAccessObject.getUserByUsername(friendName);

            if (foundUsername != null) {
                // Add friend to the friends list
                friendsList.add(friendName);
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
            final String username = dbNoteDataAccessObject.getUserByUsername(userName);
            outputBoundary.prepareGetUserByUserNameSuccessView(username);
        } catch (DataAccessException e) {
            outputBoundary.prepareFailView(e.getMessage());
        }
    }

    @Override
    public void executeAddFriendInDB(User user, String friendName) {
        try {
            dbNoteDataAccessObject.addFriendinDB(user, friendName);
        } catch (DataAccessException e) {
            outputBoundary.prepareFailView(e.getMessage());
        }
    }
}