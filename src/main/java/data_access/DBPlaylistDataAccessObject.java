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
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";
    private static final String INFO = "info";
    private static final String USER = "user";
    private static final String PLAYLISTCOLLECTION = "playlist collection";

    // Method to add playlist to the database
    public JSONArray addPlaylistinDB(User user, String newPlaylist) throws DataAccessException {
        // Make API call to get User object
        final String username = user.getName();

        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            // Make request and get response
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            // Check if it was successful
            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // Gets the data from 'user' and 'info'
                final JSONObject userJSONObject = responseBody.getJSONObject(USER);
                final JSONObject data = userJSONObject.getJSONObject(INFO);

                // Debug: Print current info to check the state before update
                System.out.println("Before update - Info JSON file: " + data.toString(4));

                // Check if key exists in the 'info' JSON
                if (!data.has(PLAYLISTCOLLECTION)) {
                    // create JSONArray with newPlaylist as key + empty JSONArray
                    data.put(PLAYLISTCOLLECTION, new JSONArray());
                }

                // Retrieve current playlist collection
                final JSONArray currentPlaylists = data.getJSONArray(PLAYLISTCOLLECTION);

                // Check if playlist already exists
                boolean playlistExists = false;
                for (int i = 0; i < currentPlaylists.length(); i++) {
                    JSONObject playlist = currentPlaylists.getJSONObject(i);
                    if (playlist.has(newPlaylist)) {
                        playlistExists = true;
                        break;
                    }
                }
                // If the playlist does not exist, add it
                if (!playlistExists) {
                    // Create a new playlist with an empty array
                    JSONObject newPlaylistObject = new JSONObject();
                    newPlaylistObject.put(newPlaylist, new JSONArray());
                    currentPlaylists.put(newPlaylistObject);
                    System.out.println(newPlaylist + " added successfully!");
                } else {
                    System.out.println(newPlaylist + " already exists!");
                }

                // Debug: Print the updated 'info' JSON
                System.out.println("After update - Info JSON file: " + data.toString(4));

                // Create updated request body
                final JSONObject updatedUser = new JSONObject();
                updatedUser.put(USERNAME, username);
                updatedUser.put(PASSWORD, user.getPassword());
                updatedUser.put(INFO, data);

                final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
                final RequestBody body = RequestBody.create(updatedUser.toString(), mediaType);
                final Request updateRequest = new Request.Builder()
                        .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                        .method("PUT", body)
                        .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
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
                throw new DataAccessException(
                        "Error retrieving user data to add Playlist: " + responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException("Error occurred while adding playlist " + ex.getMessage());
        }
        return null;
    }


    public void removePlaylistinDB(User user, String playlistName) throws DataAccessException {
        final String username = user.getName();
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Get current user data
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();
        try {
            // Make a request and get response
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            // Check to see if successful
            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject(USER);
                final JSONObject data = userJSONObject.getJSONObject(INFO);

                // Debug: Print current info to check the state before update
                System.out.println("Before update - Info JSON file: " + data.toString(4));

                // See if keys exists in 'info' JSON
                if (data.has(PLAYLISTCOLLECTION)) {
                    final JSONArray playlistCollection = data.getJSONArray(PLAYLISTCOLLECTION);

                    boolean playlistRemoved = false;

                    // Search through playlist collection and remove the playlist
                    for (int i = 0; i < playlistCollection.length(); i++) {
                        JSONObject playlist = playlistCollection.getJSONObject(i);
                        if (playlist.has(playlistName)) {
                            playlistCollection.remove(i);
                            playlistRemoved = true;
                            break;
                        }
                    }

                    // If playlist was removed
                    if (playlistRemoved) {
                        // Update user with new playlist Collection
                        final JSONObject updatedUser = new JSONObject();
                        updatedUser.put(USERNAME, username);
                        updatedUser.put(PASSWORD, user.getPassword());
                        updatedUser.put(INFO, data);

                        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
                        final RequestBody body = RequestBody.create(updatedUser.toString(), mediaType);
                        final Request updateRequest = new Request.Builder()
                                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                                .method("PUT", body)
                                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                                .build();

                        // Send the update request
                        final Response updateResponse = client.newCall(updateRequest).execute();
                        final JSONObject updateResponseBody = new JSONObject(updateResponse.body().string());

                        // Handle the response from the server after updating the user info
                        if (updateResponseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                            System.out.println("Playlist removed successfully!");
                        }
                        else {
                            throw new DataAccessException(
                                    "Error updating user info: " + updateResponseBody.getString(MESSAGE));
                        }
                    }
                    // remove the playlist key
//                    data.remove(playlistName);

                    // Debugging: Print updated 'info' JSON
                    System.out.println("Before update - Info JSON file: " + data.toString(4));
                }
                else {
                    // Do nothing if no playlists
                    System.out.println("Playlist " + playlistName + " does not exist!");
                }
            }
            else {
                throw new DataAccessException(
                        "Error retrieving user data to remove the playlist: " + responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException("Error occurred while updating user info: " + ex.getMessage());
        }
    }

    // Method to retrieve list of playlists associated with the given username
    public List<String> getPlaylists(String username) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject(USER);
                final JSONObject data = userJSONObject.getJSONObject(INFO);

                // Debug: Print current info to check the state
                System.out.println("Info JSON file: " + data.toString(4));

                // Retrieve all keys in 'info' as playlist names
                List<String> playlists = new ArrayList<>();
                if (data.has(PLAYLISTCOLLECTION)) {
                    final JSONArray playlistCollection = data.getJSONArray(PLAYLISTCOLLECTION);

                    // Iterate over each object in collection
                    for (int i = 0; i < playlistCollection.length(); i++) {
                        JSONObject playlist = playlistCollection.getJSONObject(i);
                        // Each playlist is an object with the playlist name as the key
                        for (String key : playlist.keySet()) {
                            playlists.add(key);  // Add playlist name to the list
                        }
                    }
                }
                return playlists;
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
