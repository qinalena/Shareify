package use_case.comment;

/**
 * Input Boundary for comment use cases.
 */
public interface CommentInputBoundary {

    /**
     * Executes the save comment use case.
     * @param comment comment to be saved to database
     * @param username playlist owner
     * @param playlistName name of playlist
     */
    void executeSaveComment(String comment, String username, String playlistName);

    /**
     * Executes the load comment use case.
     * @param username playlist owner
     * @param playlistName name of playlist
     */
    void executeLoadComments(String username, String playlistName);
}
