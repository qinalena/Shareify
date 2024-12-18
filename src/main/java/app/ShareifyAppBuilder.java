package app;

import java.awt.*;

import javax.swing.*;

import data_access.*;
import entity.UserFactory;
import entity.UserFactoryInter;
import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ChatController;
import interface_adapter.chat.ChatPresenter;
import interface_adapter.chat.ChatViewModel;
import interface_adapter.comment.CommentController;
import interface_adapter.comment.CommentPresenter;
import interface_adapter.comment.CommentViewModel;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileController;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfilePresenter;
import interface_adapter.friends_list_user_story.friend_profile.FriendProfileViewModel;
import interface_adapter.friends_list_user_story.friend_profile_friends_list.*;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsController;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsPresenter;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsViewModel;
import interface_adapter.friends_list_user_story.add_friend.*;
import interface_adapter.friends_list_user_story.friend_playlist.FriendPlaylistController;
import interface_adapter.friends_list_user_story.friend_playlist.FriendPlaylistPresenter;
import interface_adapter.friends_list_user_story.friend_playlist.FriendPlaylistViewModel;
import interface_adapter.friends_list_user_story.friends_list.*;
import interface_adapter.login_user_story.login.*;
import interface_adapter.login_user_story.signup.*;
import interface_adapter.login_user_story.welcome.*;
import interface_adapter.playlist_collection_user_story.add_playlist.*;
import interface_adapter.playlist_collection_user_story.playlist_collection.*;
import interface_adapter.playlist_user_story.playlist.*;
import interface_adapter.playlist_user_story.search_song.*;
import interface_adapter.user_profile_user_story.note.*;
import interface_adapter.user_profile_user_story.user_profile.*;
import use_case.chat.ChatInputBoundary;
import use_case.chat.ChatInteractor;
import use_case.chat.ChatOutputBoundary;
import use_case.comment.CommentInputBoundary;
import use_case.comment.CommentInteractor;
import use_case.comment.CommentOutputBoundary;
import use_case.friends_list_user_story.friend_profile.FriendProfileInteractor;
import use_case.friends_list_user_story.friend_profile_friends_list.FriendProfileFriendsListInteractor;
import use_case.friends_list_user_story.friend_profile.FriendProfileOutputBoundary;
import interface_adapter.user_profile_user_story.change_password.*;
import use_case.friends_list_user_story.friend_profile_friends_list.FriendProfileFriendsListOutputBoundary;
import use_case.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsInteractor;
import use_case.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsOutputBoundary;
import interface_adapter.user_profile_user_story.logout.*;

import use_case.playlist_user_story.search_song.SpotifyDataAccessInterface;
import use_case.friends_list_user_story.add_friend.*;
import use_case.friends_list_user_story.friend_playlist.FriendPlaylistInputBoundary;
import use_case.friends_list_user_story.friend_playlist.FriendPlaylistInteractor;
import use_case.friends_list_user_story.friend_playlist.FriendPlaylistOutputBoundary;
import use_case.friends_list_user_story.friends_list.*;
import use_case.login_user_story.login.*;
import use_case.login_user_story.signup.*;
import use_case.playlist_collection_user_story.add_playlist.*;
import use_case.playlist_collection_user_story.playlist_collection.*;
import use_case.playlist_user_story.playlist.*;
import use_case.playlist_user_story.search_song.*;
import use_case.user_profile_user_story.note.*;
import use_case.user_profile_user_story.user_profile.*;
import view.interact_with_friends_user_story.ChatView;
import view.ViewManager;
import view.friends_list_user_story.*;
import use_case.user_profile_user_story.change_password.*;
import view.friends_list_user_story.FriendProfilePlaylistsView;
import view.interact_with_friends_user_story.CommentView;
import view.friends_list_user_story.FriendView;

import use_case.user_profile_user_story.logout.*;


import view.login_user_story.*;
import view.playlist_collection_user_story.*;
import view.playlist_user_story.*;
import view.user_profile_user_story.*;

/**
 * Builder for the Shareify Application.
 */
