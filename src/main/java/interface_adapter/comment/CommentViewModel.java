package interface_adapter.comment;

import interface_adapter.ViewModel;

/**
 * ViewModel for Comments.
 */
public class CommentViewModel extends ViewModel<CommentState> {

    public CommentViewModel() {
        super("comment");
        setState(new CommentState());
    }
}
