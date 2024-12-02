package data_access;

import entity.Playlist;
import entity.Song;
import use_case.playlist_user_story.PlaylistDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory DAO for writing tests.
 */
public class InMemoryDataAccessObject implements PlaylistDataAccessInterface {
    private final List<Playlist> playlistCollection = new ArrayList<>();

    public List<Playlist> getPlaylistCollection() {
        return playlistCollection;
    }

    @Override
    public Playlist getPlaylist(String playlistName) {

        for (Playlist playlist : playlistCollection) {
            if (playlist.getName().equals(playlistName)) {
                return playlist;
            }
        }
        return null;
    }

    @Override
    public void addSongToPlaylist(String currentPlaylistName, Song song) throws DataAccessException {
        boolean playlistFound = false;

        for (Playlist playlist : playlistCollection) {
            if (playlist.getName() == currentPlaylistName) {
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
    public void removeSongFromPlaylist(String currentPlaylistName, int songIndex) throws DataAccessException {
        boolean playlistFound = false;

        for (Playlist playlist : playlistCollection) {
            if (playlist.getName() == currentPlaylistName) {
                playlist.removeSong(songIndex);
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

    /**
     * Add a Playlist to the playlistCollection for testing purposes.
     * @param playlist the playlist to be added
     */
    public void addPlaylist(Playlist playlist) {
        playlistCollection.add(playlist);
    }

}
