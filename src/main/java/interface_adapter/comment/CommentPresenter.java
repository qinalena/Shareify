package interface_adapter.comment;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.friend_playlist.FriendPlaylistViewModel;
import use_case.comment.CommentOutputBoundary;
import view.ViewManager;
import view.friends_list_user_story.FriendPlaylistView;

import javax.swing.text.View;
import java.util.List;

/**
 * Presenter for viewing and writing comments.
 */
public class CommentPresenter implements CommentOutputBoundary {

    private final CommentViewModel commentViewModel;
    private final ViewManagerModel viewManagerModel;
    private final FriendPlaylistViewModel friendPlaylistViewModel;

    public CommentPresenter(CommentViewModel commentViewModel, ViewManagerModel viewManagerModel,
                            FriendPlaylistViewModel friendPlaylistViewModel) {
        this.commentViewModel = commentViewModel;
        this.viewManagerModel = viewManagerModel;
        this.friendPlaylistViewModel = friendPlaylistViewModel;
    }

    @Override
    public void prepareSuccessView(List<String> comments) {
        commentViewModel.getState().setComments(comments);
        commentViewModel.getState().setError(null);
        commentViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        commentViewModel.getState().setError(errorMessage);
        commentViewModel.firePropertyChanged();

    }

    @Override
    public void switchToFriendPlaylistView() {
        viewManagerModel.setState(friendPlaylistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

}

