package use_case.comment;

import data_access.DBUserDataAccessObject;
import data_access.DataAccessException;

import java.util.List;

/**
 * Interactor for the comment saving and loading use cases.
 */
public class CommentInteractor implements CommentInputBoundary {

    private final CommentDataAccessInterface commentDataAccessInterface;
    private final CommentOutputBoundary commentOutputBoundary;

    public CommentInteractor(CommentDataAccessInterface commentDataAccessInterface,
                             CommentOutputBoundary commentOutputBoundary) {
        this.commentDataAccessInterface = commentDataAccessInterface;
        this.commentOutputBoundary = commentOutputBoundary;
    }

    @Override
    public void executeSaveComment(String comment, String friendUsername, String playlistName) {
        try {
            final List<String> comments = commentDataAccessInterface
                    .saveCommentFromUser(playlistName, friendUsername, comment);
            commentOutputBoundary.prepareSuccessView(comments);
        }
        catch (DataAccessException ex) {
            commentOutputBoundary.prepareFailView(ex.getMessage());
        }
    }

    @Override
    public void executeLoadComments(String friendUsername, String playlistName) {
        try {
            final List<String> updatedComments = commentDataAccessInterface
                    .loadCommentsFromUser(playlistName, friendUsername);
            commentOutputBoundary.prepareSuccessView(updatedComments);
        }
        catch (DataAccessException ex) {
            commentOutputBoundary.prepareFailView(ex.getMessage());
        }
    }

    @Override
    public void switchToFriendPlaylistView() {
        commentOutputBoundary.switchToFriendPlaylistView();
    }
}
