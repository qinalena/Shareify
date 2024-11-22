package interface_adapter.login;

import interface_adapter.ViewManagerModel;
import interface_adapter.add_friend.AddFriendState;
import interface_adapter.friends_list.FriendsListState;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.user_profile.UserProfileState;
import interface_adapter.user_profile.UserProfileViewModel;
import interface_adapter.add_friend.AddFriendViewModel;
import use_case.login.LoginOutputBoundary;
import use_case.login.LoginOutputData;

/**
 * The Presenter for the Login Use Case.
 */
public class LoginPresenter implements LoginOutputBoundary {

    private final LoginViewModel loginViewModel;
    private final ViewManagerModel viewManagerModel;
    private final UserProfileViewModel UserProfileViewModel;
    private final FriendsListViewModel FriendsListViewModel;
    private final AddFriendViewModel AddFriendViewModel;

    public LoginPresenter(ViewManagerModel viewManagerModel,
                          UserProfileViewModel userProfileViewModel,
                          LoginViewModel loginViewModel,
                          FriendsListViewModel friendsListViewModel,
                          AddFriendViewModel addFriendViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.UserProfileViewModel = userProfileViewModel;
        this.loginViewModel = loginViewModel;
        this.FriendsListViewModel = friendsListViewModel;
        this.AddFriendViewModel = addFriendViewModel;
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
    }

    @Override
    public void prepareFailView(String error) {
        final LoginState loginState = loginViewModel.getState();
        loginState.setLoginError(error);
        loginViewModel.firePropertyChanged();
    }

}
