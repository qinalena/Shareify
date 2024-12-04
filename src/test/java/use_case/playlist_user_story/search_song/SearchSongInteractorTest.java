package use_case.playlist_user_story.search_song;

import data_access.spotify_api.SpotifyClientCredAuth;
import data_access.spotify_api.SpotifyDataAccessObject;
import data_access.InMemoryDataAccessObject;
import entity.Playlist;
import entity.Song;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class SearchSongInteractorTest {
    SpotifyAuthorizationInterface spotifyAuth = new SpotifyClientCredAuth();
    SpotifyDataAccessInterface spotifyDAO = new SpotifyDataAccessObject(spotifyAuth);

    @Test
    public void testAddSongSuccess() {

        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();

        playlistDAO.addPlaylist(new Playlist("Playlist1"));

        SearchSongOutputBoundary searchSongPresenter = new SearchSongOutputBoundary() {

            @Override
            public void searchSong(List<String> displaySearchResults) {

            }

            @Override
            public void switchToPlaylistView() {

            }

            @Override
            public void addSong(String selectedSong) {
                // Check if DAO is updated
                Playlist playlist = playlistDAO.getPlaylist("Playlist1");
                Song song = playlist.getSongs().get(0);
                assertEquals("Starships", song.getName());
                assertEquals("Starships - Nicki Minaj", selectedSong);
            }

            @Override
            public void prepareFailView(String message) {

            }
        };

        SearchSongInputBoundary searchSongInteractor = new SearchSongInteractor(spotifyDAO, playlistDAO, searchSongPresenter);

        SearchSongInputData searchSongInputData = new SearchSongInputData("Playlist1", "Starships - Nicki Minaj");

        searchSongInteractor.addSong(searchSongInputData);

    }

    @Test
    public void testAddSongFail() {

        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();

        SearchSongOutputBoundary searchSongPresenter = new SearchSongOutputBoundary() {

            @Override
            public void searchSong(List<String> displaySearchResults) {

            }

            @Override
            public void switchToPlaylistView() {

            }

            @Override
            public void addSong(String selectedSong) {

            }

            @Override
            public void prepareFailView(String message) {
            assertEquals("Playlist not found in the collection.", message);
            }
        };

        SearchSongInputBoundary searchSongInteractor = new SearchSongInteractor(spotifyDAO, playlistDAO, searchSongPresenter);

        SearchSongInputData searchSongInputData = new SearchSongInputData("Playlist1", "Starships - Nicki Minaj");

        searchSongInteractor.addSong(searchSongInputData);
    }

    @Test
    public void testSwitchToPlaylistView() {

        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();

        playlistDAO.addPlaylist(new Playlist("Playlist1"));

        SearchSongOutputBoundary searchSongPresenter = new SearchSongOutputBoundary() {

            @Override
            public void searchSong(List<String> displaySearchResults) {

            }

            @Override
            public void switchToPlaylistView() {
            // This is expected. Don't need to assert anything.
            }

            @Override
            public void addSong(String selectedSong) {

            }

            @Override
            public void prepareFailView(String message) {

            }
        };

        SearchSongInputBoundary searchSongInteractor = new SearchSongInteractor(spotifyDAO, playlistDAO, searchSongPresenter);

        searchSongInteractor.switchToPlaylistView();

    }

    @Test
    public void testSearchSong() {

        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();

        playlistDAO.addPlaylist(new Playlist("Playlist1"));

        SearchSongOutputBoundary searchSongPresenter = new SearchSongOutputBoundary() {

            @Override
            public void searchSong(List<String> displaySearchResults) {
                // Search results is a list of 10 songs
                assertEquals(10, displaySearchResults.size());
            }

            @Override
            public void switchToPlaylistView() {
                // This is expected. Don't need to assert anything.
            }

            @Override
            public void addSong(String selectedSong) {

            }


            @Override
            public void prepareFailView(String message) {

            }
        };

        SearchSongInputBoundary searchSongInteractor = new SearchSongInteractor(spotifyDAO, playlistDAO, searchSongPresenter);

        searchSongInteractor.searchSong("Starships");

    }
}