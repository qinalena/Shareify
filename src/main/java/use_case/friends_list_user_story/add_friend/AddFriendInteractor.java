package use_case.friends_list_user_story.add_friend;

import data_access.DBFriendDataAccessObject;

import java.util.List;

public class AddFriendInteractor implements AddFriendInputBoundary {

    private final DBFriendDataAccessObject dbFriendDataAccessObject;
    private final AddFriendOutputBoundary outputBoundary;
    private final List<String> friendsList;
    private AddFriendOutputBoundary addFriendPresenter;

    public AddFriendInteractor(DBFriendDataAccessObject dbFriendDataAccessObject,
                               AddFriendOutputBoundary outputBoundary,
                               List<String> friendsList) {
        if (outputBoundary == null) {
            throw new NullPointerException("Output boundary cannot be null");
        }
        this.dbFriendDataAccessObject = dbFriendDataAccessObject;
        this.outputBoundary = outputBoundary;
        this.addFriendPresenter = outputBoundary;
        this.friendsList = friendsList;  // Initialize the friends list
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

                // Pass the updated friends list to the output boundary
                outputBoundary.prepareSuccessView(friendsList);
            } else {
                // If user doesn't exist, pass an error message
                outputBoundary.prepareFailView("User does not exist.");
            }
        } catch (Exception e) {
            // Handle errors and pass a failure message
            outputBoundary.prepareFailView("Error adding friend.");
        }
    }

    @Override
    public void switchToFriendsListView() {
        addFriendPresenter.swtichToFriendsListView();
    }
}