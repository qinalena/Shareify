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

    public static List<String> saveComment(String playlistName, String comment) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD to save note
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, playlistName);
        requestBody.put(PASSWORD, playlistName);
        final JSONObject extra = new JSONObject();
        final List<String> currentComments = loadComments(playlistName);
        currentComments.add(comment);
        extra.put("comments", currentComments);
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
                return loadComments(playlistName);
            } else if (responseBody.getInt(STATUS_CODE_LABEL) == CREDENTIAL_ERROR) {
                throw new DataAccessException("Message could not be found or password was incorrect");
            } else {
                throw new DataAccessException("Database error: " + responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

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

    // Access a user's playlist and load comments from that person's object in the database

    /**
     * Method to load the comments of a user's playlist.
     * @param playlistName the name of the playlist we are loading the comments from
     * @return an array containing the comments
     * @throws DataAccessException exception
     * @throws RuntimeException exception
     */
    public static List<String> loadComments(String playlistName) throws DataAccessException {
        // Make an API call to get the user object.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", playlistName))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject data = userJSONObject.getJSONObject("info");
                final List<String> commentsList = new ArrayList<>();
                final JSONArray jsonArray = data.getJSONArray("comments");
                for (int i = 0; i < jsonArray.length(); i++) {
                    commentsList.add(jsonArray.getString(i));
                }
                return commentsList;
            } else {
                throw new DataAccessException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

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

    // Method of checking if a user exists in our database
    public static String getUserByUsername(String username) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

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

    // New method for updating user info at any time
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

}
