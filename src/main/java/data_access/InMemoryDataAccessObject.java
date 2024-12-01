package data_access;

import entity.Playlist;
import entity.Song;
import entity.User;
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionDataAccessInterface;
import use_case.playlist_user_story.PlaylistDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory DAO for writing tests.
 */
public class InMemoryDataAccessObject implements PlaylistDataAccessInterface, PlaylistCollectionDataAccessInterface {
    private final List<Playlist> playlistCollection = new ArrayList<>();

    @Override
    public Playlist getPlaylist(String playlistName) throws DataAccessException {
        return null;
    }

    @Override
    public void addSongToPlaylist(Playlist currentPlaylist, Song song) throws DataAccessException {
        boolean playlistFound = false;

        for (Playlist playlist : playlistCollection) {
            if (playlist.getName() == currentPlaylist.getName()) {
                playlist.addSong(song);
                playlistFound = true;
                break;
                // Exit the loop once the playlist is found and updated
            }
        }

        // Throw exception if the playlist was not found
        if (!playlistFound) {
            throw new DataAccessException("Playlist not found in the collection.");
        }
    }

    @Override
    public void removeSongFromPlaylist(Playlist playlist, int songIndex) throws DataAccessException {

    }

    @Override
    public void addPlaylistinDB(User user, String newPlaylist) throws DataAccessException {
    }

    @Override
    public void removePlaylistinDB(User user, String playlistName) throws DataAccessException {

    }

    @Override
    public List<String> getPlaylists(String username) throws DataAccessException {
        return List.of();
    }
}
