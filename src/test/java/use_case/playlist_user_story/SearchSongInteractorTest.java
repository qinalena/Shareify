package use_case.playlist_user_story;

import data_access.InMemoryDataAccessObject;
import entity.Playlist;
import entity.Song;
import org.junit.Test;
import spotify_api.SpotifyConnection;
import spotify_api.SpotifyConnectionInterface;
import use_case.playlist_user_story.search_song.*;

import static org.junit.Assert.*;

//public class SearchSongInteractorTest {
//    SpotifyConnectionInterface spotifyDAO = new SpotifyConnection();
//
//    @Test
//    public void testAddSongSuccess() {
//
//        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();
//
//        playlistDAO.addPlaylistToUser("Playlist1");
//
//        SearchSongOutputBoundary searchSongPresenter = new SearchSongOutputBoundary() {
//
//            @Override
//            public void searchSong(SearchSongOutputData searchSongOutputData) {
//
//            }
//
//            @Override
//            public void switchToPlaylistView() {
//
//            }
//
//            @Override
//            public void addSong(Song selectedSong) {
//                // Check if DAO is updated
//                Playlist playlist = playlistDAO.getPlaylistCollection().get(0);
//                Song song = playlist.getSongs().get(0);
//                assertEquals("Starships", song.getName());
//
//                // Check if interactor output data is correct
//                assertEquals("Starships", selectedSong.getName());
//            }
//
//            @Override
//            public void prepareFailView(String message) {
//
//            }
//        };
//
//        SearchSongInputBoundary searchSongInteractor = new SearchSongInteractor(spotifyDAO, playlistDAO, searchSongPresenter);
//
//        Playlist playlist = new Playlist("Playlist1");
//        SearchSongInputData searchSongInputData = new SearchSongInputData(playlist, "Starships - Nicki Minaj");
//
//        searchSongInteractor.addSong(searchSongInputData);
//
//    }
//
//    @Test
//    public void testAddSongFail() {
//
//        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();
//
//        SearchSongOutputBoundary searchSongPresenter = new SearchSongOutputBoundary() {
//
//            @Override
//            public void searchSong(SearchSongOutputData searchSongOutputData) {
//
//            }
//
//            @Override
//            public void switchToPlaylistView() {
//
//            }
//
//            @Override
//            public void addSong(Song selectedSong) {
//
//            }
//
//            @Override
//            public void prepareFailView(String message) {
//            assertEquals("Playlist not found in the collection.", message);
//            }
//        };
//
//        SearchSongInputBoundary searchSongInteractor = new SearchSongInteractor(spotifyDAO, playlistDAO, searchSongPresenter);
//
//        Playlist playlist = new Playlist("Playlist1");
//        SearchSongInputData searchSongInputData = new SearchSongInputData(playlist, "Starships - Nicki Minaj");
//
//        searchSongInteractor.addSong(searchSongInputData);
//    }
//
//    @Test
//    public void testSwitchToPlaylistView() {
//
//        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();
//
//        playlistDAO.addPlaylistToUser("Playlist1");
//
//        SearchSongOutputBoundary searchSongPresenter = new SearchSongOutputBoundary() {
//
//            @Override
//            public void searchSong(SearchSongOutputData searchSongOutputData) {
//
//            }
//
//            @Override
//            public void switchToPlaylistView() {
//            // This is expected. Don't need to assert anything.
//            }
//
//            @Override
//            public void addSong(Song selectedSong) {
//
//            }
//
//            @Override
//            public void prepareFailView(String message) {
//
//            }
//        };
//
//        SearchSongInputBoundary searchSongInteractor = new SearchSongInteractor(spotifyDAO, playlistDAO, searchSongPresenter);
//
//        searchSongInteractor.switchToPlaylistView();
//
//    }
//
//    @Test
//    public void testSearchSong() {
//
//        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();
//
//        playlistDAO.addPlaylistToUser("Playlist1");
//
//        SearchSongOutputBoundary searchSongPresenter = new SearchSongOutputBoundary() {
//
//            @Override
//            public void searchSong(SearchSongOutputData searchSongOutputData) {
//                // Search results is a list of 10 songs
//                assertEquals(10, searchSongOutputData.getSearchResults().size());
//            }
//
//            @Override
//            public void switchToPlaylistView() {
//                // This is expected. Don't need to assert anything.
//            }
//
//            @Override
//            public void addSong(Song selectedSong) {
//
//            }
//
//            @Override
//            public void prepareFailView(String message) {
//
//            }
//        };
//
//        SearchSongInputBoundary searchSongInteractor = new SearchSongInteractor(spotifyDAO, playlistDAO, searchSongPresenter);
//
//        searchSongInteractor.searchSong("Starships");
//
//    }
//}