package app;

import java.awt.*;

import javax.swing.*;

import data_access.*;
import entity.UserFactory;
import entity.UserFactoryInter;
import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.add_friend.*;
import interface_adapter.friends_list_user_story.friends_list.*;
import interface_adapter.login_user_story.login.*;
import interface_adapter.login_user_story.signup.*;
import interface_adapter.login_user_story.welcome.*;
import interface_adapter.playlist_collection_user_story.add_playlist.*;
import interface_adapter.playlist_collection_user_story.playlist_collection.*;
import interface_adapter.user_profile_user_story.change_password.ChangePasswordController;
import interface_adapter.user_profile_user_story.change_password.ChangePasswordPresenter;
import interface_adapter.user_profile_user_story.logout.LogoutController;
import interface_adapter.user_profile_user_story.logout.LogoutPresenter;
import interface_adapter.user_profile_user_story.note.*;
import interface_adapter.user_profile_user_story.user_profile.*;
import use_case.friends_list_user_story.add_friend.*;
import use_case.friends_list_user_story.friends_list.*;
import use_case.login_user_story.login.*;
import use_case.login_user_story.signup.*;
import use_case.playlist_collection_user_story.add_playlist.*;
import use_case.playlist_collection_user_story.playlist_collection.*;
import use_case.user_profile_user_story.change_password.ChangePasswordInputBoundary;
import use_case.user_profile_user_story.change_password.ChangePasswordInteractor;
import use_case.user_profile_user_story.change_password.ChangePasswordOutputBoundary;
import use_case.user_profile_user_story.change_password.ChangePasswordUserDataAccessInterface;
import use_case.user_profile_user_story.logout.LogoutInputBoundary;
import use_case.user_profile_user_story.logout.LogoutInteractor;
import use_case.user_profile_user_story.logout.LogoutOutputBoundary;
import use_case.user_profile_user_story.note.*;
import use_case.user_profile_user_story.user_profile.*;
import view.ViewManager;
import view.friends_list_user_story.*;
import view.login_user_story.*;
import view.playlist_collection_user_story.*;
import view.user_profile_user_story.*;

/**
 * Builder for the Shareify Application.
 */
