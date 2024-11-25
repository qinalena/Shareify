package use_case.playlist_collection_user_story.playlist_collection;

import java.util.List;

import entity.User;
import use_case.user_profile_user_story.note.DataAccessException;

/**
 * Interface for the PlaylistCollectionDAO. It consists of methods for
 * loading the playlists.
 */

public interface PlaylistCollectionDataAccessInterface {

    /**
     * Returns the playlist associated with the user. The password
     * is not checked, so anyone can read the information.
     * @param user the user information associated with the playlist
     * @param index index to determine where the selected playlist is
     * @throws DataAccessException if the user's playlist can not be loaded for any reason
     */
    void removePlaylist(User user, int index) throws DataAccessException;

    /**
     * Creates a playlist for given user.
     *
     * @param user the user info associated with the playlist
     * @param newPlaylist name of playlist
     * @throws DataAccessException if user's playlist can not be loaded for some reason
     */
    void addPlaylist(User user, String newPlaylist) throws DataAccessException;

    /**
     * Gets playlist from given user.
     * @param playlistName name of playlist
     * @return playlist
     * @throws DataAccessException if playlist cannot be found
     */
    List<String> getPlaylists(String playlistName) throws DataAccessException;
}