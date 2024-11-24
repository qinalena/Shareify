package interface_adapter.playlist_collection_user_story.playlist_collection;

import interface_adapter.ViewManagerModel;
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionOutputBoundary;
import interface_adapter.playlist_collection_user_story.add_playlist.AddPlaylistViewModel;
import interface_adapter.user_profile_user_story.user_profile.UserProfileViewModel;
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionOutputBoundary;
import view.playlist_collection_user_story.PlaylistCollectionView;

/**
 * The Presenter for Playlist Collection Use Case.
 */
public class PlaylistCollectionPresenter implements PlaylistCollectionOutputBoundary {

    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private final ViewManagerModel viewManagerModel;
    private final UserProfileViewModel userProfileViewModel = new UserProfileViewModel();
    private PlaylistCollectionView addPlaylistViewModel;

    public PlaylistCollectionPresenter(PlaylistCollectionViewModel playlistCollectionViewModel,
                                       ViewManagerModel viewManagerModel) {

        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the add playlist case.
     * @param playlistName the output data
     */
    @Override
    public void preparePlaylistAddedView(String playlistName) {
        playlistCollectionViewModel.getState().addPlaylist(playlistName);
        playlistCollectionViewModel.firePropertyChanged();
    }

    /**
     * Prepares the delete playlist case.
     *
     * @param playlistName the explanation of the failure
     */
    @Override
    public void preparePlaylistRemovedView(String playlistName) {
        playlistCollectionViewModel.getState().removePlaylist(playlistName);
        playlistCollectionViewModel.firePropertyChanged();
    }

    /**
     * Prepares the failure view for the Playlist Collection related Use cases.
     * @param error indicates issue
     */
    @Override
    public void prepareFailView(String error) {
        playlistCollectionViewModel.getState().setPlaylistError(error);
        playlistCollectionViewModel.firePropertyChanged();
    }

    @Override
    public void switchToUserProfileView() {
        viewManagerModel.setState(userProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToAddPlaylistView() {
        viewManagerModel.setState(addPlaylistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}