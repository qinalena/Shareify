package interface_adapter.playlist_user_story.playlist;

import entity.Playlist;
import interface_adapter.ViewManagerModel;
import interface_adapter.playlist_collection_user_story.playlist_collection.PlaylistCollectionViewModel;
import interface_adapter.playlist_user_story.search_song.SearchSongViewModel;
import use_case.playlist_user_story.playlist.PlaylistOutputBoundary;

/**
 * The presenter for a Playlist.
 */
public class PlaylistPresenter implements PlaylistOutputBoundary {

    private final PlaylistViewModel playlistViewModel;
    private final PlaylistCollectionViewModel playlistCollectionViewModel;
    private final SearchSongViewModel searchSongViewModel;
    private final ViewManagerModel viewManagerModel;

    public PlaylistPresenter(PlaylistViewModel playlistViewModel,
                             PlaylistCollectionViewModel playlistCollectionViewModel, SearchSongViewModel searchSongViewModel,
                             ViewManagerModel viewManagerModel) {
        this.playlistViewModel = playlistViewModel;
        this.playlistCollectionViewModel = playlistCollectionViewModel;
        this.searchSongViewModel = searchSongViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void removeSong(int songIndex) {
        playlistViewModel.getState().removeSong(songIndex);
        playlistViewModel.firePropertyChanged();

    }

    @Override
    public void switchToPlaylistCollectionView() {
        viewManagerModel.setState(playlistCollectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }

    @Override
    public void switchToSearchSongView(Playlist currentPlaylist) {
        searchSongViewModel.getState().setCurrentPlaylist(currentPlaylist);

        viewManagerModel.setState(searchSongViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}
