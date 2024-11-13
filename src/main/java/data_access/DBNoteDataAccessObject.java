package data_access;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import entity.User;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.note.DataAccessException;
import use_case.note.NoteDataAccessInterface;

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

        // POST METHOD to save note
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());
        final JSONObject extra = new JSONObject();
        extra.put("note", note);
        requestBody.put("info", extra);

        System.out.println(requestBody);
        // The reason this doesn't work is because saveNote is only happening when you create a new user
        // we are not saving any new notes so theres nothing else to save so these tests wont work

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

                // Print the info list using getter
                System.out.println("Info List:");
                System.out.println(user.getInfo());

                System.out.println(data.getString("test"));

                return data.getString("note");
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

        // Add the 'info' property to the request body (you can modify this to include the actual info)
        JSONObject userInfo = new JSONObject();
        // If you want to save a list, convert it into a JSON array
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
            } else {
                // Throw error if status is not 200 (success)
                throw new DataAccessException(responseBody.getString(MESSAGE));
            }
        } catch (IOException | JSONException ex) {
            throw new DataAccessException(ex.getMessage());
        }
    }

    public String getUserByUsername(String username) throws DataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Create the request to search for the user
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader("Content-Type", CONTENT_TYPE_JSON)
                .build();

        try {
            // Execute the request
            final Response response = client.newCall(request).execute();

            // Parse the response
            final JSONObject responseBody = new JSONObject(response.body().string());

            // Check if the response indicates the user was found
            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                // User found, return the username (or any user data)
                return responseBody.getJSONObject("user").getString(USERNAME);
            } else {
                // User not found, raise an exception
                throw new DataAccessException("User not found in the database.");
            }

        } catch (IOException | JSONException ex) {
            // Handle errors (network issues, parsing issues, etc.)
            throw new DataAccessException("Error occurred while searching for the user: " + ex.getMessage());
        }
    }

}
