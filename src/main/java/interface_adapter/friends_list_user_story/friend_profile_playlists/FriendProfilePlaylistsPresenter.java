package interface_adapter.friends_list_user_story.friend_profile_playlists;

import interface_adapter.ViewManagerModel;
import use_case.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsOutputBoundary;

public class FriendProfilePlaylistsPresenter implements FriendProfilePlaylistsOutputBoundary {
    private final FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel;
    private final ViewManagerModel viewManagerModel;

    public FriendProfilePlaylistsPresenter(FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel, ViewManagerModel viewManagerModel) {
        this.friendProfilePlaylistsViewModel = friendProfilePlaylistsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void preparePlaylistAddedView(String playlistName) {
        friendProfilePlaylistsViewModel.getState().addPlaylist(playlistName);
        friendProfilePlaylistsViewModel.firePropertyChanged();
    }

    @Override
    public void preparePlaylistRemovedView(String playlistName) {
        friendProfilePlaylistsViewModel.getState().removePlaylist(playlistName);
        friendProfilePlaylistsViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        friendProfilePlaylistsViewModel.getState().setPlaylistError(error);
        friendProfilePlaylistsViewModel.firePropertyChanged();
    }
}
