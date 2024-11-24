package use_case.playlist_collection_user_story.playlist_collection;

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
     * @return the playlists created by the user
     * @throws DataAccessException if the user's playlist can not be loaded for any reason
     */
    String loadPlaylist(User user) throws DataAccessException;

    /**
     * Creates a playlist for given user.
     * @param user the user info associated with the playlist
     * @param playlistName name of playlist
     * @return the playlist
     * @throws DataAccessException if user's playlist can not be loaded for some reason
     */
    String addPlaylist (User user, String playlistName) throws DataAccessException;
}
