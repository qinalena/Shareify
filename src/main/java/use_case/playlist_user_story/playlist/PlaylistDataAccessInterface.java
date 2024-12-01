package use_case.playlist_user_story.playlist;

import entity.Playlist;
import entity.Song;
import use_case.DataAccessException;

/**
 * Data Access Interface for Playlist.
 */
public interface PlaylistDataAccessInterface {

    /**
     * Returns a playlist from a user's Playlist Collection.
     * @param playlistName the name of the playlist to be returned
     * @return a Playlist object, containing a list of Songs
     * @throws DataAccessException if a user's playlist cannot be returned for any reason
     */
    Playlist getPlaylist(String playlistName) throws DataAccessException;

    /**
     * Adds a song to a playlist in the user's Playlist Collection.
     * @param playlist the playlist to add to
     * @param song the song to be added
     * @throws DataAccessException if user's playlist in Playlist Collection cannot be updated for any reason
     */
    void addSongToPlaylist(Playlist playlist, Song song) throws DataAccessException;

    /**
     * Removes song from the specified playlist in the user's Playlist Collection.
     * @param playlist  the playlist to remove from
     * @param songIndex the index of the song to be removed
     * @throws DataAccessException if user's playlist in Playlist Collection cannot be updated for any reason
     */
    void removeSongFromPlaylist(Playlist playlist, int songIndex) throws DataAccessException;
}
