package interface_adapter.playlist_user_story;

import interface_adapter.ViewManagerModel;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;
import use_case.playlist_user_story.PlaylistOutputBoundary;

/**
 * The presenter for a Playlist.
 */
public class PlaylistPresenter implements PlaylistOutputBoundary {

    private final PlaylistViewModel playlistViewModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private final ViewManagerModel viewManagerModel;

    public PlaylistPresenter(PlaylistViewModel playlistViewModel,
                             PlaylistCollectionViewModel playlistCollectionViewModel,
                             ViewManagerModel viewManagerModel) {
        this.playlistViewModel = playlistViewModel;
        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void removeTrack() {

    }

    @Override
    public void switchToPlaylistCollectionView() {
        viewManagerModel.setState(playlistCollectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void switchToSearchTracksView() {

    }
}
