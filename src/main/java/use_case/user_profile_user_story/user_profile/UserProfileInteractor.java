package use_case.user_profile_user_story.user_profile;

import data_access.LoggedInDataAccessObject;
import entity.User;

/**
 * The interactor for our User Profile.
 */
public class UserProfileInteractor implements UserProfileInputBoundary {

    private final LoggedInDataAccessObject loggedInDataAccessObject;
    private final UserProfileOutputBoundary userProfilePresenter;

    public UserProfileInteractor(LoggedInDataAccessObject loggedInDataAccessObject, UserProfileOutputBoundary userProfilePresenter) {
        this.loggedInDataAccessObject = loggedInDataAccessObject;
        this.userProfilePresenter = userProfilePresenter;
    }

    @Override
    public void execute() {
        final String username = loggedInDataAccessObject.getUsername();
        final String password = loggedInDataAccessObject.getPassword();
        final UserProfileOutputData userProfileOutputData = new UserProfileOutputData(username, false);
        userProfilePresenter.prepareSuccessView(userProfileOutputData);
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

    @Override
    public void switchToChangePasswordView() {
        userProfilePresenter.switchToChangePasswordView();
    }
}