public class ShareifyAppBuilder {
    public static final int HEIGHT = 300;
    public static final int WIDTH = 600;

    private final JPanel cardPanel = new JPanel();
    private final CardLayout cardLayout = new CardLayout();

    private final UserFactoryInter userFactory = new UserFactory();

    private NoteDataAccessInterface noteDAO;
    private SpotifyDataAccessInterface spotifyDAO;
    private final DBUserDataAccessObject userDataAccessObject = new DBUserDataAccessObject();
    private final DBPlaylistDataAccessObject dbPlaylistDataAccessObject = new DBPlaylistDataAccessObject();

    private final ViewManagerModel viewManagerModel = new ViewManagerModel();
    private final ViewManager viewManager = new ViewManager(cardPanel, cardLayout, viewManagerModel);

    private WelcomeViewModel welcomeViewModel;
    private WelcomeView welcomeView;

    private SignupView signupView;
    private SignupViewModel signupViewModel;

    private LoginViewModel loginViewModel = new LoginViewModel();
    private LoginView loginView;

    private UserProfileViewModel userProfileViewModel;
    private UserProfileView userProfileView;
    private ChangePasswordView changePasswordView;

    private FriendsListViewModel friendsListViewModel;
    private FriendsListView friendsListView;

    private NoteViewModel noteViewModel;
    private NoteView noteView;

    private AddFriendView addFriendView;
    private AddFriendViewModel addFriendViewModel;

    private ChatViewModel chatViewModel;
    private ChatView chatView;

    private CommentViewModel commentViewModel;
    private CommentView commentView;

    private FriendProfilePlaylistsView friendProfilePlaylistsView;
    private FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel;

    private FriendProfileFriendsListView friendProfileFriendsListView;
    private FriendProfileFriendsListViewModel friendProfileFriendsListViewModel;

    private PlaylistViewModel playlistViewModel;
    private PlaylistView playlistView;

    private FriendPlaylistViewModel friendPlaylistViewModel;
    private FriendPlaylistView friendPlaylistView;

    private SearchSongViewModel searchSongViewModel;
    private SearchSongView searchSongView;

    private PlaylistCollectionViewModel playlistCollectionViewModel;
    private PlaylistCollectionView playlistCollectionView;
    private PlaylistCollectionController playlistCollectionController;
    private PlaylistCollectionInteractor playlistCollectionInteractor;
    private AddPlaylistViewModel addPlaylistViewModel;
    private AddPlaylistView addPlaylistView;
    private AddPlaylistInteractor addPlaylistInteractor;
    private PlaylistCollectionOutputBoundary playlistCollectionOutputBoundary;
    private AddPlaylistOutputBoundary addPlaylistOutputBoundary;

    private FriendProfileViewModel friendProfileViewModel;
    private FriendView friendProfileView;
    private DBFriendDataAccessObject dbFriendDataAccessObject = new DBFriendDataAccessObject();

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
     * Sets the SpotifyDAO to be used in this application.
     * @param spotifyConnection the DAO to use
     * @return this builder
     */
    public ShareifyAppBuilder addSpotifyDAO(SpotifyDataAccessInterface spotifyConnection) {
        spotifyDAO = spotifyConnection;
        return this;
    }

    /**
     * Adds the UserProfileView to the application.
     * @return this builder
     * @throws RuntimeException if this method is called before addLoginUseCase
     */
    public ShareifyAppBuilder addUserProfileView() {
        userProfileViewModel = new UserProfileViewModel();
        userProfileView = new UserProfileView(userProfileViewModel);
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
        addPlaylistOutputBoundary = new AddPlaylistPresenter(addPlaylistViewModel,
                viewManagerModel, playlistCollectionViewModel);
        playlistCollectionViewModel = new PlaylistCollectionViewModel();
        playlistCollectionView = new PlaylistCollectionView(playlistCollectionViewModel, dbPlaylistDataAccessObject);
        cardPanel.add(playlistCollectionView, playlistCollectionView.getViewName());
        return this;
    }

