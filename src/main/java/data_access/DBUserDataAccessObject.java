package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Playlist;
import entity.Song;
import entity.UserFactoryInter;
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
import use_case.playlist_user_story.playlist.PlaylistDataAccessInterface;
import use_case.playlist_user_story.search_song.SearchSongDataAccessInterface;
import use_case.user_profile_user_story.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login_user_story.login.LoginUserDataAccessInterface;
import use_case.user_profile_user_story.logout.LogoutUserDataAccessInterface;
import use_case.DataAccessException;
import use_case.login_user_story.signup.SignupUserDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface,
        LogoutUserDataAccessInterface, PlaylistCollectionDataAccessInterface,
        PlaylistDataAccessInterface, SearchSongDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String INFO = "info";
    private static final String MESSAGE = "message";

    // Keep track of current logged-in user so we can make calls to the DB
    private User currentUser;

    private final UserFactoryInter userFactory;

    public DBUserDataAccessObject(UserFactoryInter userFactory) {
        this.userFactory = userFactory;
        // No need to do anything to reinitialize a user list! The data is the cloud that may be miles away.
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }

    @Override
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    @Override
    public User getUser(String username) throws RuntimeException {
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
        requestBody.put(USERNAME, user.getUsername());
        requestBody.put(PASSWORD, user.getPassword());
        final JSONObject extra = new JSONObject();
        final JSONArray friends = new JSONArray();
        friends.put("newUserName3");
        friends.put("newUserName4");
        friends.put("newUserName5");
        friends.put("newUserName6");
        extra.put("friends", friends);
        requestBody.put(INFO, extra);
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
        requestBody.put(USERNAME, user.getUsername());
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

    @Override
    public void addPlaylistToUser(String playlistName) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", currentUser.getUsername()))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject data = userJSONObject.getJSONObject("info");
                System.out.println("Before update - Info JSON file: " + data.toString(4));

                // Get current list of playlists or initialize an empty list
                JSONObject playlistCollection = data.optJSONObject("playlist collection");
                if (playlistCollection == null) {
                    playlistCollection = new JSONObject();
                }

                // Add new playlist to the list
                playlistCollection.put(playlistName, new JSONObject());

                // Update info field with new playlist list
                data.put("playlists collection", playlistCollection);

                System.out.println("After update - Info JSON file: " + data.toString(4));

                // Create updated request
                JSONObject updatedUser = new JSONObject();
                updatedUser.put(USERNAME, currentUser.getUsername());
                updatedUser.put(PASSWORD, currentUser.getPassword());
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

    @Override
    public void addSongToPlaylist(Playlist playlist, Song song) throws DataAccessException {

    }

    @Override
    public List<Playlist> getPlaylistCollection() throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();

        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", currentUser.getUsername()))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject data = userJSONObject.getJSONObject("info");

                if (data.has("playlist collection")) {
                    // Convert jsonPlaylistCollection to List<Playlist>
                    final List<Playlist> playlistCollection = new ArrayList<>();
                    final JSONObject jsonPlaylistCollection = data.getJSONObject("playlist collection");

                    // Convert jsonPlaylist to Playlist, where the key is the playlist name and the value is a JSONObject representing songs
                    for (String playlistName : jsonPlaylistCollection.keySet()) {
                        final Playlist playlist = new Playlist(playlistName);
                        final JSONObject jsonPlaylist = jsonPlaylistCollection.getJSONObject(playlistName);

                        // Convert jsonArtists to Song, where the key is the song name and the value is a JSONArray of artists
                        for (String songName : jsonPlaylist.keySet()) {
                            final JSONArray jsonArtists = jsonPlaylist.getJSONArray(songName);

                            final String[] artists = new String[jsonArtists.length()];
                            for (int i = 0; i < jsonArtists.length(); i++) {
                                artists[i] = jsonArtists.getString(i);
                            }

                            final Song song = new Song(songName, artists);
                            playlist.addSong(song);

                        }
                        playlistCollection.add(playlist);

                    }
                    return playlistCollection;
                }
                else {
                    // If there is no playlist collection, return an empty list
                    return new ArrayList<>();
                }

            }
            else {
                throw new DataAccessException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }
}
