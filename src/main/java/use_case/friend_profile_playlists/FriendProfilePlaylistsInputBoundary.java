package use_case.friend_profile_playlists;

public interface FriendProfilePlaylistsInputBoundary {
    /**
     * Executes the add playlist use case.
     * @param playlist output data
     */
    void addPlaylist(String playlist);

    /**
     * Executes the delete playlist use case.
     * @param playlist output data
     */
    void removePlaylist(String playlist);
}
