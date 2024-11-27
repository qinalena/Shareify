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
    private AddPlaylistOutputBoundary addPlaylistPresenter;
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
        // this.addPlaylistOutputBoundary = addPlaylistOutputBoundary;
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
        if (this.addPlaylistOutputBoundary == null) {
            System.err.println("Output boundary cannot be null!");
            return;
        }

        try {
            if (!user.getInfo().contains(playlistName)) {
                // Add playlist to playlist collection list
                playlistCollectionNames.add(playlistName);

                System.out.println("updated playlist: " + playlistCollectionNames);

                addPlaylistOutputBoundary.prepareSuccessView(playlistCollectionNames);
            }
            else {
                addPlaylistOutputBoundary.prepareFailureView("Playlist already exists!");
            }
        }
        catch (Exception e) {
            addPlaylistOutputBoundary.prepareFailureView(e.getMessage());
        }
//        try {
//            // Retrieve user from database
//            user = userDataAccessObject.get(username);
//            System.out.println("found User: " + user.getName());
//            System.out.println("playlist under user: " + user.getInfo());
//            if (user == null) {
//                addPlaylistPresenter.prepareFailureView("User not found: " + username);
//                return;
//            }
//
//            // Update and retrive user's playlist info
//            JSONObject userInfo = user.getInfo();
//            JSONArray playlistArray = userInfo.optJSONArray("playlist");
//            if (playlistArray == null) {
//                playlistArray = new JSONArray();
//            }
//
//            if (playlistArray.toList().contains(playlistName)) {
//                addPlaylistPresenter.prepareFailureView("Playlist already exists: " + playlistName);
//                return;
//            }
//
//            playlistArray.put(playlistName);
//            userInfo.put("playlist", playlistArray);
//            user.setInfo(userInfo);
//
//            // Update database
//            userDataAccessObject.addPlaylistToUser(user, playlistName);
//
//            addPlaylistPresenter.prepareSuccessView("Playlist added successfully!");

//            List<String> playlistNow = user.getInfo();
//            System.out.println("Current playlist: " + playlistNow);
//
//            if (playlistNow.contains(playlistName)) {
//                addPlaylistPresenter.prepareFailureView("Playlist already exists!");
//                return;
//            }
//            playlistNow.add(playlistName);
//
//            user.setInfo(playlistNow);
//
//            userDataAccessObject.addPlaylistToUser(user, playlistName);
//
//            // Add playlist to the database
//            JSONObject currentPlaylist = playlistDataAccessObject.addPlaylistinDB(user, playlistName);
//
//            if (currentPlaylist != null) {
//                playlistCollectionNames.add(playlistName);
//                addPlaylistPresenter.prepareSuccessView(playlistCollectionNames);
//
//                // Update user with new playlist collection
//                user.setInfo(playlistCollectionNames);
//                userDataAccessObject.addPlaylistToUser(user, playlistName);
//                System.out.println("Attempted to add playlist: " + playlistName + " for user: " + username);
//            }
//            else {
//                addPlaylistPresenter.prepareFailureView("Failed to add playlist!");
//            }
//            user.setInfo(playlistCollectionNames);
//
//        catch (DataAccessException ex) {
//            addPlaylistPresenter.prepareFailureView(ex.getMessage());
//        }
//        if (this.addPlaylistOutputBoundary == null) {
//            System.err.println("addPlaylistOutputBoundary is null");
//            return;
//        }
//        try {
//            // Checks to see if playlist exists in the database
//            final JSONArray currentPlaylist = playlistDataAccessObject.getPlaylists(User);
//            // Add playlist to the playlist collection list
//            if (currentPlaylist != null) {
//                // Add playlist to playlist collection
//                playlistList.add(playlistName);
//                // Pass updated playlist list to output boundary
//                addPlaylistOutputBoundary.prepareSuccessView(playlistList);
//            }
//            // Case that the playlist already exist
//            else {
//                addPlaylistOutputBoundary.prepareFailureView("Playlist already exists!");
//            }
//        }
//        catch (DataAccessException evt) {
//            addPlaylistOutputBoundary.prepareFailureView(evt.getMessage());
//        }
    }

    @Override
    public void switchToPlaylistCollectionView() {
        addPlaylistPresenter.switchToPlaylistCollectionView();
    }
}