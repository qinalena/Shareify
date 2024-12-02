package use_case.comment;

import data_access.DataAccessException;

import java.util.List;

/**
 * Interface for the comment DAO, consists of methods for loading and saving comments.
 */
public interface CommentDataAccessInterface {

    /**
     * Load in comments from a user.
     * @param friendUsername owner of playlist
     * @param playlistName name of playlist
     * @return a list of comments
     * @throws DataAccessException exception
     */
    List<String> loadCommentsFromUser(String playlistName, String friendUsername) throws DataAccessException;

    /**
     * Saves a comment to a DB and returns updated comment list.
     * @param comment comment we want to add
     * @param friendUsername owner of playlist
     * @param playlistName name of playlist
     * @return a list of comments
     * @throws DataAccessException exception
     */
    List<String> saveCommentFromUser(String playlistName, String friendUsername, String comment) throws DataAccessException;
}
