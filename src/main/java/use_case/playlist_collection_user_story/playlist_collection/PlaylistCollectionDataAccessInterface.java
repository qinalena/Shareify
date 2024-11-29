package use_case.playlist_collection_user_story.playlist_collection;

import entity.Playlist;
import entity.Song;
import use_case.DataAccessException;

import java.util.List;

/**
 * Interface for the PlaylistCollectionDAO. It consists of methods for
 * loading the playlists.
 */
public interface PlaylistCollectionDataAccessInterface {

    /**
     * Returns the Playlist Collection of a user.
     * @return list of Playlists; empty list if a user has no playlists
     * @throws DataAccessException if cannot get user's playlist collection for any reason
     */
    List<Playlist> getPlaylistCollection() throws DataAccessException;

    /**
     * Add newly created playlist to user's Playlist Collection.
     * @param playlistName the name of the newly created playlist
     * @throws DataAccessException if cannot get user's Playlist Collection for any reason
     */
    void addPlaylistToUser(String playlistName) throws DataAccessException;

    /**
     * Adds a song to a playlist in the user's Playlist Collection.
     * @param playlist the playlist to add to
     * @param song the song to be added
     * @throws DataAccessException if user's playlist in Playlist Collection cannot be updated for any reason
     */
    void addSongToPlaylist(Playlist playlist, Song song) throws DataAccessException;

    /**
     * Removes song from the specified playlist in the user's Playlist Collection.
     * @param playlist the playlist to remove from
     * @param song the song to be removed
     * @throws DataAccessException if user's playlist in Playlist Collection cannot be updated for any reason
     */
    void removeSongFromPlaylist(Playlist playlist, Song song) throws DataAccessException;
}