    /**
     * Adds the Playlist View to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addPlaylistView() {
        playlistViewModel = new PlaylistViewModel();
        playlistView = new PlaylistView(playlistViewModel);
        cardPanel.add(playlistView, playlistView.getViewName());
        return this;
    }

    /**
     * Adds the Search Track View to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addSearchTrackView() {
        searchSongViewModel = new SearchSongViewModel();
        searchSongView = new SearchSongView(searchSongViewModel);
        cardPanel.add(searchSongView, searchSongView.getViewName());
        return this;
    }

    /**
     * Adds the add playlist view to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addAddPlaylistView() {
        if (playlistCollectionController == null) {
            addPlaylistCollectionUseCase();
        }
        addPlaylistViewModel = new AddPlaylistViewModel();
        addPlaylistView = new AddPlaylistView(new DefaultListModel<>(),
                addPlaylistViewModel);
        cardPanel.add(addPlaylistView, addPlaylistViewModel.getViewName());
        return this;
    }

    /**
     * Adds the Friends List View to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addFriendsListView() {
        friendsListViewModel = new FriendsListViewModel();
        friendsListView = new FriendsListView(friendsListViewModel);
        cardPanel.add(friendsListView, friendsListView.getViewName());
        return this;
    }

    /**
     * Adds the UserProfile Use Case to the application.
     * < p>This method must be called after addUserProfileView!< /p>
     * @return this builder
     * @throws RuntimeException if this method is called before addUserProfileView
     */
    public ShareifyAppBuilder addUserProfileUseCase() {
        final UserProfileOutputBoundary userProfileOutputBoundary =
                new UserProfilePresenter(userProfileViewModel, noteViewModel, playlistCollectionViewModel,
                        friendsListViewModel, viewManagerModel);
        final UserProfileInputBoundary userProfileInteractor = new UserProfileInteractor(
                userProfileOutputBoundary);

        final UserProfileController userProfileController = new UserProfileController(userProfileInteractor);
        if (userProfileView == null) {
            throw new RuntimeException("addUserProfileView must be called before addUserProfileUseCase");
        }
        userProfileView.setUserProfileController(userProfileController);
        return this;
    }

