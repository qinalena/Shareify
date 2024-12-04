package interface_adapter.playlist_user_story.search_song;

import java.util.List;

import interface_adapter.ViewManagerModel;
import interface_adapter.playlist_user_story.playlist.PlaylistViewModel;
import use_case.playlist_user_story.search_song.SearchSongOutputBoundary;

/**
 * The presenter for Search Track.
 */
public class SearchSongPresenter implements SearchSongOutputBoundary {

    private final SearchSongViewModel searchSongViewModel;
    private final PlaylistViewModel playlistViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchSongPresenter(SearchSongViewModel searchSongViewModel,
                               PlaylistViewModel playlistViewModel, ViewManagerModel viewManagerModel) {
        this.searchSongViewModel = searchSongViewModel;
        this.playlistViewModel = playlistViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void searchSong(List<String> displaySearchResults) {
        searchSongViewModel.getState().setCurrentSearchResults(displaySearchResults);
        searchSongViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPlaylistView() {
        viewManagerModel.setState(playlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void addSong(String song) {
        playlistViewModel.getState().addSong(song);
        playlistViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        searchSongViewModel.getState().setError(message);
        searchSongViewModel.firePropertyChanged();
    }
}
