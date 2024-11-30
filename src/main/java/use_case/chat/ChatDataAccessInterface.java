package use_case.chat;

import use_case.user_profile_user_story.note.DataAccessException;

import java.util.List;

/**
 * Interface for getting chat use case related data.
 */
public interface ChatDataAccessInterface {

    /**
     * Load messages between users.
     * @param sender currents sender of message (logged-in user)
     * @param receiver of messages.
     * @return a list of messages.
     * @throws DataAccessException exception
     */
    List<String> loadChatBetweenUsers(String sender, String receiver) throws DataAccessException;

    /**
     * Save a message to the Chat DB between the two users and get the updated list of messages.
     * @param chat we would like to save
     * @param sender of message (current logged-in user)
     * @param receiver of message
     * @return the updated list of messages
     * @throws DataAccessException exception
     */
    List<String> saveChatBetweenUsers(String chat, String sender, String receiver) throws DataAccessException;
}
