package use_case.friends_list_user_story.friend_playlist;

import data_access.DataAccessException;
import entity.Playlist;

public interface FriendPlaylistDataAccessInterface {
    Playlist getFriendPlaylist(String username, String playlistName) throws DataAccessException;
}
