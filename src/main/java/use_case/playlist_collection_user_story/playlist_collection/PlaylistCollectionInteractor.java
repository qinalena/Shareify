package use_case.playlist_collection_user_story.playlist_collection;

import entity.Playlist;
import entity.Song;
import entity.User;
import data_access.DBPlaylistDataAccessObject;
import use_case.user_profile_user_story.note.DataAccessException;

import java.util.List;

/**
 * The "Use Case Interactor" for our playlist collection related use cases of creating
 * a playlist.
 */

public class PlaylistCollectionInteractor implements PlaylistCollectionInputBoundary {
    private final PlaylistCollectionOutputBoundary playlistCollectionPresenter;
    private final DBPlaylistDataAccessObject dbPlaylistDataAccessObject;

    public PlaylistCollectionInteractor(PlaylistCollectionOutputBoundary playlistCollectionPresenter) {
        this.playlistCollectionPresenter = playlistCollectionPresenter;
        this.dbPlaylistDataAccessObject = new DBPlaylistDataAccessObject();
    }

    @Override
    public void addPlaylist(User user, String playlistName) {
        if (playlistName == null || playlistName.isEmpty()) {
            playlistCollectionPresenter.prepareFailView("Playlist name cannot be empty.");
            return;
        }
        try {
            // Call DAO to add playlist to database
            // individual playlist
            dbPlaylistDataAccessObject.addPlaylistinDB(user, playlistName);

            playlistCollectionPresenter.preparePlaylistAddedView(playlistName);
        }
        catch (DataAccessException e) {
            playlistCollectionPresenter.prepareFailView("Failed to add playlist: " + e.getMessage());
        }
    }

    @Override
    public void removePlaylist(User user, String playlistName) {
        if (playlistName == null || playlistName.isEmpty()) {
            playlistCollectionPresenter.prepareFailView("Playlist name cannot be empty.");
            return;
        }
        try {
            // Call to DAO to remove playlist from the database
            dbPlaylistDataAccessObject.removePlaylistinDB(user, playlistName);
            playlistCollectionPresenter.preparePlaylistRemovedView(playlistName);
        }
        catch (DataAccessException e) {
            playlistCollectionPresenter.prepareFailView("Failed to remove playlist: " + e.getMessage());
        }
        playlistCollectionPresenter.preparePlaylistRemovedView(playlistName);
    }

    @Override
    public void switchToPlaylistView(String playlistName) {
        if (playlistName == null) {
            playlistCollectionPresenter.prepareFailView("Must select a playlist.");
        }
        else {
            // Hard coded playlist collection example
            // Actual code should search the DB using the playlist name and then
            // populate a playlist in PlaylistCollectionOutputData (convert JSON string values into Song objects)
            Playlist playlistTest = new Playlist("Playlist1");
            playlistTest.addSong(new Song("Starships", new String[]{"Nicki Minaj"}));

            PlaylistCollectionOutputData playlistCollectionOutputData = new PlaylistCollectionOutputData(playlistTest);
            playlistCollectionPresenter.switchToPlaylistView(playlistCollectionOutputData, playlistName);
        }
    }

    @Override
    public void switchToUserProfileView() {
        playlistCollectionPresenter.switchToUserProfileView();
    }

    @Override
    public void switchToAddPlaylistView() {
        playlistCollectionPresenter.switchToAddPlaylistView();
    }
}
