package use_case.playlist_user_story.search_song;

import entity.Song;
import entity.User;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import spotify_api.SpotifyConnectionInterface;
import data_access.LoggedInDataAccessInterface;

/**
 * The Interactor for SearchTrack.
 */
public class SearchSongInteractor implements SearchSongInputBoundary {

    private final SpotifyConnectionInterface spotifyDAO;
    private final SearchSongDataAccessInterface searchSongDAO;
    private final LoggedInDataAccessInterface loggedInDAO;
    private final SearchSongOutputBoundary searchSongPresenter;

    public SearchSongInteractor(SpotifyConnectionInterface spotifyDAO, SearchSongDataAccessInterface searchSongDAO, LoggedInDataAccessInterface loggedInDAO, SearchSongOutputBoundary searchSongPresenter) {
        this.spotifyDAO = spotifyDAO;
        this.searchSongDAO = searchSongDAO;
        this.loggedInDAO = loggedInDAO;
        this.searchSongPresenter = searchSongPresenter;
    }

    @Override
    public void searchSong(String query) {
        if (query != null) {
            SearchSongOutputData searchSongOutputData = new SearchSongOutputData();
            final Track[] searchResults = spotifyDAO.searchTrack(query);

            for (Track searchResult : searchResults) {
                final ArtistSimplified[] artists = searchResult.getArtists();
                String[] artistNames = new String[artists.length];

                for (int i = 0; i < artists.length; i++) {
                    artistNames[i] = artists[i].getName();
                }

                final Song song = new Song(searchResult.getName(), artistNames);
                searchSongOutputData.addSong(song);
            }

            searchSongPresenter.searchSong(searchSongOutputData);
        }

    }

    @Override
    public void switchToPlaylistView() {
        searchSongPresenter.switchToPlaylistView();
    }

    @Override
    public void addSong(SearchSongInputData selectedSong) {
        User currentUser = loggedInDAO.getLoggedInUser();
        searchSongPresenter.addSong(selectedSong);

    }
}
