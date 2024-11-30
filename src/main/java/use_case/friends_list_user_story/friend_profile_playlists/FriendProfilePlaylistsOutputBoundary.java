package use_case.friends_list_user_story.friend_profile_playlists;

import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionOutputData;

public interface FriendProfilePlaylistsOutputBoundary {

    void preparePlaylistAddedView(String playlistName);

    void preparePlaylistRemovedView(String playlistName);

    void prepareFailView(String error);

    void switchToFriendProfileView(String selectedFriendName, String password);

    /**
     * Switches to Playlist View.
     *
     * @param playlistCollectionOutputData output data
     * @param playlistName
     */
    void switchToPlaylistView(PlaylistCollectionOutputData playlistCollectionOutputData, String playlistName);
}
