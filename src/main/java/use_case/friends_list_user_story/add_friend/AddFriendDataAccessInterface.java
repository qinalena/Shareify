package use_case.friends_list_user_story.add_friend;

import data_access.DataAccessException;
import entity.User;

public interface AddFriendDataAccessInterface {

    /**
     * Checks if a user exists in the database by their username.
     *
     * @param username The username of the user to check.
     * @return The username of the user if found, otherwise throws an exception.
     * @throws DataAccessException If the user is not found or there is an error accessing the database.
     */
    String getUserByUsername(String username) throws DataAccessException;

    /**
     * Adds a friend to the user's friend list in the database.
     *
     * @param user The user to whom the friend is being added.
     * @param newName The name of the friend to be added.
     * @throws DataAccessException If there is an error adding the friend, such as invalid credentials or a database error.
     */
     void addFriendinDB(User user, String newName) throws DataAccessException;
}
