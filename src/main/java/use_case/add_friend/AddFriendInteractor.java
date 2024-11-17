package use_case.add_friend;

import data_access.DBNoteDataAccessObject;
import use_case.add_friend.AddFriendOutputBoundary;
import java.util.List;

public class AddFriendInteractor implements AddFriendInputBoundary {

    private final DBNoteDataAccessObject dbNoteDataAccessObject;
    private final AddFriendOutputBoundary outputBoundary;
    private final List<String> friendsList;  // The list of friends

    public AddFriendInteractor(DBNoteDataAccessObject dbNoteDataAccessObject,
                               AddFriendOutputBoundary outputBoundary,
                               List<String> friendsList) {
        if (outputBoundary == null) {
            throw new NullPointerException("Output boundary cannot be null");
        }
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;
        this.outputBoundary = outputBoundary;
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
            String foundUsername = dbNoteDataAccessObject.getUserByUsername(friendName);

            if (foundUsername != null) {
                // Add friend to the friends list
                friendsList.add(friendName);

                // Pass the updated friends list to the output boundary
                outputBoundary.prepareSuccessView(friendsList);  // Updated friends list
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