package use_case.add_playlist;

import java.util.List;

import data_access.DBUserDataAccessObject;

/**
 * The "Use Case Interactor" for our adding playlist related use cases.
 */
public class AddPlaylistInteractor implements AddPlaylistInputBoundary {

    private final DBUserDataAccessObject dbUserDataAccessObject;
    private final AddPlaylistOutputBoundary addPlaylistOutputBoundary;
    private final List<String> playlistList;

    public AddPlaylistInteractor(DBUserDataAccessObject dbUserDataAccessObject,
                                 AddPlaylistOutputBoundary addPlaylistOutputBoundary,
                                 List<String> playlistList) {
        this.dbUserDataAccessObject = dbUserDataAccessObject;
        this.addPlaylistOutputBoundary = addPlaylistOutputBoundary;
        this.playlistList = playlistList;

        if (addPlaylistOutputBoundary == null) {
            throw new NullPointerException("addPlaylistOutputBoundary is null");
        }
    }

    @Override
    public void execute(String playlistName) {
        if (this.addPlaylistOutputBoundary == null) {
            System.err.println("addPlaylistOutputBoundary is null");
            return;
        }
        // Returns the current logged-in user's username
        final String currentUser = dbUserDataAccessObject.getCurrentUsername();
        // Add playlist to the playlist collection list
        if (currentUser != null) {
            // Add playlist to user's profile
            playlistList.add(playlistName);
            addPlaylistOutputBoundary.prepareSuccessView(playlistList);
        }
        // Case that the user doesn't exist
        else {
            addPlaylistOutputBoundary.prepareFailureView("User does not exist.");
        }
    }
}
