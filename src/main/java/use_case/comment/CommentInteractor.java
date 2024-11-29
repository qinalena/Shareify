package use_case.comment;

import data_access.DBUserDataAccessObject;
import use_case.user_profile_user_story.note.DataAccessException;

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
    public void executeSaveComment(String comment, String username, String playlistName) {
        try {
            final List<String> comments = commentDataAccessInterface
                    .saveCommentFromUser(comment, username, playlistName);
            commentOutputBoundary.prepareSuccessView(comments);
        }
        catch (DataAccessException ex) {
            commentOutputBoundary.prepareFailView(ex.getMessage());
        }
    }

    @Override
    public void executeLoadComments(String username, String playlistName) {
        try {
            final List<String> updatedComments = commentDataAccessInterface
                    .loadCommentsFromUser(username, playlistName);
            commentOutputBoundary.prepareSuccessView(updatedComments);
        }
        catch (DataAccessException ex) {
            commentOutputBoundary.prepareFailView(ex.getMessage());
        }
    }
}
