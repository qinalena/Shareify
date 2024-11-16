package app;

import java.awt.*;

import javax.swing.*;

import interface_adapter.ViewManagerModel;
import interface_adapter.note.NoteController;
import interface_adapter.note.NotePresenter;
import interface_adapter.note.NoteViewModel;
import interface_adapter.playlist_collection.PlaylistCollectionController;
import interface_adapter.playlist_collection.PlaylistCollectionPresenter;
import interface_adapter.playlist_collection.PlaylistCollectionViewModel;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfilePresenter;
import interface_adapter.user_profile.UserProfileViewModel;
import use_case.note.NoteDataAccessInterface;
import use_case.note.NoteInputBoundary;
import use_case.note.NoteInteractor;
import use_case.note.NoteOutputBoundary;
import use_case.playlist_collection.PlaylistCollectionInteractor;
import use_case.playlist_collection.PlaylistCollectionOutputBoundary;
import use_case.user_profile.PlaylistCollectionInputBoundary;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;
import view.NoteView;
import view.PlaylistCollectionView;
import view.UserProfileView;
import view.ViewManager;

/**
 * Builder for the Shareify Application.
 */
public class UserProfileAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 400;

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private NoteDataAccessInterface noteDAO;

    private UserProfileViewModel userProfileViewModel;
    private UserProfileView userProfileView;

    private NoteViewModel noteViewModel;
    private NoteView noteView;

    private PlaylistCollectionViewModel playlistCollectionViewModel;
    private PlaylistCollectionView playlistCollectionView;

    // For refreshing the note before displaying the Note View
    private NoteInputBoundary noteInteractor;

    private PlaylistCollectionInputBoundary playlistCollectionInteractor;

    public UserProfileAppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Sets the NoteDAO to be used in this application.
     * @param noteDataAccess the DAO to use
     * @return this builder
     */
    public UserProfileAppBuilder addNoteDAO(NoteDataAccessInterface noteDataAccess) {
        noteDAO = noteDataAccess;
        return this;
    }

    /**
     * Creates the NoteView and underlying NoteViewModel.
     * @return this builder
     */
    public UserProfileAppBuilder addNoteView() {
        noteViewModel = new NoteViewModel();
        noteView = new NoteView(noteViewModel);
        cardPanel.add(noteView, noteView.getViewName());
        return this;
    }

    /**
     * Creates the UserProfileView and underlying UserProfileViewModel.
     * @return this builder
     */
    public UserProfileAppBuilder addUserProfileView() {
        userProfileViewModel = new UserProfileViewModel();
        userProfileView = new UserProfileView(userProfileViewModel);
        cardPanel.add(userProfileView, userProfileView.getViewName());
        return this;
    }

    /**
     * Adds the Playlist Collection View to the application.
     * @return this builder
     */
    public UserProfileAppBuilder addPlaylistCollectionView() {
        playlistCollectionViewModel = new PlaylistCollectionViewModel();
        playlistCollectionView = new PlaylistCollectionView(playlistCollectionViewModel);
        cardPanel.add(playlistCollectionView, playlistCollectionView.getViewName());
        return this;
    }

    /**
     * Creates the objects for the User Profile Use Case and connects the UserProfileView to its
     * controller.
     * <p>This method must be called after addUserProfileView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addUserProfileView
     */
    public UserProfileAppBuilder addUserProfileUseCase() {
        final UserProfileOutputBoundary userProfileOutputBoundary =
                new UserProfilePresenter(userProfileViewModel, noteViewModel, viewManagerModel);

        final PlaylistCollectionOutputBoundary playlistCollectionOutputBoundary = new PlaylistCollectionPresenter(
                playlistCollectionViewModel, viewManagerModel);

        final PlaylistCollectionInputBoundary userProfileInteractor = new UserProfileInteractor(
                noteDAO, userProfileOutputBoundary, playlistCollectionOutputBoundary);

        final UserProfileController userProfileController = new UserProfileController(userProfileInteractor);
        if (userProfileView == null) {
            throw new RuntimeException("addUserProfileView must be called before addUserProfileUseCase");
        }
        userProfileView.setUserProfileController(userProfileController);
        return this;
    }

    /**
     * Creates the objects for the Note Use Case and connects the NoteView to its
     * controller.
     * <p>This method must be called after addNoteView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addNoteView
     */
    public UserProfileAppBuilder addNoteUseCase() {
        final NoteOutputBoundary noteOutputBoundary = new NotePresenter(noteViewModel, viewManagerModel);
        noteInteractor = new NoteInteractor(
                noteDAO, noteOutputBoundary);

        final NoteController noteController = new NoteController(noteInteractor);
        if (noteView == null) {
            throw new RuntimeException("addNoteView must be called before addNoteUseCase");
        }
        noteView.setNoteController(noteController);
        return this;
    }

    /**
     * Adds the Playlist Collection Use Case to the application.
     * @return this builder
     * @throws RuntimeException if this method is called before addPlaylistCollectionView
     */
    public UserProfileAppBuilder addPlaylistCollectionUseCase() {
        final PlaylistCollectionOutputBoundary playlistCollectionOutputBoundary = new PlaylistCollectionPresenter(
                playlistCollectionViewModel, viewManagerModel);

        final PlaylistCollectionController playlistCollectionController = new PlaylistCollectionController(
                playlistCollectionInteractor);
        if (playlistCollectionView == null) {
            throw new RuntimeException("addPlaylistCollectionView must be called before addPlaylistCollectionUseCase");
        }
        playlistCollectionView.setPlaylistCollectionController(playlistCollectionController);
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

        frame.add(cardPanel);

        viewManagerModel.setState(userProfileView.getViewName());
        viewManagerModel.firePropertyChanged();

        // refresh so that the note will be visible when we start the program
        noteInteractor.executeRefresh();

        return frame;

    }
}
