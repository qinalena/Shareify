package use_case.friends_list_user_story.friend_profile_playlists;

import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionOutputData;

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

    void switchToFriendProfileView(String selectedFriendName, String password);

    /**
     * Prepares failure view for playlist collection use cases.
     * @param error message indicating issue
     */
    void prepareFailView(String error);

    /**
     * Switches to Playlist View.
     *
     * @param playlistName
     */
    void switchToPlaylistView(String playlistName);
}
