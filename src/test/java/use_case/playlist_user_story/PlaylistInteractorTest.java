package use_case.playlist_user_story;

import data_access.InMemoryDataAccessObject;
import entity.Playlist;
import entity.Song;
import org.junit.Test;
import use_case.playlist_user_story.playlist.PlaylistInputBoundary;
import use_case.playlist_user_story.playlist.PlaylistInteractor;
import use_case.playlist_user_story.playlist.PlaylistOutputBoundary;

import static org.junit.Assert.assertEquals;

public class PlaylistInteractorTest {

    @Test
    public void removeSongSuccess() {

        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();
        String[] artists = new String[] {"Nicki Minaj"};
        Song song = new Song("Starships", artists);
        Playlist playlist = new Playlist("Playlist1");
        playlist.addSong(song);
        playlistDAO.addPlaylist(playlist);

        PlaylistOutputBoundary playlistPresenter = new PlaylistOutputBoundary() {
            @Override
            public void removeSong(int songIndex) {
                Playlist playlist =  playlistDAO.getPlaylist("Playlist1");
                assert playlist.getSongs().size() == 0;
                assert songIndex == 0;
            }

            @Override
            public void switchToPlaylistCollectionView() {

            }

            @Override
            public void switchToSearchSongView(String currentPlaylistName) {

            }

            @Override
            public void prepareFailView(String message) {

            }
        };

        PlaylistInputBoundary playlistInteractor = new PlaylistInteractor(playlistDAO, playlistPresenter);

        playlistInteractor.removeSong("Playlist1", 0);

    }

    @Test
    public void removeSongFail() {

        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();

        PlaylistOutputBoundary playlistPresenter = new PlaylistOutputBoundary() {
            @Override
            public void removeSong(int songIndex) {

            }

            @Override
            public void switchToPlaylistCollectionView() {

            }

            @Override
            public void switchToSearchSongView(String currentPlaylistName) {

            }

            @Override
            public void prepareFailView(String message) {
            assertEquals("Playlist not found in the collection.", message);
            }
        };

        PlaylistInputBoundary playlistInteractor = new PlaylistInteractor(playlistDAO, playlistPresenter);

        playlistInteractor.removeSong("Playlist1", 0);

    }

    @Test
    public void switchToPlaylistCollectionView() {

        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();
        Playlist playlist = new Playlist("Playlist1");
        playlistDAO.addPlaylist(playlist);

        PlaylistOutputBoundary playlistPresenter = new PlaylistOutputBoundary() {
            @Override
            public void removeSong(int songIndex) {

            }

            @Override
            public void switchToPlaylistCollectionView() {
                // This is expected. Don't need to assert anything.
            }

            @Override
            public void switchToSearchSongView(String currentPlaylistName) {

            }

            @Override
            public void prepareFailView(String message) {

            }
        };

        PlaylistInputBoundary playlistInteractor = new PlaylistInteractor(playlistDAO, playlistPresenter);

        playlistInteractor.switchToPlaylistCollectionView();

    }

    @Test
    public void switchToSearchSongView() {

        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();
        Playlist playlist = new Playlist("Playlist1");
        playlistDAO.addPlaylist(playlist);

        PlaylistOutputBoundary playlistPresenter = new PlaylistOutputBoundary() {
            @Override
            public void removeSong(int songIndex) {

            }

            @Override
            public void switchToPlaylistCollectionView() {

            }

            @Override
            public void switchToSearchSongView(String currentPlaylistName) {
                assertEquals("Playlist1", currentPlaylistName);
            }

            @Override
            public void prepareFailView(String message) {

            }
        };

        PlaylistInputBoundary playlistInteractor = new PlaylistInteractor(playlistDAO, playlistPresenter);

        playlistInteractor.switchToSearchSongView("Playlist1");

    }
}
