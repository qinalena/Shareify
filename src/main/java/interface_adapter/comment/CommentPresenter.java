package interface_adapter.comment;

import use_case.comment.CommentOutputBoundary;

import java.util.List;

/**
 * Presenter for viewing and writing comments.
 */
public class CommentPresenter implements CommentOutputBoundary {

    private final CommentViewModel commentViewModel;

    public CommentPresenter(CommentViewModel commentViewModel) {
        this.commentViewModel = commentViewModel;
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

}

