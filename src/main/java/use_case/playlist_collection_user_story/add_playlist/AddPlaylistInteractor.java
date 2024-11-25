package use_case.playlist_collection_user_story.add_playlist;

import java.util.List;

import data_access.DBPlaylistDataAccessObject;
import use_case.user_profile_user_story.note.DataAccessException;

/**
 * The "Use Case Interactor" for our adding playlist related use cases.
 */
public class AddPlaylistInteractor implements AddPlaylistInputBoundary {

    private final DBPlaylistDataAccessObject playlistDataAccessObject;
    private final AddPlaylistOutputBoundary addPlaylistOutputBoundary;
    private final List<String> playlistList;
    private AddPlaylistOutputBoundary addPlaylistPresenter;

    public AddPlaylistInteractor(DBPlaylistDataAccessObject playlistDataAccessObject,
                                 AddPlaylistOutputBoundary addPlaylistOutputBoundary,
                                 List<String> playlistList) {
        if (addPlaylistOutputBoundary == null) {
            throw new NullPointerException("Output boundary cannot be null!");
        }
        this.playlistDataAccessObject = playlistDataAccessObject;
        this.addPlaylistOutputBoundary = addPlaylistOutputBoundary;
        this.addPlaylistPresenter = addPlaylistOutputBoundary;
        this.playlistList = playlistList;
    }

    @Override
    public void execute(String playlistName) {
        if (this.addPlaylistOutputBoundary == null) {
            System.err.println("addPlaylistOutputBoundary is null");
            return;
        }
        try {
            // Checks to see if playlist exists in the database
            final String currentPlaylist = playlistDataAccessObject.getPlaylists(playlistName).toString();
            // Add playlist to the playlist collection list
            if (currentPlaylist != null) {
                // Add playlist to playlist collection
                playlistList.add(playlistName);
                // Pass updated playlist list to output boundary
                addPlaylistOutputBoundary.prepareSuccessView(playlistList);
            }
            // Case that the playlist already exist
            else {
                addPlaylistOutputBoundary.prepareFailureView("Playlist already exists!");
            }
        }
        catch (DataAccessException evt) {
            addPlaylistOutputBoundary.prepareFailureView(evt.getMessage());
        }
    }

    @Override
    public void switchToPlaylistCollectionView() {
        addPlaylistPresenter.switchToPlaylistCollectionView();
    }
}