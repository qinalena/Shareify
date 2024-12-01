package use_case.playlist_user_story.search_song;

import data_access.DataAccessException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import use_case.playlist_user_story.PlaylistDataAccessInterface;

/**
 * The Interactor for Search Song.
 */
public class SearchSongInteractor implements SearchSongInputBoundary {

    private final SpotifyConnectionInterface spotifyDAO;
    private final PlaylistDataAccessInterface playlistDAO;
    private final SearchSongOutputBoundary searchSongPresenter;

    public SearchSongInteractor(SpotifyConnectionInterface spotifyDAO,
                                PlaylistDataAccessInterface playlistDAO, SearchSongOutputBoundary searchSongPresenter) {
        this.spotifyDAO = spotifyDAO;
        this.playlistDAO = playlistDAO;
        this.searchSongPresenter = searchSongPresenter;
    }

    @Override
    public void searchSong(String query) {
        if (query != null) {
            final Track[] tracks = spotifyDAO.searchTrack(query);
            final SearchSongOutputData searchSongOutputData = new SearchSongOutputData(tracks);
            searchSongPresenter.searchSong(searchSongOutputData);
        }

    }

    @Override
    public void switchToPlaylistView() {
        searchSongPresenter.switchToPlaylistView();
    }

    @Override
    public void addSong(SearchSongInputData searchSongInputData) {
        try {
            playlistDAO.addSongToPlaylist(searchSongInputData.getCurrentPlaylist(),
                    searchSongInputData.getSelectedSong());

            searchSongPresenter.addSong(searchSongInputData.getSelectedSong());
        }
        catch (DataAccessException exception) {
            searchSongPresenter.prepareFailView(exception.getMessage());
        }
    }
}
