package interface_adapter.friends_list_user_story.friend_playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsViewModel;
import use_case.friends_list_user_story.friend_playlist.FriendPlaylistOutputBoundary;

public class FriendPlaylistPresenter implements FriendPlaylistOutputBoundary {

    private final FriendPlaylistViewModel friendPlaylistViewModel;
    private final FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel;
    private final ViewManagerModel viewManagerModel;
    // Add comment view model to switch to

    public FriendPlaylistPresenter(FriendPlaylistViewModel friendPlaylistViewModel,
                                   ViewManagerModel viewManagerModel,
                                   FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel) {
        this.friendPlaylistViewModel = friendPlaylistViewModel;
        this.friendProfilePlaylistsViewModel = friendProfilePlaylistsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToPlaylistCollectionView() {
        viewManagerModel.setState(friendProfilePlaylistsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
