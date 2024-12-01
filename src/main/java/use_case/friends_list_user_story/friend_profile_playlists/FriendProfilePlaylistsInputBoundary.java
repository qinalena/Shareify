package use_case.friends_list_user_story.friend_profile_playlists;

import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionOutputData;

public interface FriendProfilePlaylistsInputBoundary {

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
    void switchToPlaylistView(String playlistName, String username, String password);
}
