package use_case.user_profile_user_story.user_profile;

import entity.Playlist;
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionDataAccessInterface;

import java.util.List;

/**
 * The interactor for our User Profile.
 */
public class UserProfileInteractor implements UserProfileInputBoundary {

    private final PlaylistCollectionDataAccessInterface playlistCollectionDataAccessObject;
    private final UserProfileOutputBoundary userProfilePresenter;

    public UserProfileInteractor(PlaylistCollectionDataAccessInterface playlistCollectionDataAccessObject, UserProfileOutputBoundary userProfilePresenter) {
        this.playlistCollectionDataAccessObject = playlistCollectionDataAccessObject;
        this.userProfilePresenter = userProfilePresenter;
    }

    @Override
    public void switchToNoteView() {
        userProfilePresenter.switchToNoteView();
    }

    @Override
    public void switchToPlaylistCollectionView() {
        // Reformat into output data
        try {
            List<Playlist> playlistCollection = playlistCollectionDataAccessObject.getPlaylistCollection();
            userProfilePresenter.switchToPlaylistCollectionView(playlistCollection);
        }
        catch (Exception e) {}
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
