package use_case.playlist_user_story.playlist;

import entity.Playlist;
import data_access.DataAccessException;

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
    public void removeSong(Playlist playlist, int songIndex) {
        // TODO: Remove song from playlist in the DB
        try {
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
    public void switchToSearchSongView(Playlist currentPlaylist) {
        playlistPresenter.switchToSearchSongView(currentPlaylist);

    }
}
