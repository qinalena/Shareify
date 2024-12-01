package interface_adapter.login_user_story.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.chat.ChatState;
import interface_adapter.chat.ChatViewModel;
import interface_adapter.comment.CommentState;
import interface_adapter.comment.CommentViewModel;
import interface_adapter.friends_list_user_story.add_friend.AddFriendState;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistState;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistViewModel;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionState;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;
import interface_adapter.user_profile_user_story.user_profile.UserProfileState;
import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;
import interface_adapter.friends_list_user_story.friends_list.FriendsListViewModel;
import interface_adapter.friends_list_user_story.friends_list.FriendsListState;
import interface_adapter.friends_list_user_story.add_friend.AddFriendViewModel;
import use_case.login_user_story.login.LoginOutputBoundary;
import use_case.login_user_story.login.LoginOutputData;
import view.ChatView;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    //    private final UserProfileViewModel UserProfileViewModel;
    //    private final FriendsListViewModel FriendsListViewModel;
    //    private final AddFriendViewModel AddFriendViewModel;
    private final ChatViewModel chatViewModel;
    private final CommentViewModel commentViewModel;
    private final UserProfileViewModel userProfileViewModel;
    private final FriendsListViewModel friendsListViewModel;
    private final AddFriendViewModel addFriendViewModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private final AddPlaylistViewModel addPlaylistViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          UserProfileViewModel userProfileViewModel,
                          LoginViewModel loginViewModel,
                          FriendsListViewModel friendsListViewModel,
                          AddFriendViewModel addFriendViewModel,
                          PlaylistCollectionViewModel playlistCollectionViewModel,
                          AddPlaylistViewModel addPlaylistViewModel, ChatViewModel chatViewModel,
                          CommentViewModel commentViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userProfileViewModel = userProfileViewModel;
        this.loginViewModel = loginViewModel;
        this.chatViewModel = chatViewModel;
        this.friendsListViewModel = friendsListViewModel;
        this.addFriendViewModel = addFriendViewModel;
        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.addPlaylistViewModel = addPlaylistViewModel;
        this.commentViewModel = commentViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.
        this.viewManagerModel.setState(userProfileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

        final UserProfileState userProfileState = userProfileViewModel.getState();
        userProfileState.setCurrentUsername(response.getUsername());
        if (response.getNote() == ""){
            userProfileState.setBio("Hi! I'm new to Shareify! :)");
        } else {
            userProfileState.setBio(response.getNote());}
        this.userProfileViewModel.setState(userProfileState);
        this.userProfileViewModel.firePropertyChanged();

        final FriendsListState friendsListState = friendsListViewModel.getState();
        friendsListState.setPassword(response.getPassword());
        friendsListState.setUsername(response.getUsername());
        this.friendsListViewModel.setState(friendsListState);
        this.friendsListViewModel.firePropertyChanged();

        final AddFriendState addFriendState = addFriendViewModel.getState();
        addFriendState.setUsername(response.getUsername());
        addFriendState.setPassword(response.getPassword());
        this.addFriendViewModel.setState(addFriendState);
        this.addFriendViewModel.firePropertyChanged();

        final PlaylistCollectionState playlistCollectionState = playlistCollectionViewModel.getState();
        playlistCollectionState.setUsername(response.getUsername());
        playlistCollectionState.setPassword(response.getPassword());
        this.playlistCollectionViewModel.setState(playlistCollectionState);
        this.playlistCollectionViewModel.firePropertyChanged();

        final AddPlaylistState addPlaylistState = addPlaylistViewModel.getState();
        addPlaylistState.setUsername(response.getUsername());
        addPlaylistState.setPassword(response.getPassword());
        this.addPlaylistViewModel.setState(addPlaylistState);
        this.addPlaylistViewModel.firePropertyChanged();

        final ChatState chatState = chatViewModel.getState();
        chatState.setUsername(response.getUsername());
        this.chatViewModel.setState(chatState);
        this.chatViewModel.firePropertyChanged();

        final CommentState commentState = commentViewModel.getState();
        commentState.setUsername(response.getUsername());
        this.commentViewModel.setState(commentState);
        this.commentViewModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

}
