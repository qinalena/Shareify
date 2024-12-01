package use_case.playlist_collection_user_story.playlist_collection;

import java.util.List;

import entity.User;
import org.json.JSONArray;
import use_case.DataAccessException;

/**
 * Interface for the PlaylistCollectionDAO. It consists of methods for
 * loading the playlists.
 */

public interface PlaylistCollectionDataAccessInterface {
    /**
     * Creates a playlist for given user.
     *
     * @param user        the user info associated with the playlist
     * @param newPlaylist name of playlist
     * @throws DataAccessException if user's playlist can not be loaded for some reason
     */
    void addPlaylistinDB(User user, String newPlaylist) throws DataAccessException;

    /**
     * Returns the playlist associated with the user. The password
     * is not checked, so anyone can read the information.
     * @param user the user information associated with the playlist
     * @param playlistName keys - playlist names
     * @throws DataAccessException if the user's playlist can not be loaded for any reason
     */
    void removePlaylistinDB(User user, String playlistName) throws DataAccessException;

    /**
     * Gets playlist from given user.
     * @param username user info associated with playlist
     * @return playlist
     * @throws DataAccessException if playlist cannot be found
     */
    List<String> getPlaylists(String username) throws DataAccessException;
}
