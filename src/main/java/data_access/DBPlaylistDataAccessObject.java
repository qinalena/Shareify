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
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionDataAccessInterface;
import use_case.user_profile_user_story.note.DataAccessException;

/**
 * The DAO for accessing individual playlists stored in database.
 */
public class DBPlaylistDataAccessObject implements PlaylistCollectionDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    static final int CREDENTIAL_ERROR = 401;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";

    /**
     * Updates the user information in the database.
     *
     * @param user The user whose information is being updated.
     * @param key The key of the information to be updated (e.g., "note", "friends", etc.).
     * @param newInfo The new information to be updated.
     * @throws DataAccessException If there is an error updating the user information, such as invalid credentials or a database error.
     */

    // Method to add playlist to the database
    @Override
    public void addPlaylist(User user, String newPlaylist) throws DataAccessException {
        // Make API call to get User object
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

                // Check if key doesn't exists in the 'info' JSON
                if (!data.has("playlists")) {
                    data.put("playlists", new JSONArray());
                }
                final JSONArray currentPlaylists = data.getJSONArray("playlists");
                currentPlaylists.put(newPlaylist);

                // Update new user data with new playlist collection
                data.put("playlists", currentPlaylists);

                // Create updated request body
                final JSONObject updatedUser = new JSONObject();
                updatedUser.put("username", username);
                updatedUser.put("password", user.getPassword());
                updatedUser.put("info", data);

                final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
                final RequestBody body = RequestBody.create(mediaType, updatedUser.toString());
                final Request updateRequest = new Request.Builder()
                        .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                        .method("PUT", body)
                        .addHeader("Content-Type", CONTENT_TYPE_JSON)
                        .build();

                // Send update request
                final Response updateResponse = client.newCall(updateRequest).execute();
                final JSONObject updateResponseBody = new JSONObject(updateResponse.body().string());

                if (updateResponseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                    System.out.println("Playlist added successfully!");
                }
                else {
                    throw new DataAccessException("Error updating user info! " + updateResponseBody.getString(MESSAGE));
                }
            }
            else {
                throw new DataAccessException("Error retrieving user data to add Playlist: " + responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException("Error occurred while adding playlist " + ex.getMessage());
        }
    }

    @Override
    public void removePlaylist(User user, int index) throws DataAccessException {
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

                // See if keys exists in 'info' JSON
                if (data.has("playlists")) {
                    final JSONArray currentPlaylists = data.getJSONArray("playlists");
                    // Check that index is valid
                    if (index < 0 || index >= currentPlaylists.length()) {
                        throw new DataAccessException("Invalid index!");
                    }
                    // Remove playlist from specified index
                    currentPlaylists.remove(index);

                    // Update the playlist array in the info
                    data.put("playlists", currentPlaylists);
                }
                else {
                    // Do nothing if no playlists
                    System.out.println("No playlists to remove");
                    return;
                }

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
                    System.out.println("Playlist removed successfully!");
                }
                else {
                    throw new DataAccessException("No playlist found for user! " + updateResponseBody.getString(MESSAGE));
                }
            }
            else {
                throw new DataAccessException("Error retrieving user data to remove the playlist: " + responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException("Error occurred while updating user info: " + ex.getMessage());
        }
    }

    // Method to retrieve list of playlists associated with the given username
    @Override
    public List<String> getPlaylists(String username) throws DataAccessException {
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
}
