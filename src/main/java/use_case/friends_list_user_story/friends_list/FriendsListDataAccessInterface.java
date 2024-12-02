package use_case.friends_list_user_story.friends_list;

import data_access.DataAccessException;
import entity.User;

import java.util.List;

public interface FriendsListDataAccessInterface {

    /**
     * Retrieves the list of friends associated with the given username.
     *
     * @param username The username of the user whose friends are being retrieved.
     * @return A list of friends' names if the operation is successful.
     * @throws DataAccessException If there is an error retrieving the friends, such as invalid credentials or a database error.
     */
    List<String> getFriends(String username) throws DataAccessException;

    /**
     * Removes a friend from the user's friend list in the database.
     *
     * @param user The user from whom the friend is being removed.
     * @param index The index of the friend to be removed in the friend list.
     * @throws DataAccessException If there is an error removing the friend, such as invalid credentials, a database error, or an invalid index.
     */
    void removeFriendinDB(User user, int index) throws DataAccessException;

    /**
     * Retrieves the password associated with the given username.
     *
     * @param username The username of the user whose password is being retrieved.
     * @return The password of the user if found.
     * @throws DataAccessException If the user is not found or there is an error accessing the database.
     */
    String getPasswordByUserName(String username) throws DataAccessException;
}
