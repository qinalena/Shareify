package use_case.friends_list_user_story.friend_profile_playlists;

public class FriendProfilePlaylistsInteractor implements FriendProfilePlaylistsInputBoundary {
    private final FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter;

    public FriendProfilePlaylistsInteractor(FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter) {
        if (friendProfilePlaylistsPresenter == null) {
            throw new NullPointerException("Presenter cannot be null");
        }
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

    @Override
    public void switchToFriendProfileView(String selectedFriendName, String password) {
        friendProfilePlaylistsPresenter.switchToFriendProfileView(selectedFriendName, password);
    }
}
