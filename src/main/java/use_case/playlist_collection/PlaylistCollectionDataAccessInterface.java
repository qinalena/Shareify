package use_case.playlist_collection;

import entity.User;
import use_case.note.DataAccessException;

/**
 * Interface for the PlaylistCollectionDAO. It consists of methods for
 * loading the playlists.
 */

public interface PlaylistCollectionDataAccessInterface {

    /**
     * Returns the note associated with the user. The password
     * is not checked, so anyone can read the information.
     * @param user the user information associated with the note
     * @return the contents of the note
     * @throws DataAccessException if the user's note can not be loaded for any reason
     */
    String loadPlaylistCollection(User user) throws DataAccessException;
}
