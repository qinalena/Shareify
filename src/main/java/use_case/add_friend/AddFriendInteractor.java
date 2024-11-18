package use_case.add_friend;

import data_access.DBNoteDataAccessObject;
import data_access.DBUserDataAccessObject;
import use_case.add_friend.AddFriendOutputBoundary;
import java.util.List;
import interface_adapter.login.LoginState;
import entity.User;

public class AddFriendInteractor implements AddFriendInputBoundary {

    private final DBNoteDataAccessObject dbNoteDataAccessObject;
    private final AddFriendOutputBoundary outputBoundary;
    private final List<String> friendsList;  // The list of friends
    private final DBUserDataAccessObject dbUserDataAccessObject;
    private final String username = LoginState.getUsername();

    public AddFriendInteractor(DBNoteDataAccessObject dbNoteDataAccessObject,
                               AddFriendOutputBoundary outputBoundary,
                               List<String> friendsList, DBUserDataAccessObject dbUserDataAccessObject) {
        if (outputBoundary == null) {
            throw new NullPointerException("Output boundary cannot be null");
        }
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;
        this.outputBoundary = outputBoundary;
        this.friendsList = friendsList;  // Initialize the friends list
        this.dbUserDataAccessObject = dbUserDataAccessObject;
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
            User foundUsername = dbUserDataAccessObject.get(friendName);

            if (foundUsername != null) {
                // Add friend to the friends list
                friendsList.add(friendName);
                User user = dbUserDataAccessObject.get(username);
                Object existedlist = user.getInfo().get("Friends");
                List existedlist1 = (List) existedlist;
                existedlist1.add(friendName);
                dbNoteDataAccessObject.updateUserInfo(user, "Friends", existedlist1);

                // Pass the updated friends list to the output boundary
                outputBoundary.prepareSuccessView(existedlist1);  // Updated friends list
            } else {
                // If user doesn't exist, pass an error message
                outputBoundary.prepareFailView("User does not exist.");
            }
        } catch (Exception e) {
            // Handle errors and pass a failure message
            outputBoundary.prepareFailView("Error adding friend.");
        }
    }
}