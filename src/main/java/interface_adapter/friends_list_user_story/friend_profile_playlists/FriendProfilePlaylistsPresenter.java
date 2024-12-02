package interface_adapter.friends_list_user_story.friend_profile_playlists;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.friend_playlist.FriendPlaylistViewModel;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileState;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileViewModel;
import use_case.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsOutputBoundary;
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionOutputData;

public class FriendProfilePlaylistsPresenter implements FriendProfilePlaylistsOutputBoundary {
    private final FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel;
    private final ViewManagerModel viewManagerModel;
    private final FriendProfileViewModel friendProfileViewModel;
    private final FriendPlaylistViewModel friendPlaylistViewModel;

    public FriendProfilePlaylistsPresenter(FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel,
                                           ViewManagerModel viewManagerModel, FriendProfileViewModel friendProfileViewModel,
                                           FriendPlaylistViewModel friendPlaylistViewModel) {
        this.friendProfilePlaylistsViewModel = friendProfilePlaylistsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.friendProfileViewModel = friendProfileViewModel;
        this.friendPlaylistViewModel = friendPlaylistViewModel;
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

    @Override
    public void switchToPlaylistView(PlaylistCollectionOutputData playlistCollectionOutputData, String username, String password) {
        friendPlaylistViewModel.getState().setCurrentPlaylist(playlistCollectionOutputData.getPlaylist());
        friendPlaylistViewModel.getState().setFriendUsername(username);
        friendPlaylistViewModel.getState().setFriendPassword(password);
        friendPlaylistViewModel.firePropertyChanged();

        viewManagerModel.setState(friendPlaylistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
