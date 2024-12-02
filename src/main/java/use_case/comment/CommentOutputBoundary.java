package use_case.comment;

import java.util.List;

/**
 * Presenter.
 */
public interface CommentOutputBoundary {

    /**
     * Prepares the success view for the comment related Use Cases.
     * @param comments the output data
     */
    void prepareSuccessView(List<String> comments);

    /**
     * Prepares the failure view for the comment related Use Cases.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switch to friend playlist view when back button is pressed.
     */
    void switchToFriendPlaylistView();
}
