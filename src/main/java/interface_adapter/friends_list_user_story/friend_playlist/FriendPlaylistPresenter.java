package interface_adapter.friends_list_user_story.friend_playlist;

import interface_adapter.ViewManagerModel;
import interface_adapter.comment.CommentState;
import interface_adapter.comment.CommentViewModel;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsViewModel;
import use_case.friends_list_user_story.friend_playlist.FriendPlaylistOutputBoundary;

public class FriendPlaylistPresenter implements FriendPlaylistOutputBoundary {

    private final FriendPlaylistViewModel friendPlaylistViewModel;
    private final FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel;
    private final CommentViewModel commentViewModel;
    private final ViewManagerModel viewManagerModel;
    // Add comment view model to switch to

    public FriendPlaylistPresenter(FriendPlaylistViewModel friendPlaylistViewModel,
                                   ViewManagerModel viewManagerModel,
                                   FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel,
                                   CommentViewModel commentViewModel) {
        this.friendPlaylistViewModel = friendPlaylistViewModel;
        this.friendProfilePlaylistsViewModel = friendProfilePlaylistsViewModel;
        this.viewManagerModel = viewManagerModel;
        this.commentViewModel = commentViewModel;
    }

    @Override
    public void switchToPlaylistCollectionView() {
        viewManagerModel.setState(friendProfilePlaylistsViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToCommentView(String friendUsername, String playlistName) {
        viewManagerModel.setState(commentViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

        final CommentState commentState = commentViewModel.getState();
        commentState.setFriendUsername(friendUsername);
        commentState.setPlaylistName(playlistName);
        this.commentViewModel.setState(commentState);
        this.commentViewModel.firePropertyChanged();
    }

}
