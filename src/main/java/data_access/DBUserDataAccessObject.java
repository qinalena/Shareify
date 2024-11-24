package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.UserFactoryInter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.User;
import entity.UserFactory;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.note.DataAccessException;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";

    private final int indentNumber = 4;

    // Static field to track current logged-in user
    private static String currentUsername;

    private final UserFactoryInter userFactory;


    public DBUserDataAccessObject(UserFactoryInter userFactory) {
        this.userFactory = userFactory;
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    /**
     * Retrieves the inputted username.
     * @param username the username to look up
     * @return
     */
    public User get(String username) {
        // Make an API call to get the user object.
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
                final String name = userJSONObject.getString(USERNAME);
                final String password = userJSONObject.getString(PASSWORD);

                return userFactory.createUser(name, password);
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Sets the current logged-in username.
     * @param name output data
     */
    @Override
    public void setCurrentUsername(String name) {
        currentUsername = name;
    }

    @Override
    public boolean existsByName(String username) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/checkIfUserExists?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            return responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE;
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void createUser(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("POST", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void changePassword(User user) {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/user")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // success!
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Adds playlist info to the user's database.
     * @param user name of user
     * @param playlistName name of playlist
     * @throws DataAccessException ...
     */
    public void addPlaylistToUser(User user, String playlistName) throws DataAccessException {
        final String username = user.getName();
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
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
                // Print current info to check state
                System.out.println("Before update - info JSON file: " + data.toString(indentNumber));

                // See if keys exists in 'info' JSON
                if (data.has("playlists")) {
                    final JSONArray currentPlaylists = data.getJSONArray("playlists");
                    data.put("playlists", currentPlaylists.put(playlistName));
                }
                else {
                    final JSONArray playlists = new JSONArray();
                    playlists.put(playlistName);
                    data.put("playlists", playlists);
                }

                // Check updated info JSON
                System.out.println("After update - info JSON file: " + data.toString(indentNumber) );

                // Create updated request
                final JSONObject updatedUser = new JSONObject();
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
                    System.out.println("User info updated with new playlist successfully!");
                }
                else {
                    throw new DataAccessException("Error updating user info: " + updateResponseBody.getString(MESSAGE));
                }
            }
            else {
                throw new DataAccessException("Error retrieving user data: " + responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException("Error occurred while updating user info: " + ex.getMessage());
        }
    }

    /**
     * Updates the playlists that have been removed.
     * @param user logged-in user
     * @param index index of list of playlists
     * @throws DataAccessException ...
     */
    public void removePlaylistinDB(User user, int index) throws DataAccessException {
        final String username = user.getName();
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
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
                // Print current info to check state
                System.out.println("Before update - info JSON file: " + data.toString(indentNumber));

                // See if keys exists in 'info' JSON
                if (data.has("playlists")) {
                    final JSONArray currentPlaylists = data.getJSONArray("playlists");
                    // Check that index is valid
                    if (index < 0 || index >= currentPlaylists.length()) {
                        throw new DataAccessException("Invalid index!");
                    }
                    // Remove playlist from specified index
                    currentPlaylists.remove(index);

                    // Update playlist array in db
                    data.put("playlists", currentPlaylists);
                }
                else {
                    // Do nothing if no playlists
                    System.out.println("No playlists to remove");
                    return;
                }

                // Check updated info JSON
                System.out.println("After update - info JSON file: " + data.toString(indentNumber) );

                // Create updated request
                final JSONObject updatedUser = new JSONObject();
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
                    System.out.println("User info updated with new playlist successfully!");
                }
                else {
                    throw new DataAccessException("Error updating user info: " + updateResponseBody.getString(MESSAGE));
                }
            }
            else {
                throw new DataAccessException("Error retrieving user data: " + responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException("Error occurred while updating user info: " + ex.getMessage());
        }
    }

    /**
     * Gets playlist to add to db.
     * @param playlistName name of playlist
     * @return playlist
     * @throws DataAccessException ...
     */
    public List<String> getPlaylists(String playlistName) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s"))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject data = userJSONObject.getJSONObject("info");
                // Print current info to check state
                System.out.println("Info JSON file: " + data.toString(indentNumber));

                // See if keys exists in 'info' JSON
                if (data.has("playlists")) {
                    final JSONArray currentPlaylists = data.getJSONArray("playlists");
                    final List<String> playlistList = new ArrayList<>();

                    // Convert JSONArray to List<String>
                    for (int i = 0; i < currentPlaylists.length(); i++) {
                        playlistList.add(currentPlaylists.getString(i));
                    }
                    return playlistList;
                }
                else {
                    // No playlist = return empty list
                    return new ArrayList<>();
                }
            }
            else {
                throw new DataAccessException("Error retrieving user data: " + responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException("Error occurred while retrieving playlist: " + ex.getMessage());
        }
    }

    public String getCurrentUsername() {
        return currentUsername;
    }
}
