package use_case.friends_list_user_story.friend_profile_friends_list;

import data_access.DataAccessException;

import java.util.List;

public interface FriendProfileFriendsListDataAccessInterface {

    /**
     * Retrieves the list of friends associated with the given username.
     *
     * @param username The username of the user whose friends are being retrieved.
     * @return A list of friends' names if the operation is successful.
     * @throws DataAccessException If there is an error retrieving the friends, such as invalid credentials or a database error.
     */
     List<String> getFriends(String username) throws DataAccessException;
}
