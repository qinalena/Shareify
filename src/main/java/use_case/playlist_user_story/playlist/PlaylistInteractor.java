package use_case.playlist_user_story.playlist;

import entity.Playlist;

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
    public void removeSong(int songIndex) {
        // TODO: Remove song from playlist in the DB

        playlistPresenter.removeSong(songIndex);
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
