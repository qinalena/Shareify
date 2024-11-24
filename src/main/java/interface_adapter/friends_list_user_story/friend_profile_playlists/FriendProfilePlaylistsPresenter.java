package interface_adapter.friends_list_user_story.friend_profile_playlists;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileState;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileViewModel;
import use_case.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsOutputBoundary;

public class FriendProfilePlaylistsPresenter implements FriendProfilePlaylistsOutputBoundary {
    private final FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final FriendProfileViewModel friendProfileViewModel;

    public FriendProfilePlaylistsPresenter(FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel,
                                           ViewManagerModel viewManagerModel, FriendProfileViewModel friendProfileViewModel) {
        this.friendProfilePlaylistsViewModel = friendProfilePlaylistsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.friendProfileViewModel = friendProfileViewModel;
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

    @Override
    public void switchToFriendProfileView(String selectedFriendName, String password) {
        viewManagerModel.setState(friendProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        final FriendProfileState friendProfileState = friendProfileViewModel.getState();
        friendProfileState.setPassword(password);
        friendProfileState.setUsername(selectedFriendName);
        this.friendProfileViewModel.setState(friendProfileState);
        this.friendProfileViewModel.firePropertyChanged();
    }
}
