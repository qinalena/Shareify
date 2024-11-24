package use_case.playlist_collection_user_story.playlist_collection;

import entity.User;
import use_case.user_profile_user_story.note.DataAccessException;

/**
 * Interface for the PlaylistCollectionDAO. It consists of methods for
 * loading the playlists.
 */

public interface PlaylistCollectionDataAccessInterface {

    /**
     * Returns the playlist collection associated with the user. The password
     * is not checked, so anyone can read the information.
     * @param user the user
     * @return the contents of the playlist collection
     * @throws DataAccessException if the user's playlist collection can not be loaded for any reason
     */
    String loadPlaylistCollection(User user) throws DataAccessException;
}
