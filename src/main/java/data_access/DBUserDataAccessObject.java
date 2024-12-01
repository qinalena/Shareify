package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
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
import use_case.playlist_user_story.playlist.PlaylistDataAccessInterface;
import use_case.playlist_user_story.search_song.SearchSongDataAccessInterface;
import use_case.chat.ChatDataAccessInterface;
import use_case.comment.CommentDataAccessInterface;
import use_case.user_profile_user_story.change_password.ChangePasswordUserDataAccessInterface;
import use_case.login_user_story.login.LoginUserDataAccessInterface;
import use_case.user_profile_user_story.logout.LogoutUserDataAccessInterface;
import use_case.login_user_story.signup.SignupUserDataAccessInterface;
import use_case.user_profile_user_story.note.NoteDataAccessInterface;

/**
 * The DAO for user data.
 */
public class DBUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface,
        ChangePasswordUserDataAccessInterface, LogoutUserDataAccessInterface, PlaylistCollectionDataAccessInterface,
        PlaylistDataAccessInterface, SearchSongDataAccessInterface, CommentDataAccessInterface,
        ChatDataAccessInterface, NoteDataAccessInterface {
    private static final int SUCCESS_CODE = 200;
    private static final int CREDENTIAL_ERROR = 401;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String INFO = "info";
    private static final String MESSAGE = "message";

    // Static field to track current logged-in user
    private static String currentUsername;


    public User get(String username) throws RuntimeException {
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

                return new User(name, password);
            }
            else {
                throw new RuntimeException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    // Sets the current logged-in username
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

                // Get current list of playlists or initialize an empty list
                JSONArray playlists = data.optJSONArray("playlists");
                if (playlists == null) {
                    playlists = new JSONArray();
                }

                // Add new playlist to the list
                playlists.put(playlistName);

                // Update info field with new playlist list
                data.put("playlists", playlists);

                // Create updated request
                JSONObject updatedUser = new JSONObject();
                updatedUser.put(USERNAME, username);
                updatedUser.put(PASSWORD, user.getPassword());
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
        final User thisPerson = get(username);
        requestBody.put(USERNAME, thisPerson.getName());
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

    public String getCurrentUsername() {
        return currentUsername;
    }

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
            return "";
        }
    }

}
