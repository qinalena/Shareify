package app;

import data_access.DBNoteDataAccessObject;
import entity.User;
import spotify_api.SpotifyConnection;
import use_case.note.DataAccessException;
import use_case.note.NoteDataAccessInterface;

/**
 * An application where we can view and add to a note stored by a user.
 * <p>
 * This is a minimal example of using the password-protected user API from lab 5,
 * but demonstrating the endpoint allowing you to store an arbitrary JSON object.
 * This functionality could be used in any project where your team wants to persist
 * data which is then accessible across devices.</p>
 * <p>The code is intentionally somewhat incomplete to leave work to be done if your
 * team were to choose to work on a project which would require similar functionality.
 * For example, we have intentionally not created a full "Note" entity here, but
 * rather just represented a note as a string.
 * </p>
 * The ViewManager code has also been removed, since this minimal program only requires a single
 * view. Your team may wish to bring back the ViewManager or make your own implementation of supporting
 * switching between views depending on your project.
 */
public class MainNoteApplication {
    /**
     * The main entry point of the application.
     * <p>
     * The program will show you the note currently saved in the system.
     * You are able to edit it and then save it to the system. You can refresh
     * to update the note to reflect what was saved most recently. This
     * uses the API from lab, so there is one database storing the note,
     * which means that if anyone updates the note, that is what you will
     * see when you refresh.
     * </p> <p>
     * You can generalize the code to allow you to
     * specify which "user" to save the note for, which will allow your team
     * to store information specific to your team which is password-protected.
     * The username and password used in this application are currently for
     * user jonathan_calver2, but you can change that. As you did in lab 3,
     * you will likely want to store password information locally rather than
     * in your repo. Or you can require the user to enter their credentials
     * in your application; it just depends on what your program's main
     * functionality.
     * </p>
     * @param args commandline arguments are ignored
     */
    public static void main(String[] args) {

        // create the data access and inject it into our builder!
        final NoteDataAccessInterface noteDataAccess = new DBNoteDataAccessObject();

        // Connect to Spotify API
        final SpotifyConnection spotifyConnection = new SpotifyConnection();

        // Example call using Spotify API
        System.out.println(spotifyConnection.getSongArtist("Starships"));

        // Create Comment section for a playlist

        // AFTER CREATING A NEW USER HERE PUT IN YOUR CREDENTIALS IN NoteInteractor AND THEN COMMENT THIS BLOCK OUT
        // OTHERWISE IT WILL KEEP TRYING TO CREATE A NEW USER EACH TIME WITH THE SAME STUFF
        // Create a new user
//        User newUser = new User("newUserName3", "password123");
//
//        try {
//            // Create the user in the data storage
//            DBNoteDataAccessObject.createUser(newUser);
//            System.out.println("User created successfully!");
//
//            // Add a default note for the new user
//            String initialNote = "Username: " + newUser.getName();
//            initialNote += "\nPassword: " + newUser.getPassword();
//            initialNote += "\nFavourite artist: " + spotifyConnection.getSongArtist("Starships");
//
//            noteDataAccess.saveNote(newUser, initialNote);
//            System.out.println("Note added successfully for user: " + newUser.getName());
//
//        } catch (DataAccessException ex) {
//            System.err.println("Error creating user or saving note: " + ex.getMessage());
//        }

        final NoteAppBuilder builder = new NoteAppBuilder();
        builder.addNoteDAO(noteDataAccess)
                .addCommentView().build().setVisible(true);
    }

}
