package use_case.playlist_user_story.search_song;

import data_access.DataAccessException;
import entity.Song;
import use_case.playlist_user_story.PlaylistDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * The Interactor for Search Song.
 */
public class SearchSongInteractor implements SearchSongInputBoundary {

    private final SpotifyDataAccessInterface spotifyDAO;
    private final PlaylistDataAccessInterface playlistDAO;
    private final SearchSongOutputBoundary searchSongPresenter;

    public SearchSongInteractor(SpotifyDataAccessInterface spotifyDAO,
                                PlaylistDataAccessInterface playlistDAO, SearchSongOutputBoundary searchSongPresenter) {
        this.spotifyDAO = spotifyDAO;
        this.playlistDAO = playlistDAO;
        this.searchSongPresenter = searchSongPresenter;
    }

    @Override
    public void searchSong(String query) {
        if (query != null) {
            final List<Song> searchResults = spotifyDAO.searchTrack(query);
            final List<String> displaySearchResults = new ArrayList<>();
            for (final Song searchResult : searchResults) {
                displaySearchResults.add(searchResult.toString());
            }
            searchSongPresenter.searchSong(displaySearchResults);
        }
    }

    @Override
    public void switchToPlaylistView() {
        searchSongPresenter.switchToPlaylistView();
    }

    @Override
    public void addSong(SearchSongInputData searchSongInputData) {
        try {
            final Song selectedSong = new Song(searchSongInputData.getSongName(), searchSongInputData.getArtists());

            playlistDAO.addSongToPlaylist(searchSongInputData.getCurrentPlaylistName(), selectedSong);

            searchSongPresenter.addSong(selectedSong.toString());
        }
        catch (DataAccessException exception) {
            searchSongPresenter.prepareFailView(exception.getMessage());
        }
    }
}
