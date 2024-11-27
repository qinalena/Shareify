package use_case.playlist_user_story;

import data_access.InMemoryDataAccessObject;
import entity.Playlist;
import entity.Song;
import org.junit.Test;
import spotify_api.SpotifyConnection;
import spotify_api.SpotifyConnectionInterface;
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionDataAccessInterface;
import use_case.playlist_user_story.search_song.*;

import java.util.List;

import static org.junit.Assert.*;

public class SearchSongInteractorTest {

    @Test
    public void testAddSongSuccess() {

        InMemoryDataAccessObject playlistDAO = new InMemoryDataAccessObject();
        SpotifyConnectionInterface spotifyDAO = new SpotifyConnection();

        playlistDAO.addPlaylistToUser("Playlist1");

        SearchSongOutputBoundary searchSongPresenter = new SearchSongOutputBoundary() {

            @Override
            public void searchSong(SearchSongOutputData searchSongOutputData) {

            }

            @Override
            public void switchToPlaylistView() {

            }

            @Override
            public void addSong(Song selectedSong) {
                // Check if DAO is updated
                Playlist playlist = playlistDAO.getPlaylistCollection().get(0);
                Song song = playlist.getSongs().get(0);
                assertEquals("Starships", song.getName());

                // Check if interactor output data is correct
                assertEquals("Starships", selectedSong.getName());
            }
        };

        SearchSongInputBoundary searchSongInteractor = new SearchSongInteractor(spotifyDAO, playlistDAO, searchSongPresenter);

        Playlist playlist = new Playlist("Playlist1");
        SearchSongInputData searchSongInputData = new SearchSongInputData(playlist, "Starships - Nicki Minaj");

        searchSongInteractor.addSong(searchSongInputData);

    }
}