package interface_adapter.login_user_story.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.ViewModel;
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

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final UserProfileViewModel UserProfileViewModel;
    private final FriendsListViewModel FriendsListViewModel;
    private final AddFriendViewModel AddFriendViewModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private final AddPlaylistViewModel addPlaylistViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          UserProfileViewModel userProfileViewModel,
                          LoginViewModel loginViewModel,
                          FriendsListViewModel friendsListViewModel,
                          AddFriendViewModel addFriendViewModel,
                          PlaylistCollectionViewModel playlistCollectionViewModel,
                          AddPlaylistViewModel addPlaylistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.UserProfileViewModel = userProfileViewModel;
        this.loginViewModel = loginViewModel;
        this.FriendsListViewModel = friendsListViewModel;
        this.AddFriendViewModel = addFriendViewModel;
        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.addPlaylistViewModel = addPlaylistViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData response) {
        // On success, switch to the logged in view.
        this.viewManagerModel.setState(UserProfileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();

        final UserProfileState userProfileState = UserProfileViewModel.getState();
        userProfileState.setUsername(response.getUsername());
        this.UserProfileViewModel.setState(userProfileState);
        this.UserProfileViewModel.firePropertyChanged();

        final FriendsListState friendsListState = FriendsListViewModel.getState();
        friendsListState.setPassword(response.getPassword());
        friendsListState.setUsername(response.getUsername());
        this.FriendsListViewModel.setState(friendsListState);
        this.FriendsListViewModel.firePropertyChanged();

        final AddFriendState addFriendState = AddFriendViewModel.getState();
        addFriendState.setUsername(response.getUsername());
        addFriendState.setPassword(response.getPassword());
        this.AddFriendViewModel.setState(addFriendState);
        this.AddFriendViewModel.firePropertyChanged();

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
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

}