    /**
     * Adds the Playlist Collection Use Case to the application.
     * @return this builder
     * @throws RuntimeException if this method is called before addPlaylistCollectionView
     */
    public ShareifyAppBuilder addPlaylistCollectionUseCase() {
        // Instantiate the output boundary/presenter
        playlistCollectionOutputBoundary =
                new PlaylistCollectionPresenter(playlistCollectionViewModel, addPlaylistViewModel,
                        playlistViewModel, userProfileViewModel, viewManagerModel);

        // Instantiate the input boundary/interactor
        playlistCollectionInteractor =
                new PlaylistCollectionInteractor(dbPlaylistDataAccessObject, userDataAccessObject,
                        playlistCollectionOutputBoundary);

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
     * Adds the friend profile use case to the application.
     * @return this builder
     * @throws RuntimeException for runtime exceptions
     */
    public ShareifyAppBuilder addFriendProfileUseCase() {
        final FriendProfileOutputBoundary friendProfileOutputBoundary = new FriendProfilePresenter(
                friendProfileViewModel, viewManagerModel, noteViewModel, friendProfilePlaylistsViewModel,
                friendProfileFriendsListViewModel, chatViewModel);
        final FriendProfileInteractor friendProfileInteractor = new FriendProfileInteractor(friendProfileOutputBoundary);

        final FriendProfileController friendProfileController = new FriendProfileController(friendProfileInteractor);
        if (friendProfileView == null) {
            throw new RuntimeException("addFriendProfileView must be called before addFriendProfileUseCase");
        }
        friendProfileView.setFriendProfileController(friendProfileController);
        return this;
    }

    /**
     * Adds playlist use case to application.
     * @return this builder
     */
    public ShareifyAppBuilder addAddPlaylistUseCase() {
        addPlaylistOutputBoundary = new AddPlaylistPresenter(addPlaylistViewModel,
                viewManagerModel, playlistCollectionViewModel);
        addPlaylistInteractor = new AddPlaylistInteractor(addPlaylistOutputBoundary);

        final AddPlaylistController addPlaylistController = new AddPlaylistController(addPlaylistInteractor);
        playlistCollectionController = new PlaylistCollectionController(playlistCollectionInteractor);
        if (addPlaylistView == null) {
            throw new RuntimeException("addPlaylistView must be called before addAddPlaylistUseCase");
        }
        addPlaylistView.setAddPlaylistController(addPlaylistController);
        addPlaylistView.setPlaylistCollectionController(playlistCollectionController);
        return this;
    }

    /**
     * Adds the Playlist Use Case to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addPlaylistUseCase() {
        final PlaylistOutputBoundary playlistOutputBoundary =
                new PlaylistPresenter(playlistViewModel, playlistCollectionViewModel, searchSongViewModel,
                        viewManagerModel);
        final PlaylistInputBoundary playlistInteractor = new PlaylistInteractor(userDataAccessObject,
                playlistOutputBoundary);

        final PlaylistController playlistController = new PlaylistController(playlistInteractor);
        playlistView.setPlaylistController(playlistController);

        return this;
    }

    /**
     * Adds the Search Track Use Case to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addSearchTrackUseCase() {
        final SearchSongOutputBoundary searchSongOutputBoundary =
                new SearchSongPresenter(searchSongViewModel, playlistViewModel, viewManagerModel);
        final SearchSongInputBoundary searchTrackInteractor =
                new SearchSongInteractor(spotifyDAO, userDataAccessObject, searchSongOutputBoundary);

        final SearchSongController searchTrackController = new SearchSongController(searchTrackInteractor);
        searchSongView.setSearchSongController(searchTrackController);

        return this;
    }

    /**
     * Adds the NoteView to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addNoteView() {
        noteViewModel = new NoteViewModel();
        noteView = new NoteView(noteViewModel);
        cardPanel.add(noteView, noteView.getViewName());
        return this;
    }

    /**
     * Creates the objects for the Note Use Case and connects the NoteView to its
     * controller.
     * < p>
     *     This method must be called after addNoteView!
     * < /p>
     * @return this builder
     * @throws RuntimeException if this method is called before addNoteView
     */
    public ShareifyAppBuilder addNoteUseCase() {
        final NoteOutputBoundary noteOutputBoundary = new NotePresenter(noteViewModel,
                userProfileViewModel, viewManagerModel);
        final NoteInputBoundary noteInteractor = new NoteInteractor(noteDAO, noteOutputBoundary);

        final NoteController noteController = new NoteController(noteInteractor);
        if (noteView == null) {
            throw new RuntimeException("addNoteView must be called before addNoteUseCase");
        }
        noteView.setNoteController(noteController);
        return this;
    }

    /**
     * Adds Change Password view to application.
     * @return this builder
     */
    public ShareifyAppBuilder addChangePasswordView() {
        changePasswordView = new ChangePasswordView(userProfileViewModel);
        cardPanel.add(changePasswordView, changePasswordView.getViewName());
        return this;
    }

    /**
     * Adds Change Password use case to application.
     * @return this builder
     */
    public ShareifyAppBuilder addChangePasswordUseCase() {
        final ChangePasswordOutputBoundary changePasswordOutputBoundary =
                new ChangePasswordPresenter(userProfileViewModel, viewManagerModel);

        final ChangePasswordInputBoundary changePasswordInteractor =
                new ChangePasswordInteractor(userDataAccessObject, changePasswordOutputBoundary);

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
        final FriendsListOutputBoundary friendsListOutputBoundary = new FriendsListPresenter(friendsListViewModel,
                viewManagerModel, addFriendViewModel, friendProfileViewModel, userProfileViewModel);
        final FriendsListInteractor friendsListInteractor = new FriendsListInteractor(friendsListOutputBoundary,
                dbFriendDataAccessObject);

        // Create the controller and connect it to the interactor
        final FriendsListController friendsListController = new FriendsListController(friendsListInteractor);

        // Link the controller to the view
        friendsListView.setFriendsListController(friendsListController);

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
        final LoginOutputBoundary loginOutputBoundary = new LoginPresenter(viewManagerModel,
                userProfileViewModel, loginViewModel, friendsListViewModel, addFriendViewModel,
                playlistCollectionViewModel, addPlaylistViewModel, chatViewModel, commentViewModel);
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
     * Adds the Friend profile view to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addFriendProfileView() {
        friendProfileViewModel = new FriendProfileViewModel();
        friendProfileView = new FriendView(friendProfileViewModel);
        cardPanel.add(friendProfileView, friendProfileViewModel.getViewName());
        return this;
    }

    /**
     * Adds the friend profile use case to the application.
     * @return this builder
     * @throws RuntimeException for idk
     */
    public ShareifyAppBuilder addAddFriendUseCase() {
        final AddFriendOutputBoundary addFriendOutPutBoundary = new AddFriendPresenter(addFriendViewModel,
                viewManagerModel, friendsListViewModel);
        final AddFriendInteractor addFriendInteractor = new AddFriendInteractor(dbFriendDataAccessObject,
                addFriendOutPutBoundary);
        final AddFriendController addFriendController = new AddFriendController(addFriendInteractor);
        if (addFriendView == null) {
            throw new RuntimeException("addFriendProfileView must be called before addFriendProfileUseCase");
        }
        addFriendView.setAddFriendController(addFriendController);
        return this;
    }

    /**
     * Adds the add friend view to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addAddFriendView() {
        addFriendViewModel = new AddFriendViewModel();
        addFriendView = new AddFriendView(addFriendViewModel);
        cardPanel.add(addFriendView, addFriendViewModel.getViewName());
        return this;
    }

    /**
     * Adds the friend profile playlists collection use case to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addFriendProfilePlaylistUseCase() {
        // Instantiate the output boundary/presenter
        final FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsOutputBoundary =
                new FriendProfilePlaylistsPresenter(friendProfilePlaylistsViewModel,
                        viewManagerModel, friendProfileViewModel, friendPlaylistViewModel);

        // Instantiate the input boundary/interactor
        final FriendProfilePlaylistsInteractor friendProfilePlaylistsInteractor =
                new FriendProfilePlaylistsInteractor(friendProfilePlaylistsOutputBoundary,
                        friendProfilePlaylistsViewModel, userDataAccessObject);

        // Creating controller + connect to interactor
        final FriendProfilePlaylistsController friendProfilePlaylistsController = new
                FriendProfilePlaylistsController(friendProfilePlaylistsInteractor);
        if (friendProfilePlaylistsView == null) {
            throw new RuntimeException("addPlaylistCollectionView must be called before addPlaylistCollectionUseCase");
        }

        // Link controller to the view
        friendProfilePlaylistsView.setPlaylistCollectionController(friendProfilePlaylistsController);
        return this;
    }

    /**
     * Adds the friend profile playlist colection view to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addFriendProfilePlaylistView() {
        friendProfilePlaylistsViewModel = new FriendProfilePlaylistsViewModel();
        friendProfilePlaylistsView = new FriendProfilePlaylistsView(friendProfilePlaylistsViewModel,
                dbPlaylistDataAccessObject);
        cardPanel.add(friendProfilePlaylistsView, friendProfilePlaylistsViewModel.getViewName());
        return this;
    }

    /**
     * Adds the viewing friend's friend list use case.
     * @return this builder
     */
    public ShareifyAppBuilder addFriendProfileFriendsListUseCase() {
        if (friendProfileFriendsListView == null) {
            throw new RuntimeException("addFriendsListView must be called before addFriendsListUseCase");
        }
        // Instantiate the output boundary (presenter) and input boundary (interactor)
        final FriendProfileFriendsListOutputBoundary friendProfileFriendsListOutputBoundary =
                new FriendProfileFriendsListPresenter(friendProfileViewModel,
                viewManagerModel, friendProfileFriendsListViewModel);
        final FriendProfileFriendsListInteractor friendProfileFriendsListInteractor =
                new FriendProfileFriendsListInteractor(friendProfileFriendsListOutputBoundary, dbFriendDataAccessObject);
        // Create the controller and connect it to the interactor
        final FriendProfileFriendsListController friendProfileFriendsListController = new
                FriendProfileFriendsListController(friendProfileFriendsListInteractor);

        // Link the controller to the view
        friendProfileFriendsListView.setFriendsListController(friendProfileFriendsListController);

        return this;
    }

    /**
     * Shows a friend's Friends list view.
     * @return this builder
     */
    public ShareifyAppBuilder addfriendProfileFriendsListView() {
        friendProfileFriendsListViewModel = new FriendProfileFriendsListViewModel();
        friendProfileFriendsListView = new FriendProfileFriendsListView(friendProfileFriendsListViewModel);
        cardPanel.add(friendProfileFriendsListView, friendProfileFriendsListView.getViewName());
        return this;
    }

    /**
     * Adds the Logout Use Case to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addLogoutUseCase() {
        final LogoutOutputBoundary logoutOutputBoundary = new LogoutPresenter(viewManagerModel,
                userProfileViewModel, loginViewModel, signupViewModel);

        final LogoutInputBoundary logoutInteractor =
                new LogoutInteractor(userDataAccessObject, logoutOutputBoundary);

        final LogoutController logoutController = new LogoutController(logoutInteractor);
        userProfileView.setLogoutController(logoutController);
        return this;
    }

    /**
     * Adds the Friend Playlist View to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addFriendPlaylistView() {
        friendPlaylistViewModel = new FriendPlaylistViewModel();
        friendPlaylistView = new FriendPlaylistView(friendPlaylistViewModel);
        cardPanel.add(friendPlaylistView, friendPlaylistView.getViewName());
        return this;
    }

    /**
     * Adds the Friend Playlist Use Case to the application.
     * @return this builder
     */
    public ShareifyAppBuilder addFriendPlaylistUseCase() {
        final FriendPlaylistOutputBoundary friendPlaylistOutputBoundary =
                new FriendPlaylistPresenter(friendPlaylistViewModel, viewManagerModel,
                        friendProfilePlaylistsViewModel, commentViewModel);
        final FriendPlaylistInputBoundary playlistInteractor = new FriendPlaylistInteractor(
                friendPlaylistOutputBoundary);

        final FriendPlaylistController playlistController = new FriendPlaylistController(playlistInteractor);
        friendPlaylistView.setPlaylistController(playlistController);

        return this;
    }

    /**
     * Adds the Chat View.
     * @return this builder
     */
    public ShareifyAppBuilder addChatView() {
        chatViewModel = new ChatViewModel();
        chatView = new ChatView(chatViewModel);
        cardPanel.add(chatView, chatView.getViewName());
        return this;
    }

    /**
     * Adds the chat Use Case.
     * @return this builder.
     */
    public ShareifyAppBuilder addChatUseCase() {
        final ChatOutputBoundary chatOutputBoundary = new ChatPresenter(chatViewModel,
                viewManagerModel, friendProfileViewModel);
        final ChatInputBoundary chatInteractor = new ChatInteractor(userDataAccessObject, chatOutputBoundary);

        final ChatController chatController = new ChatController(chatInteractor);
        chatView.setChatController(chatController);
        return this;

    }

    /**
     * Adds the Comment View.
     * @return this builder
     */
    public ShareifyAppBuilder addCommentView() {
        commentViewModel = new CommentViewModel();
        commentView = new CommentView(commentViewModel);
        cardPanel.add(commentView, commentView.getViewName());
        return this;
    }

    /**
     * Adds the comment Use Case.
     * @return this builder.
     */
    public ShareifyAppBuilder addCommentUseCase() {
        final CommentOutputBoundary commentOutputBoundary = new CommentPresenter(commentViewModel, viewManagerModel,
                friendPlaylistViewModel);
        final CommentInputBoundary commentInteractor = new CommentInteractor(userDataAccessObject,
                commentOutputBoundary);

        final CommentController commentController = new CommentController(commentInteractor);
        commentView.setCommentController(commentController);
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
