package use_case.playlist_collection_user_story.add_playlist;

import java.util.List;

import data_access.DBPlaylistDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.User;
import org.json.JSONArray;
import org.json.JSONObject;
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionDataAccessInterface;
import use_case.user_profile_user_story.note.DataAccessException;

/**
 * The "Use Case Interactor" for our adding playlist related use cases.
 */
public class AddPlaylistInteractor implements AddPlaylistInputBoundary {

    private final DBPlaylistDataAccessObject playlistDataAccessObject;
    private final DBUserDataAccessObject userDataAccessObject;
    private final AddPlaylistOutputBoundary addPlaylistOutputBoundary;
    private final List<String> playlistCollectionNames;
    private final AddPlaylistOutputBoundary addPlaylistPresenter;
    private entity.User user;

    public AddPlaylistInteractor(DBPlaylistDataAccessObject playlistDataAccessObject,
                                 DBUserDataAccessObject userDataAccessObject,
                                 AddPlaylistOutputBoundary addPlaylistOutputBoundary,
                                 List<String> playlistList) {
        if (addPlaylistOutputBoundary == null) {
            throw new NullPointerException("Output boundary cannot be null!");
        }
        this.playlistDataAccessObject = playlistDataAccessObject;
        this.userDataAccessObject = userDataAccessObject;
        this.addPlaylistOutputBoundary = addPlaylistOutputBoundary;
        this.addPlaylistPresenter = addPlaylistOutputBoundary;
        this.playlistCollectionNames = playlistList;
    }

    /**
     * Executes the save playlist use case.
     * @param playlistName inputted name of playlist
     */
    @Override
    public void executeCreatePlaylist(String playlistName) {
        try {
            if (!user.getInfo().contains(playlistName)) {
                // Add playlist to playlist collection list
                playlistCollectionNames.add(playlistName);

                // Debugging
                System.out.println("updated playlist: " + playlistCollectionNames);

                // Pass update to output boundary
                addPlaylistOutputBoundary.prepareSuccessView(playlistCollectionNames);
            }
            else {
                addPlaylistOutputBoundary.prepareFailureView("Playlist already exists!");
            }
        }
        catch (Exception e) {
            addPlaylistOutputBoundary.prepareFailureView(e.getMessage());
        }
    }

    @Override
    public void switchToPlaylistCollectionView() {
        addPlaylistPresenter.switchToPlaylistCollectionView();
    }
}