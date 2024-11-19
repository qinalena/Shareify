package use_case.add_playlist;

import data_access.DBNoteDataAccessObject;
import use_case.add_playlist.AddPlaylistOutputBoundary;
import java.util.List;

public class AddPlaylistInteractor implements AddPlaylistInputBoundary {

    private final DBNoteDataAccessObject dbNoteDataAccessObject;
    private final AddPlaylistOutputBoundary addPlaylistOutputBoundary;
    private final List<String> playlistList;

    public AddPlaylistInteractor(DBNoteDataAccessObject dbNoteDataAccessObject,
                                 AddPlaylistOutputBoundary addPlaylistOutputBoundary,
                                 List<String> playlistList) {
        this.dbNoteDataAccessObject = dbNoteDataAccessObject;
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
        try {
            final String findUser = dbNoteDataAccessObject.getUserByUsername(playlistName);
            // Add playlist to the playlist collection list
            if (findUser != null) {
                playlistList.add(playlistName);
                addPlaylistOutputBoundary.prepareSuccessView(playlistList);
            }
            // Case that the user doesn't exist
            else {
                addPlaylistOutputBoundary.prepareFailureView("User does not exist.");
            }
        }
        catch (Exception e) {
            addPlaylistOutputBoundary.prepareFailureView(e.getMessage());
        }
    }
}
