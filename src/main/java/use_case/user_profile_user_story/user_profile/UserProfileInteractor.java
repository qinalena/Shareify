package use_case.user_profile_user_story.user_profile;

/**
 * The interactor for our User Profile.
 */
public class UserProfileInteractor implements UserProfileInputBoundary {

    private final UserProfileOutputBoundary userProfilePresenter;

    public UserProfileInteractor(UserProfileOutputBoundary userProfilePresenter) {
        this.userProfilePresenter = userProfilePresenter;
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
    public void switchToFriendsListView(String username, String password) {
        userProfilePresenter.switchToFriendsListView(username, password);
    }

    @Override
    public void switchToChangePasswordView() {
        userProfilePresenter.switchToChangePasswordView();
    }
}
