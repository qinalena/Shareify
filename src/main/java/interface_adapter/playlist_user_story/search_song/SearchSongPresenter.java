package interface_adapter.playlist_user_story.search_song;

import entity.Song;
import interface_adapter.ViewManagerModel;
import interface_adapter.playlist_user_story.playlist.PlaylistViewModel;
import use_case.playlist_user_story.search_song.SearchSongOutputBoundary;
import use_case.playlist_user_story.search_song.SearchSongOutputData;

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
    public void searchSong(SearchSongOutputData searchSongOutputData) {
        searchSongViewModel.getState().setCurrentSearchResults(searchSongOutputData.getSearchResults());
        searchSongViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPlaylistView() {
        viewManagerModel.setState(playlistViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void addSong(Song song) {
        playlistViewModel.getState().addSong(song);
        playlistViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String message) {
        searchSongViewModel.getState().setError(message);
        searchSongViewModel.firePropertyChanged();
    }
}