public class ShareifyAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 600;

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private WelcomeViewModel welcomeViewModel;
    private WelcomeView welcomeView;

    private SignupView signupView;
    private SignupViewModel signupViewModel;
    private final UserFactoryInter userFactory = new UserFactory();

    private final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject(userFactory);
    private final LoggedInDataAccessObject loggedInDataAccessObject = new LoggedInDataAccessObject();

    private LoginViewModel loginViewModel = new LoginViewModel();
    private LoginView loginView;

    private NoteDataAccessInterface noteDAO;

    private UserProfileViewModel userProfileViewModel;
    private UserProfileView userProfileView;

    private NoteViewModel noteViewModel;
    private NoteView noteView;

    private ChangePasswordView changePasswordView;

    private PlaylistCollectionViewModel playlistCollectionViewModel;
    private PlaylistCollectionView playlistCollectionView;
    private PlaylistCollectionController playlistCollectionController;
    private PlaylistCollectionInteractor playlistCollectionInteractor;
    private AddPlaylistViewModel addPlaylistViewModel =
            new AddPlaylistViewModel();
    private AddPlaylistOutputBoundary addPlaylistOutputBoundary = new AddPlaylistPresenter(addPlaylistViewModel);

    private FriendsListViewModel friendsListViewModel;
    private FriendsListView friendsListView;
    private FriendsListController friendsListController;
    private FriendsListPresenter friendsListPresenter;
    private FriendsListInputBoundary friendsListInputBoundary;
    private FriendsListOutputBoundary friendsListOutputBoundary;
    private FriendsListInteractor friendsListInteractor;
    private DBNoteDataAccessObject dbNoteDataAccessObject = new DBNoteDataAccessObject();
    private AddFriendViewModel addFriendViewModel =
            new AddFriendViewModel();
    private AddFriendOutputBoundary addFriendOutputBoundary = new AddFriendPresenter(addFriendViewModel);

    // For refreshing the note before displaying the Note View
    private NoteInputBoundary noteInteractor;

    public ShareifyAppBuilder() {
        cardPanel.setLayout(cardLayout);
    }

    /**
     * Sets the NoteDAO to be used in this application.
     * @param noteDataAccess the DAO to use
     * @return this builder
     */
    public ShareifyAppBuilder addNoteDAO(NoteDataAccessInterface noteDataAccess) {
        noteDAO = noteDataAccess;
        return this;
    }

    /**
     * Adds the UserProfileView to the application.
     * @return this builder
     * @throws RuntimeException if this method is called before addLoginUseCase
     */
    public ShareifyAppBuilder addUserProfileView() {
        userProfileView = new UserProfileView(userProfileViewModel, userDataAccessObject, dbNoteDataAccessObject);
        cardPanel.add(userProfileView, userProfileView.getViewName());

        if (loginView == null) {
            throw new RuntimeException("addLogInView must be called before addUserProfileView");
        }
        return this;
    }


    /**
     * Adds the Playlist Collection View to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addPlaylistCollectionView() {
        playlistCollectionViewModel = new PlaylistCollectionViewModel();

        playlistCollectionView = new PlaylistCollectionView(playlistCollectionController, playlistCollectionViewModel,
                userDataAccessObject, addPlaylistOutputBoundary);
        cardPanel.add(playlistCollectionView, playlistCollectionView.getViewName());
        return this;
    }

    /**
     * Adds the Friends List View to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addFriendsListView() {
        friendsListViewModel = new FriendsListViewModel();
        friendsListView = new FriendsListView(friendsListController, friendsListViewModel, dbNoteDataAccessObject, addFriendOutputBoundary);
        cardPanel.add(friendsListView, friendsListView.getViewName());
        return this;
    }

    /**
     * Adds the UserProfile Use Case to the application.
     * <p>This method must be called after addUserProfileView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addUserProfileView
     */
    public ShareifyAppBuilder addUserProfileUseCase() {
        noteViewModel = new NoteViewModel();
        final UserProfileOutputBoundary userProfileOutputBoundary =
                new UserProfilePresenter(userProfileViewModel, noteViewModel, viewManagerModel);
        final UserProfileInputBoundary userProfileInteractor = new UserProfileInteractor(
                loggedInDataAccessObject, userProfileOutputBoundary);

        final UserProfileController userProfileController = new UserProfileController(userProfileInteractor);
        if (userProfileView == null) {
            throw new RuntimeException("addUserProfileView must be called before addUserProfileUseCase");
        }
        userProfileView.setUserProfileController(userProfileController);
        return this;
    }

    /**
     * Adds the NoteView to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addNoteView() {
        noteView = new NoteView(noteViewModel,dbNoteDataAccessObject, userDataAccessObject);
        cardPanel.add(noteView, noteView.getViewName());
        return this;
    }

    /**
     * Creates the objects for the Note Use Case and connects the NoteView to its
     * controller.
     * <p>This method must be called after addNoteView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addNoteView
     */
    public ShareifyAppBuilder addNoteUseCase() {
        final NoteOutputBoundary noteOutputBoundary = new NotePresenter(noteViewModel,
                userProfileViewModel, viewManagerModel);
        noteInteractor = new NoteInteractor(userDataAccessObject,
                noteDAO, noteOutputBoundary);

        final NoteController noteController = new NoteController(noteInteractor);
        if (noteView == null) {
            throw new RuntimeException("addNoteView must be called before addNoteUseCase");
        }
        noteView.setNoteController(noteController);
        return this;
    }

    public ShareifyAppBuilder addChangePasswordView() {
        changePasswordView = new ChangePasswordView(userProfileViewModel);
        cardPanel.add(changePasswordView, changePasswordView.getViewName());
        return this;
    }

    public ShareifyAppBuilder addChangePasswordUseCase(){
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(userProfileViewModel, viewManagerModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor((ChangePasswordUserDataAccessInterface) userDataAccessObject, changePasswordOutputBoundary, userFactory);

        final ChangePasswordController changePasswordController =
                new ChangePasswordController(changePasswordInteractor);
        changePasswordView.setChangePasswordController(changePasswordController);
        return this;
    }


    /**
     * Adds the Friends List Use Case to the application.
     * @return this builder
     * @throws RuntimeException if this method is called before addFriendsListView
     */
    public ShareifyAppBuilder addFriendsListUseCase() {
        if (friendsListView == null) {
            throw new RuntimeException("addFriendsListView must be called before addFriendsListUseCase");
        }

        // Instantiate the output boundary (presenter) and input boundary (interactor)
        friendsListOutputBoundary = new FriendsListPresenter(friendsListViewModel);
        friendsListInteractor = new FriendsListInteractor(friendsListOutputBoundary);

        // Create the controller and connect it to the interactor
        friendsListController = new FriendsListController(friendsListInteractor);

        // Link the controller to the view
        friendsListView.setFriendsListController(friendsListController);

        return this;
    }

    /**
     * Adds the Playlist Collection Use Case to the application.
     * @return this builder
     * @throws RuntimeException if this method is called before addPlaylistCollectionView
     */
    public ShareifyAppBuilder addPlaylistCollectionUseCase() {
        // Instantiate the output boundary/presenter
        final PlaylistCollectionOutputBoundary playlistCollectionOutputBoundary =
                new PlaylistCollectionPresenter(playlistCollectionViewModel, viewManagerModel);

        // Instantiate the input boundary/interactor
        playlistCollectionInteractor =
                new PlaylistCollectionInteractor(playlistCollectionOutputBoundary);

        // Creating controller + connect to interactor
        playlistCollectionController = new PlaylistCollectionController(playlistCollectionInteractor);
        if (playlistCollectionView == null) {
            throw new RuntimeException("addPlaylistCollectionView must be called before addPlaylistCollectionUseCase");
        }

        // Link controller to the view
        playlistCollectionView.setPlaylistCollectionController(playlistCollectionController);
        return this;
    }


    /**
     * Adds the Signup View to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel, viewManagerModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addSignupUseCase() {
        final SignupOutputBoundary signupOutputBoundary = new SignupPresenter(
                signupViewModel, loginViewModel, viewManagerModel);
        final SignupInputBoundary userSignupInteractor = new SignupInteractor(
                userDataAccessObject, signupOutputBoundary, userFactory);

        final SignupController controller = new SignupController(userSignupInteractor);
        signupView.setSignupController(controller);
        return this;
    }

    /**
     * Adds the Login Use Case to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addLoginUseCase() {
        userProfileViewModel = new UserProfileViewModel();
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                userProfileViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loggedInDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel, viewManagerModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Welcome view to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addWelcomeView() {
        welcomeViewModel = new WelcomeViewModel();
        welcomeView = new WelcomeView(welcomeViewModel, viewManagerModel);
        cardPanel.add(welcomeView, welcomeView.getViewName());
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                userProfileViewModel, loginViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        userProfileView.setLogoutController(logoutController);
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

        viewManagerModel.setState(welcomeView.getViewName());
        viewManagerModel.firePropertyChanged();


        return frame;

    }
}