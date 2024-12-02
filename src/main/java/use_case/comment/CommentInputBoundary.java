package use_case.comment;

/**
 * Input Boundary for comment use cases.
 */
public interface CommentInputBoundary {

    /**
     * Executes the save comment use case.
     * @param comment comment to be saved to database
     * @param friendUsername playlist owner
     * @param playlistName name of playlist
     */
    void executeSaveComment(String comment, String friendUsername, String playlistName);

    /**
     * Executes the load comment use case.
     * @param friendUsername playlist owner
     * @param playlistName name of playlist
     */
    void executeLoadComments(String friendUsername, String playlistName);

    /**
     * Switch to firnd playlist view when back button is pressed.
     */
    void switchToFriendPlaylistView();
}
