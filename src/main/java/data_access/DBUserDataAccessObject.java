package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import use_case.chat.ChatDataAccessInterface;
import use_case.comment.CommentDataAccessInterface;
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
        PlaylistDataAccessInterface, SearchSongDataAccessInterface,
    CommentDataAccessInterface, ChatDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final int CREDENTIAL_ERROR = 401;
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
    public Playlist getPlaylist(String playlistName) throws DataAccessException {
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
                final JSONArray playlistCollection = data.getJSONArray("playlist collection");

                for (int i = 0; i < playlistCollection.length(); i++) {
                    final JSONObject playlist = playlistCollection.getJSONObject(i);
                    if (playlist.get("name").equals(playlistName)) {
                        final Playlist playlistObject = new Playlist(playlistName);

                        final JSONArray songs = playlist.getJSONArray("songs");
                        for (int j = 0; j < songs.length(); j++) {
                            final JSONObject song = songs.getJSONObject(j);

                            final JSONArray artists = song.getJSONArray("artists");
                            final String[] artistArray = new String[artists.length()];
                            for (int k = 0; k < artists.length(); k++) {
                                artistArray[k] = artists.getString(k);
                            }

                            final Song songObject = new Song(song.getString("name"), artistArray);
                            playlistObject.addSong(songObject);
                        }
                        return playlistObject;
                    }

                }
            }
            else {
                throw new DataAccessException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DataAccessException(ex.getMessage());
        }
        return null;
    }

    @Override
    public void addSongToPlaylist(Playlist playlist, Song song) throws DataAccessException {
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
                final JSONArray playlistCollection = data.getJSONArray("playlist collection");

                for (int i = 0; i < playlistCollection.length(); i++) {
                    final JSONObject jsonPlaylist = playlistCollection.getJSONObject(i);
                    if (jsonPlaylist.get("name").equals(playlist.getName())) {

                        final JSONArray jsonSongs = jsonPlaylist.getJSONArray("songs");

                        final JSONObject jsonSong = new JSONObject();
                        jsonSong.put("name", song.getName());
                        jsonSong.put("artists", song.getArtists());

                        jsonSongs.put(jsonSong);
                    }
                }
                // Create updated request
                final JSONObject updatedUser = new JSONObject();
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
                    System.out.println("Song added to playlist successfully!");
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
    public void removeSongFromPlaylist(Playlist playlist, int songIndex) throws DataAccessException {
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
                final JSONArray playlistCollection = data.getJSONArray("playlist collection");

                for (int i = 0; i < playlistCollection.length(); i++) {
                    final JSONObject jsonPlaylist = playlistCollection.getJSONObject(i);
                    if (jsonPlaylist.get("name").equals(playlist.getName())) {

                        final JSONArray jsonSongs = jsonPlaylist.getJSONArray("songs");
                        jsonSongs.remove(songIndex);

                    }
                }
                // Create updated request
                final JSONObject updatedUser = new JSONObject();
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
                    System.out.println("Song removed from playlist successfully!");
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

    /**
     * Method to load the comments of a user's playlist ***BY CREATING a "playlist user"***.
     * @param playlistName the name of the playlist we are loading the comments from
     * @param username the name of the user of the playlist
     * @return a list containing the comments
     * @throws DataAccessException exception
     * @throws RuntimeException exception
     */
    public List<String> loadCommentsFromUser(String playlistName, String username) throws DataAccessException {
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
                final JSONObject data = userJSONObject.getJSONObject("info");
                final List<String> commentsList = new ArrayList<>();
                final String key = playlistName + "Comments";
                // Check whether the comments exist, then load comments or return an empty arraylist
                if (data.has(key)) {
                    final JSONArray jsonArray = data.getJSONArray(key);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        commentsList.add(jsonArray.getString(i));
                    }
                }
                // Return the comments
                return commentsList;
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
     * Saves comments to the User's playlist comment section.
     * @param playlistName name of playlist
     * @param username username of playlist owner
     * @param comment the comment we want to save
     * @return a list of comments
     * @throws DataAccessException exception
     */
    public List<String> saveCommentFromUser(String playlistName, String username, String comment) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // Get previous info so nothing is overridden
        final JSONObject info = getInfo(username);
        // POST METHOD to save note
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        // Get the User object with username using Get Method.
        final User thisPerson = getUser(username);
        requestBody.put(USERNAME, thisPerson.getUsername());
        requestBody.put(PASSWORD, thisPerson.getPassword());
        // Add the comment to the existing comments
        final List<String> currentComments = loadCommentsFromUser(playlistName, username);
        currentComments.add(comment);
        final String key = playlistName + "Comments";
        // Add the previous info along with the updated comments back into the info JSONObject
        info.put(key, currentComments);
        requestBody.put("info", info);
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
                return loadCommentsFromUser(playlistName, username);
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
     * Method to load a chat between two users.
     * @param sender the name of the user sending the message
     * @param reciever the name of user recieving the message
     * @return a list containing the chat messages
     * @throws DataAccessException exception
     * @throws RuntimeException exception
     */
    public List<String> loadChatBetweenUsers(String sender, String reciever) throws DataAccessException {
        final String key = getChatKeyBetweenUsers(sender, reciever);
        // Create the chat room if it doesn't exist
        if (!existsByName(key)) {
            final User chatRoom = new User(key, key);
            createUser(chatRoom);
        }
        // Make an API call to get the user object.
        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", key))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();
        try {
            final Response response = client.newCall(request).execute();

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONObject data = userJSONObject.getJSONObject("info");
                final List<String> chatList = new ArrayList<>();
                final String chatKey = "chat";
                // Check whether the chat exist, then load chats or return an empty arraylist
                if (data.has(chatKey)) {
                    final JSONArray jsonArray = data.getJSONArray(chatKey);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        chatList.add(jsonArray.getString(i));
                    }
                }
                // Return the comments
                return chatList;
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
     * Saves comments to the User's playlist comment section.
     * @param sender of message
     * @param reciever of message
     * @param chat chat we want to send
     * @return a list of chats
     * @throws DataAccessException exception
     */
    public List<String> saveChatBetweenUsers(String chat, String sender, String reciever) throws DataAccessException {
        // get chatroom key
        final String key = getChatKeyBetweenUsers(sender, reciever);
        // API call
        final OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        // POST METHOD to save note
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        final JSONObject extra = new JSONObject();
        // Get the User object with username using Get Method.
        requestBody.put(USERNAME, key);
        requestBody.put(PASSWORD, key);
        // Add the comment to the existing comments
        final List<String> currentChat = loadChatBetweenUsers(sender, reciever);
        currentChat.add(chat);
        final String chatKey = "chat";
        // Add the previous info along with the updated comments back into the info JSONObject
        extra.put(chatKey, currentChat);
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
                return loadChatBetweenUsers(sender, reciever);
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
     * Helper method for chat use_case that generates the Chat DB user/username.
     * @param sender the user sending the message
     * @param reciever the user recieving the message
     * @return a key that is the username and password of the chat DB user.
     */
    public String getChatKeyBetweenUsers(String sender, String reciever) {
        final String[] forSorting = {sender, reciever};
        Arrays.sort(forSorting);
        return forSorting[0] + "CHATBETWEEN" + forSorting[1];
    }

    /**
     * Helper method to get the info JSONObject so we can add information to it (So previous info isn't deleted).
     * @param username username of the person we want the info from
     * @return the JSONObject containing all their data
     * @throws DataAccessException exception
     * @throws RuntimeException exception
     */
    public JSONObject getInfo(String username) throws DataAccessException {
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
                return userJSONObject.getJSONObject("info");
            }
            else {
                throw new DataAccessException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

}
