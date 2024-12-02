package use_case.friends_list_user_story.friend_playlist;

import data_access.DataAccessException;
import entity.Playlist;

/**
 * Interface defining the data access operations for a friend's playlist.
 * This interface is part of the data access layer and provides methods to retrieve a friend's playlist.
 */
public interface FriendPlaylistDataAccessInterface {

    /**
     * Retrieves a friend's playlist based on the provided username and playlist name.
     *
     * @param username The username of the friend whose playlist is to be retrieved.
     * @param playlistName The name of the playlist to be retrieved.
     * @return The playlist associated with the given username and playlist name.
     * @throws DataAccessException If an error occurs while accessing the data.
     */
    Playlist getFriendPlaylist(String username, String playlistName) throws DataAccessException;
}