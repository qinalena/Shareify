package use_case.friends_list_user_story.friend_profile_playlists;

import entity.Playlist;

public interface FriendProfilePlaylistsOutputBoundary {

    void preparePlaylistAddedView(String playlistName);

    void preparePlaylistRemovedView(String playlistName);

    void prepareFailView(String error);

    void switchToFriendProfileView(String selectedFriendName, String password);

    /**
     * Switches to Playlist View.
     *
     * @param playlist output data
     */
    void switchToPlaylistView(Playlist playlist, String username, String password);
}
