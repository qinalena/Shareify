package app;

import interface_adapter.note.NoteViewModel;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfilePresenter;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.note.NoteDataAccessInterface;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;
import view.NoteView;
import view.UserProfileView;

import javax.swing.*;

/**
 * Builder for the Shareify Application.
 */
public class UserProfileAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;

    private NoteDataAccessInterface noteDAO;

    private UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
    private UserProfileView userProfileView;

    private NoteViewModel noteViewModel;
    private NoteView noteView;

    private UserProfileInteractor userProfileInteractor;

    /**
     * Sets the NoteDAO to be used in this application.
     * @param noteDataAccess the DAO to use
     * @return this builder
     */
    public UserProfileAppBuilder addNoteDAO(NoteDataAccessInterface noteDataAccess) {
        noteDAO = noteDataAccess;
        return this;
    }

    public UserProfileAppBuilder addNoteView() {
        noteViewModel = new NoteViewModel();
        noteView = new NoteView(noteViewModel);
    }

    /**
     * Creates the objects for the User Profile Use Case and connects the UserProfileView to its
     * controller.
     * <p>This method must be called after addUserProfileView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addUserProfileView
     */
    public UserProfileAppBuilder addUserProfileUseCase() {
        final UserProfileOutputBoundary userProfileOutputBoundary = new UserProfilePresenter(userProfileViewModel);
        userProfileInteractor = new UserProfileInteractor(
                noteDAO, userProfileOutputBoundary);

        final UserProfileController controller = new UserProfileController(userProfileInteractor);
        if (userProfileView == null) {
            throw new RuntimeException("addUserProfileView must be called before addUserProfileUseCase");
        }
        userProfileView.setUserProfileController(controller);
        return this;
    }

    /**
     * Creates the UserProfileView and underlying UserProfileViewModel.
     * @return this builder
     */
    public UserProfileAppBuilder addUserProfileView() {
        userProfileView = new UserProfileView(userProfileViewModel);
        return this;
    }

    /**
     * Builds the application.
     * @return the JFrame for the application
     */
    public JFrame build() {
        final JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Shareify");
        frame.setSize(WIDTH, HEIGHT);

        frame.add(userProfileView);

        // refresh so that the note will be visible when we start the program
//        userProfileInteractor.executeRefresh();

        return frame;

    }
}
