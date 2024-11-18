package app;

import java.awt.*;

import javax.swing.*;

import data_access.DBNoteDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.UserFactory;
import entity.UserFactoryInter;
import interface_adapter.ViewManagerModel;
import interface_adapter.add_friend.AddFriendPresenter;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.login.LoginController;
import interface_adapter.login.LoginPresenter;
import interface_adapter.login.LoginViewModel;
import interface_adapter.note.NoteController;
import interface_adapter.note.NotePresenter;
import interface_adapter.note.NoteViewModel;
import interface_adapter.signup.SignupController;
import interface_adapter.signup.SignupPresenter;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.user_profile.UserProfileController;
import interface_adapter.user_profile.UserProfilePresenter;
import interface_adapter.user_profile.UserProfileViewModel;
import interface_adapter.friends_list.FriendsListController;
import interface_adapter.friends_list.FriendsListPresenter;
import interface_adapter.welcome.WelcomeViewModel;
import use_case.add_friend.AddFriendOutputBoundary;
import use_case.friends_list.FriendsListInputBoundary;
import use_case.friends_list.FriendsListOutputBoundary;
import use_case.friends_list.FriendsListInteractor;
import use_case.login.LoginInputBoundary;
import use_case.login.LoginInteractor;
import use_case.login.LoginOutputBoundary;
import use_case.note.NoteDataAccessInterface;
import use_case.note.NoteInputBoundary;
import use_case.note.NoteInteractor;
import use_case.note.NoteOutputBoundary;
import use_case.signup.SignupInputBoundary;
import use_case.signup.SignupInteractor;
import use_case.signup.SignupOutputBoundary;
import use_case.user_profile.UserProfileInputBoundary;
import use_case.user_profile.UserProfileInteractor;
import use_case.user_profile.UserProfileOutputBoundary;
import view.*;


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

    private LoginViewModel loginViewModel = new LoginViewModel();
    private LoginView loginView;

    private NoteDataAccessInterface noteDAO;

    private UserProfileViewModel userProfileViewModel;
    private UserProfileView userProfileView;

    private NoteViewModel noteViewModel;
    private NoteView noteView;

    private FriendsListViewModel friendsListViewModel;
    private FriendsListView friendsListView;
    private FriendsListController friendsListController;
    private FriendsListPresenter friendsListPresenter;
    private FriendsListInputBoundary friendsListInputBoundary;
    private FriendsListOutputBoundary friendsListOutputBoundary;
    private FriendsListInteractor friendsListInteractor;
    private DBNoteDataAccessObject dbNoteDataAccessObject = new DBNoteDataAccessObject();
    private interface_adapter.add_friend.AddFriendViewModel AddFriendViewModel = new interface_adapter.add_friend.AddFriendViewModel();
    private AddFriendOutputBoundary addFriendOutputBoundary = new AddFriendPresenter(AddFriendViewModel);

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
     * Creates the NoteView and underlying NoteViewModel.
     * @return this builder
     */
    public ShareifyAppBuilder addNoteView() {
        noteViewModel = new NoteViewModel();
        noteView = new NoteView(noteViewModel);
        cardPanel.add(noteView, noteView.getViewName());
        return this;
    }

    /**
     * Creates the UserProfileView and underlying UserProfileViewModel.
     * @return this builder
     */
    public ShareifyAppBuilder addUserProfileView() {
        userProfileViewModel = new UserProfileViewModel();
        userProfileView = new UserProfileView(userProfileViewModel);
        cardPanel.add(userProfileView, userProfileView.getViewName());
        return this;
    }

    public ShareifyAppBuilder addFriendsListView() {
        friendsListViewModel = new FriendsListViewModel();
        friendsListView = new FriendsListView(friendsListController, friendsListViewModel, dbNoteDataAccessObject, addFriendOutputBoundary);
        cardPanel.add(friendsListView, friendsListView.getViewName());
        return this;
    }

    /**
     * Creates the objects for the User Profile Use Case and connects the UserProfileView to its
     * controller.
     * <p>This method must be called after addUserProfileView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addUserProfileView
     */
    public ShareifyAppBuilder addUserProfileUseCase() {
        final UserProfileOutputBoundary userProfileOutputBoundary =
                new UserProfilePresenter(userProfileViewModel, noteViewModel, viewManagerModel);
        final UserProfileInputBoundary userProfileInteractor = new UserProfileInteractor(
                noteDAO, userProfileOutputBoundary);

        final UserProfileController userProfileController = new UserProfileController(userProfileInteractor);
        if (userProfileView == null) {
            throw new RuntimeException("addUserProfileView must be called before addUserProfileUseCase");
        }
        userProfileView.setUserProfileController(userProfileController);
        return this;
    }

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
     * Creates the objects for the Note Use Case and connects the NoteView to its
     * controller.
     * <p>This method must be called after addNoteView!</p>
     * @return this builder
     * @throws RuntimeException if this method is called before addNoteView
     */
    public ShareifyAppBuilder addNoteUseCase() {
        final NoteOutputBoundary noteOutputBoundary = new NotePresenter(noteViewModel,
                userProfileViewModel, viewManagerModel);
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
     * Adds the Signup View to the application.
     * @return this builder
     */
    public UserProfileAppBuilder addSignupView() {
        signupViewModel = new SignupViewModel();
        signupView = new SignupView(signupViewModel, viewManagerModel);
        cardPanel.add(signupView, signupView.getViewName());
        return this;
    }

    /**
     * Adds the Signup Use Case to the application.
     * @return this builder
     */
    public UserProfileAppBuilder addSignupUseCase() {
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
    public UserProfileAppBuilder addLoginUseCase() {
        userProfileViewModel = new UserProfileViewModel();
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                userProfileViewModel, loginViewModel);
        final LoginInputBoundary loginInteractor = new LoginInteractor(
                userDataAccessObject, loginOutputBoundary);

        final LoginController loginController = new LoginController(loginInteractor);
        loginView.setLoginController(loginController);
        return this;
    }

    /**
     * Adds the Login View to the application.
     * @return this builder
     */
    public UserProfileAppBuilder addLoginView() {
        loginViewModel = new LoginViewModel();
        loginView = new LoginView(loginViewModel,viewManagerModel);
        cardPanel.add(loginView, loginView.getViewName());
        return this;
    }

    /**
     * Adds the Welcome view to the application.
     * @return this builder
     */
    public UserProfileAppBuilder addWelcomeView() {
        welcomeViewModel = new WelcomeViewModel();
        welcomeView = new WelcomeView(welcomeViewModel,viewManagerModel);
        cardPanel.add(welcomeView, welcomeView.getViewName());
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

        // refresh so that the note will be visible when we start the program
        noteInteractor.executeRefresh();

        return frame;

    }
}
