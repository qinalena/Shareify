package use_case.user_profile;

import entity.User;
import use_case.note.NoteDataAccessInterface;
import use_case.playlist_collection.PlaylistCollectionOutputBoundary;

/**
 * The interactor for our User Profile.
 */
public class UserProfileInteractor implements UserProfileInputBoundary {

    private final NoteDataAccessInterface noteDataAccessInterface;
    private final UserProfileOutputBoundary userProfilePresenter;
    private final PlaylistCollectionOutputBoundary playlistCollectionPresenter;
    private final User user = new User("newUserName3", "password123");

    public UserProfileInteractor(NoteDataAccessInterface noteDataAccessInterface, UserProfileOutputBoundary userProfilePresenter) {
        this.noteDataAccessInterface = noteDataAccessInterface;
        this.userProfilePresenter = userProfilePresenter;
        this.playlistCollectionPresenter = playlistCollectionPresenter;

        // Example usage: Create a new user if needed
//        User newUser = new User("new_user_name_1", "password123");
//        executeCreateUser(newUser);
    }

    @Override
    public void switchToNoteView() {
        userProfilePresenter.switchToNoteView();
    }

    @Override
    public void switchToPlaylistCollectionView() {
        userProfilePresenter.switchToPlaylistCollectionView();
    }

    @Override
    public void switchToFriendsListView() {
        userProfilePresenter.switchToFriendsListView();
    }

    // Method to create a new user
//    public void executeCreateUser(User user) {
//        try {
//            DBNoteDataAccessObject.createUser(user);
//            userProfileOutputBoundary.prepareSuccessView("User created successfully");
//        } catch (DataAccessException ex) {
//            userProfileOutputBoundary.prepareFailView(ex.getMessage());
//        }
//    }
}
