package data_access;

import entity.Playlist;
import entity.Song;
import entity.User;
import use_case.DataAccessException;
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory DAO for writing tests.
 */
public class InMemoryDataAccessObject implements PlaylistCollectionDataAccessInterface {
    private final List<Playlist> playlistCollection = new ArrayList<Playlist>();

    @Override
    public List<Playlist> getPlaylistCollection() throws DataAccessException {
        return playlistCollection;
    }

    @Override
    public void addPlaylistToUser(String playlistName) throws DataAccessException {

    }

    @Override
    public void addSongToPlaylist(Playlist currentPlaylist, Song song) throws DataAccessException {
        for (Playlist playlist : playlistCollection) {
            if (playlist == currentPlaylist) {
                playlist.addSong(song);
            }
        }
    }

}
