package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import entity.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.user_profile_user_story.note.DataAccessException;
import use_case.user_profile_user_story.note.NoteDataAccessInterface;

/**
 * The DAO for accessing notes stored in the database.
 */
public class DBNoteDataAccessObject implements NoteDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final int CREDENTIAL_ERROR = 401;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";

    /**
     * Saves a note for the given user.
     *
     * @param user The user for whom the note is being saved.
     * @param note The note to be saved.
     * @return The saved note if the operation is successful.
     * @throws DataAccessException If there is an error saving the note, such as invalid credentials or a database error.
     */
    @Override
    public String saveNote(User user, String note) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // Update user object's note
        user.setNote(note);

        // POST METHOD to save note
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());
        final JSONObject extra = new JSONObject();
        extra.put("note", note);
        requestBody.put("info", extra);
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                return loadNote(user);
            }
            else if (responseBody.getInt(STATUS_CODE_LABEL) == CREDENTIAL_ERROR) {
                throw new DataAccessException("Message could not be found or password was incorrect");
            }
            else {
                throw new DataAccessException("Database error: " + responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    /**
     * Loads the note associated with the given user.
     *
     * @param user The user for whom the note is being loaded.
     * @return The loaded note if the operation is successful.
     * @throws DataAccessException If there is an error loading the note, such as invalid credentials or a database error.
     */
    @Override
    public String loadNote(User user) throws DataAccessException {
        // Make an API call to get the user object.
        final String username = user.getName();
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject data = userJSONObject.getJSONObject("info");
                return data.getString("note");
            }
            else {
                throw new DataAccessException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Creates a new user in the database.
     *
     * @param user The user to be created.
     * @throws DataAccessException If there is an error creating the user, such as a database error or duplicate username.
     */
    public static void createUser(User user) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST method to create a new user (same as save method)
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());

        final JSONObject userInfo = new JSONObject();
        if (user.getInfo() != null && !user.getInfo().isEmpty()) {
            for (String info : user.getInfo()) {
                userInfo.append("info", info);
            }
        }
        requestBody.put("info", userInfo);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            // Handle the response status
            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // Success, user created!
            }
            else {
                // Throw error if status is not 200 (success)
                throw new DataAccessException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    /**
     * Checks if a user exists in the database by their username.
     *
     * @param username The username of the user to check.
     * @return The username of the user if found, otherwise throws an exception.
     * @throws DataAccessException If the user is not found or there is an error accessing the database.
     */
    public String getUserByUsername(String username) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        System.out.println("before database call username: " + username);
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                return responseBody.getJSONObject("user").getString(USERNAME);
            } else {
                throw new DataAccessException("User not found in the database.");
            }
        } catch (IOException | JSONException ex) {
            throw new DataAccessException("Error occurred while searching for the user: " + ex.getMessage());
        }
    }

    /**
     * Updates the user information in the database.
     *
     * @param user The user whose information is being updated.
     * @param key The key of the information to be updated (e.g., "note", "friends", etc.).
     * @param newInfo The new information to be updated.
     * @throws DataAccessException If there is an error updating the user information, such as invalid credentials or a database error.
     */
    public void updateUserInfo(User user, String key, String newInfo) throws DataAccessException {
        final String username = user.getName();
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        // URL to fetch the current user data
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            // Make the request and get the response
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            // Check if the response was successful
            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // Get the 'user' and 'info' data from the response
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject data = userJSONObject.getJSONObject("info");

                // Debug: Print current info to check the state before update
                System.out.println("Before update - Info JSON file: " + data.toString(4));

                // Check if the key exists in the 'info' JSON
                if (data.has(key)) {
                    String oldText = data.getString(key);
                    data.put(key, oldText + newInfo);
                } else {
                    data.put(key, newInfo);
                }

                // Debug: Print the updated 'info' JSON
                System.out.println("After update - Info JSON file: " + data.toString(4));

                // Create the updated request body
                JSONObject updatedUser = new JSONObject();
                updatedUser.put("username", username);
                updatedUser.put("password", user.getPassword());
                updatedUser.put("info", data);

                final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
                final RequestBody body = RequestBody.create(updatedUser.toString(), mediaType);
                final Request updateRequest = new Request.Builder()
                        .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                        .method("PUT", body)
                        .addHeader("Content-Type", CONTENT_TYPE_JSON)
                        .build();

                // Send the update request
                final Response updateResponse = client.newCall(updateRequest).execute();
                final JSONObject updateResponseBody = new JSONObject(updateResponse.body().string());

                // Handle the response from the server after updating the user info
                if (updateResponseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                    System.out.println("User info updated successfully!");
                } else {
                    throw new DataAccessException("Error updating user info: " + updateResponseBody.getString(MESSAGE));
                }

            } else {
                throw new DataAccessException("Error retrieving user data: " + responseBody.getString(MESSAGE));
            }

        } catch (IOException | JSONException ex) {
            throw new DataAccessException("Error occurred while updating user info: " + ex.getMessage());
        }
    }

    /**
     * Adds a friend to the user's friend list in the database.
     *
     * @param user The user to whom the friend is being added.
     * @param newName The name of the friend to be added.
     * @throws DataAccessException If there is an error adding the friend, such as invalid credentials or a database error.
     */
    public void addFriendinDB(User user, String newName) throws DataAccessException {

        // Dummy user
        // user = new User("newUserName7", "password123");

        final String username = user.getName();
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        // URL to fetch the current user data
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            // Make the request and get the response
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            // Check if the response was successful
            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // Get the 'user' and 'info' data from the response
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject data = userJSONObject.getJSONObject("info");
                // Debug: Print current info to check the state before update
                System.out.println("Before update - Info JSON file: " + data.toString(4));

                // Check if the key exists in the 'info' JSON
                if (data.has("friends")) {
                    final JSONArray currentfriends = data.getJSONArray("friends");
                    data.put("friends", currentfriends.put(newName));
                } else {
                    final JSONArray friends = new JSONArray();
                    friends.put(newName);
                    data.put("friends", friends);
                }

                // Debug: Print the updated 'info' JSON
                System.out.println("After update - Info JSON file: " + data.toString(4));

                // Create the updated request body
                JSONObject updatedUser = new JSONObject();
                updatedUser.put("username", username);
                updatedUser.put("password", user.getPassword());
                updatedUser.put("info", data);

                final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
                final RequestBody body = RequestBody.create(updatedUser.toString(), mediaType);
                final Request updateRequest = new Request.Builder()
                        .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                        .method("PUT", body)
                        .addHeader("Content-Type", CONTENT_TYPE_JSON)
                        .build();

                // Send the update request
                final Response updateResponse = client.newCall(updateRequest).execute();
                final JSONObject updateResponseBody = new JSONObject(updateResponse.body().string());

                // Handle the response from the server after updating the user info
                if (updateResponseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                    System.out.println("User info updated successfully!");
                } else {
                    throw new DataAccessException("Error updating user info: " + updateResponseBody.getString(MESSAGE));
                }

            } else {
                throw new DataAccessException("Error retrieving user data: " + responseBody.getString(MESSAGE));
            }

        } catch (IOException | JSONException ex) {
            throw new DataAccessException("Error occurred while updating user info: " + ex.getMessage());
        }
    }

    /**
     * Removes a friend from the user's friend list in the database.
     *
     * @param user The user from whom the friend is being removed.
     * @param index The index of the friend to be removed in the friend list.
     * @throws DataAccessException If there is an error removing the friend, such as invalid credentials, a database error, or an invalid index.
     */
    public void removeFriendinDB(User user, int index) throws DataAccessException {
        // Dummy user
        // user = new User("newUserName7", "password123");
        final String username = user.getName();
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        // URL to fetch the current user data
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            // Make the request and get the response
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            // Check if the response was successful
            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // Get the 'user' and 'info' data from the response
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject data = userJSONObject.getJSONObject("info");
                // Debug: Print current info to check the state before update
                System.out.println("Before update - Info JSON file: " + data.toString(4));

                // Check if the key exists in the 'info' JSON
                if (data.has("friends")) {
                    final JSONArray currentFriends = data.getJSONArray("friends");

                    // Check if the index is valid
                    if (index < 0 || index >= currentFriends.length()) {
                        throw new DataAccessException("Invalid index for removing friend");
                    }

                    // Remove the friend at the specified index
                    currentFriends.remove(index);

                    // Update the 'friends' array in the 'info' JSON
                    data.put("friends", currentFriends);
                } else {
                    // If there are no friends, do nothing
                    System.out.println("No friends to remove");
                    return;
                }

                // Debug: Print the updated 'info' JSON
                System.out.println("After update - Info JSON file: " + data.toString(4));

                // Create the updated request body
                JSONObject updatedUser = new JSONObject();
                updatedUser.put("username", username);
                updatedUser.put("password", user.getPassword());
                updatedUser.put("info", data);

                final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
                final RequestBody body = RequestBody.create(updatedUser.toString(), mediaType);
                final Request updateRequest = new Request.Builder()
                        .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                        .method("PUT", body)
                        .addHeader("Content-Type", CONTENT_TYPE_JSON)
                        .build();

                // Send the update request
                final Response updateResponse = client.newCall(updateRequest).execute();
                final JSONObject updateResponseBody = new JSONObject(updateResponse.body().string());

                // Handle the response from the server after updating the user info
                if (updateResponseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                    System.out.println("User info updated successfully!");
                } else {
                    throw new DataAccessException("Error updating user info: " + updateResponseBody.getString(MESSAGE));
                }

            } else {
                throw new DataAccessException("Error retrieving user data: " + responseBody.getString(MESSAGE));
            }

        } catch (IOException | JSONException ex) {
            throw new DataAccessException("Error occurred while removing friend: " + ex.getMessage());
        }
    }

    /**
     * Retrieves the list of friends associated with the given username.
     *
     * @param username The username of the user whose friends are being retrieved.
     * @return A list of friends' names if the operation is successful.
     * @throws DataAccessException If there is an error retrieving the friends, such as invalid credentials or a database error.
     */
    public List<String> getFriends(String username) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        // URL to fetch the current user data
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            // Make the request and get the response
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            // Check if the response was successful
            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // Get the 'user' and 'info' data from the response
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject data = userJSONObject.getJSONObject("info");

                // Debug: Print current info to check the state
                System.out.println("Info JSON file: " + data.toString(4));

                // Check if the key exists in the 'info' JSON
                if (data.has("friends")) {
                    final JSONArray friendsArray = data.getJSONArray("friends");
                    List<String> friendsList = new ArrayList<>();

                    // Convert JSONArray to List<String>
                    for (int i = 0; i < friendsArray.length(); i++) {
                        friendsList.add(friendsArray.getString(i));
                    }

                    return friendsList;
                } else {
                    // If there are no friends, return an empty list
                    return new ArrayList<>();
                }
            } else {
                throw new DataAccessException("Error retrieving user data: " + responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException e) {
            throw new DataAccessException("Error occurred while retrieving friends: " + e.getMessage());
        }
    }

    /**
     * Retrieves the password associated with the given username.
     *
     * @param username The username of the user whose password is being retrieved.
     * @return The password of the user if found.
     * @throws DataAccessException If the user is not found or there is an error accessing the database.
     */
    public String getPasswordByUserName(String username) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        // URL to fetch the current user data
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            // Make the request and get the response
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            // Check if the response was successful
            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // Get the 'user' data from the response
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                // Return the password
                return userJSONObject.getString(PASSWORD);
            } else {
                throw new DataAccessException("Error retrieving user data: " + responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException e) {
            throw new DataAccessException("Error occurred while retrieving password: " + e.getMessage());
        }
    }
}
