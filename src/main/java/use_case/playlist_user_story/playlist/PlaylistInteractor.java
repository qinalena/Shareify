package use_case.playlist_user_story.playlist;

import entity.Playlist;
import data_access.DataAccessException;
import use_case.playlist_user_story.PlaylistDataAccessInterface;

/**
 * The interactor for a playlist.
 */
public class PlaylistInteractor implements PlaylistInputBoundary {

    private final PlaylistDataAccessInterface playlistDataAccessObject;
    private final PlaylistOutputBoundary playlistPresenter;

    public PlaylistInteractor(PlaylistDataAccessInterface playlistDataAccessObject,
                              PlaylistOutputBoundary playlistPresenter) {
        this.playlistDataAccessObject = playlistDataAccessObject;
        this.playlistPresenter = playlistPresenter;
    }

    @Override
    public void removeSong(String currentPlaylistName, int songIndex) {
        try {
            final Playlist playlist = new Playlist(currentPlaylistName);

            playlistDataAccessObject.removeSongFromPlaylist(playlist, songIndex);
            playlistPresenter.removeSong(songIndex);
        }
        catch (DataAccessException exception) {
            playlistPresenter.prepareFailView(exception.getMessage());
        }
    }

    @Override
    public void switchToPlaylistCollectionView() {
        playlistPresenter.switchToPlaylistCollectionView();

    }

    @Override
    public void switchToSearchSongView(String currentPlaylistName) {
        playlistPresenter.switchToSearchSongView(currentPlaylistName);

    }
}
