package use_case.playlist_collection_user_story.playlist_collection;

import entity.Playlist;
import entity.User;
import data_access.DataAccessException;
import use_case.playlist_user_story.PlaylistDataAccessInterface;

/**
 * The "Use Case Interactor" for our playlist collection related use cases of creating
 * a playlist.
 */

public class PlaylistCollectionInteractor implements PlaylistCollectionInputBoundary {
    private final PlaylistCollectionDataAccessInterface dbPlaylistDataAccessObject;
    private final PlaylistDataAccessInterface playlistDataAccessObject;
    private final PlaylistCollectionOutputBoundary playlistCollectionPresenter;

    public PlaylistCollectionInteractor(PlaylistCollectionDataAccessInterface playlistCollectionDataAccessObject,
                                        PlaylistDataAccessInterface playlistDataAccessObject, PlaylistCollectionOutputBoundary playlistCollectionPresenter) {
        this.dbPlaylistDataAccessObject = playlistCollectionDataAccessObject;
        this.playlistDataAccessObject = playlistDataAccessObject;
        this.playlistCollectionPresenter = playlistCollectionPresenter;
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
            // Actual code getting Playlist Collection from the DB (PlaylistCollectionOutputData might be redundant; pass playlist directly to presenter)
            try {
                final Playlist playlist = playlistDataAccessObject.getPlaylist(playlistName);
                final PlaylistCollectionOutputData playlistCollectionOutputData = new PlaylistCollectionOutputData(playlist);
                playlistCollectionPresenter.switchToPlaylistView(playlistCollectionOutputData);
            }
            catch (DataAccessException exception) {
                playlistCollectionPresenter.prepareFailView(exception.getMessage());
            }

            // Hard coded playlist collection example
//            Playlist playlistTest = new Playlist(playlistName);
//            playlistTest.addSong(new Song("Starships", new String[]{"Nicki Minaj"}));
//
//            PlaylistCollectionOutputData playlistCollectionOutputData = new PlaylistCollectionOutputData(playlistTest);
//            playlistCollectionPresenter.switchToPlaylistView(playlistCollectionOutputData, playlistName);
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
