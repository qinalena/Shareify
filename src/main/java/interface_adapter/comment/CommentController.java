package interface_adapter.comment;

import use_case.comment.CommentInputBoundary;

/**
 * Controller for comment use cases.
 */
public class CommentController {

    private final CommentInputBoundary commentUseCaseInteractor;

    public CommentController(CommentInputBoundary commentUseCaseInteractor) {
        this.commentUseCaseInteractor = commentUseCaseInteractor;
    }

    /**
     * Executes comment use case.
     * @param comment the comment inputted (null if no comment)
     * @param friendUsername friend's username
     * @param playlistName friend's playlist name
     */
    public void execute(String comment, String friendUsername, String playlistName) {
        if (comment != null) {
            commentUseCaseInteractor.executeSaveComment(comment, friendUsername, playlistName);
        }
        else {
            commentUseCaseInteractor.executeLoadComments(friendUsername, playlistName);
        }
    }

    /**
     * Switch to friend playlist view when back button is pressed.
     */
    public void switchToFriendPlaylistView() {
        commentUseCaseInteractor.switchToFriendPlaylistView();
    }
}

