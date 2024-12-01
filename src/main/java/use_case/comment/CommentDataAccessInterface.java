package use_case.comment;

import data_access.DataAccessException;

import java.util.List;

/**
 * Interface for the comment DAO, consists of methods for loading and saving comments.
 */
public interface CommentDataAccessInterface {

    /**
     * Load in comments from a user.
     * @param username owner of playlist
     * @param playlistName name of playlist
     * @return a list of comments
     * @throws DataAccessException exception
     */
    List<String> loadCommentsFromUser(String username, String playlistName) throws DataAccessException;

    /**
     * Saves a comment to a DB and returns updated comment list.
     * @param comment comment we want to add
     * @param username owner of playlist
     * @param playlistName name of playlist
     * @return a list of comments
     * @throws DataAccessException exception
     */
    List<String> saveCommentFromUser(String comment, String username, String playlistName) throws DataAccessException;
}
