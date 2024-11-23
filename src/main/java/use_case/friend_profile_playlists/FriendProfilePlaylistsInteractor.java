package use_case.friend_profile_playlists;

public class FriendProfilePlaylistsInteractor implements FriendProfilePlaylistsInputBoundary {
    private final FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter;

    public FriendProfilePlaylistsInteractor(FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter) {
        this.friendProfilePlaylistsPresenter = friendProfilePlaylistsPresenter;
    }

    @Override
    public void addPlaylist(String playlist) {
        if (playlist == null || playlist.isEmpty()) {
            friendProfilePlaylistsPresenter.prepareFailView("Error");
            return;
        }
        friendProfilePlaylistsPresenter.preparePlaylistAddedView(playlist);
    }

    @Override
    public void removePlaylist(String playlist) {
        friendProfilePlaylistsPresenter.preparePlaylistRemovedView(playlist);
    }
}
