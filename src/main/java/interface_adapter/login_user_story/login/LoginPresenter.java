package interface_adapter.login_user_story.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.user_profile_user_story.user_profile.UserProfileState;
import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistState;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistViewModel;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionState;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;
import use_case.login_user_story.login.LoginOutputBoundary;
import use_case.login_user_story.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final UserProfileViewModel userProfileViewModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private final AddPlaylistViewModel addPlaylistViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          UserProfileViewModel userProfileViewModel,
                          LoginViewModel loginViewModel, PlaylistCollectionViewModel playlistCollectionViewModel,
                          AddPlaylistViewModel addPlaylistViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.userProfileViewModel = userProfileViewModel;
        this.loginViewModel = loginViewModel;
        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.addPlaylistViewModel = addPlaylistViewModel;
    }

    @Override
    public void prepareSuccessView(LoginOutputData loginOutputData) {
        // On success, switch to the UserProfile view.

        final UserProfileState userProfileState = userProfileViewModel.getState();

        userProfileState.setUsername(loginOutputData.getUsername());
        this.userProfileViewModel.setState(userProfileState);
        this.userProfileViewModel.firePropertyChanged();
        this.viewManagerModel.setState(userProfileViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

}
