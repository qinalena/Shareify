package use_case.playlist_collection_user_story.playlist_collection;

import data_access.DataAccessException;
import entity.Playlist;
import entity.User;
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
                                        PlaylistDataAccessInterface playlistDataAccessObject,
                                        PlaylistCollectionOutputBoundary playlistCollectionPresenter) {
        this.dbPlaylistDataAccessObject = playlistCollectionDataAccessObject;
        this.playlistDataAccessObject = playlistDataAccessObject;
        this.playlistCollectionPresenter = playlistCollectionPresenter;
    }

    @Override
    public void addPlaylist(User user, String playlistName) {
        if (playlistName == null || playlistName.isEmpty()) {
            playlistCollectionPresenter.prepareFailView("Playlist name cannot be empty.");
        }
        else {
            try {
                // Call DAO to add playlist to database
                // individual playlist
                dbPlaylistDataAccessObject.addPlaylistinDB(user, playlistName);

                playlistCollectionPresenter.preparePlaylistAddedView(playlistName);
            }
            catch (DataAccessException exception) {
                playlistCollectionPresenter.prepareFailView("Failed to add playlist: " + exception.getMessage());
            }
        }

    }

    @Override
    public void removePlaylist(User user, String playlistName) {
        if (playlistName == null || playlistName.isEmpty()) {
            playlistCollectionPresenter.prepareFailView("Playlist name cannot be empty.");
        }
        else {
            try {
                // Call to DAO to remove playlist from the database
                dbPlaylistDataAccessObject.removePlaylistinDB(user, playlistName);
            }
            catch (DataAccessException exception) {
                playlistCollectionPresenter.prepareFailView("Failed to remove playlist: " + exception.getMessage());
            }
            playlistCollectionPresenter.preparePlaylistRemovedView(playlistName);
        }
    }

    @Override
    public void switchToPlaylistView(String playlistName) {
        if (playlistName == null) {
            playlistCollectionPresenter.prepareFailView("Must select a playlist.");
        }
        else {
            try {
                final Playlist playlist = playlistDataAccessObject.getPlaylist(playlistName);

                playlistCollectionPresenter.switchToPlaylistView(playlist.getName(), playlist.songsToStrings());
            }
            catch (DataAccessException exception) {
                playlistCollectionPresenter.prepareFailView(exception.getMessage());
            }
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
