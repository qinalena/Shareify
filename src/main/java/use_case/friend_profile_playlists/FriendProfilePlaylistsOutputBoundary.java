package use_case.friend_profile_playlists;

public interface FriendProfilePlaylistsOutputBoundary {

    void preparePlaylistAddedView(String playlistName);

    void preparePlaylistRemovedView(String playlistName);

    void prepareFailView(String error);
}
