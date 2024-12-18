package use_case.user_profile_user_story.note;

import data_access.DataAccessException;
import entity.User;

/**
 * Interface for the NoteDAO. It consists of methods for
 * both loading and saving a note.
 */
public interface NoteDataAccessInterface {

    /**
     * Saves a note for a given user. This will replace any existing note.
     * @param user the user information associated with the note
     * @param note the note to be saved
     * @return the contents of the note
     * @throws DataAccessException if the user's note can not be saved for any reason
     */
    String saveNote(User user, String note) throws DataAccessException;

    /**
     * Returns the note associated with the user. The password
     * is not checked, so anyone can read the information.
     * @param user the user information associated with the note
     * @return the contents of the note
     * @throws DataAccessException if the user's note can not be loaded for any reason
     */
    String loadNote(User user) throws DataAccessException;

    /**
     * Returns the note associated with the user. The password
     * is not checked, so anyone can read the information.
     * @param username the logged-in user's username
     * @return User.
     */
    User getUser(String username);

}
