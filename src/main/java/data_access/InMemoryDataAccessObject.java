package data_access;

import entity.Playlist;
import entity.Song;
import use_case.DataAccessException;
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionDataAccessInterface;

import java.util.ArrayList;
import java.util.List;

/**
 * In-memory DAO for writing tests.
 */
public class InMemoryDataAccessObject implements PlaylistCollectionDataAccessInterface {
    private final List<Playlist> playlistCollection = new ArrayList<>();

    @Override
    public List<Playlist> getPlaylistCollection() {
        return playlistCollection;
    }

    @Override
    public void addPlaylistToUser(String playlistName) {
        playlistCollection.add(new Playlist(playlistName));
    }

    @Override
    public void addSongToPlaylist(Playlist currentPlaylist, Song song) throws DataAccessException {
        boolean playlistFound = false;

        for (Playlist playlist : playlistCollection) {
            if (playlist.getName() == currentPlaylist.getName()) {
                playlist.addSong(song);
                playlistFound = true;
                break;  // Exit the loop once the playlist is found and updated
            }
        }

        // Throw exception if the playlist was not found
        if (!playlistFound) {
            throw new DataAccessException("Playlist not found in the collection.");
        }
    }

}
