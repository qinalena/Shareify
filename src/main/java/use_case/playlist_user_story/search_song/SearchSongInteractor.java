package use_case.playlist_user_story.search_song;

import entity.Song;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;

/**
 * The Interactor for SearchTrack.
 */
public class SearchSongInteractor implements SearchSongInputBoundary {

    private final SearchSongDataAccessInterface searchTrackDataAccessObject;
    private final SearchSongOutputBoundary searchTrackPresenter;

    public SearchSongInteractor(SearchSongDataAccessInterface searchTrackDataAccessObject, SearchSongOutputBoundary searchTrackPresenter) {
        this.searchTrackDataAccessObject = searchTrackDataAccessObject;
        this.searchTrackPresenter = searchTrackPresenter;
    }

    @Override
    public void searchSong(String query) {
        if (query != null) {
            SearchSongOutputData searchSongOutputData = new SearchSongOutputData();
            final Track[] searchResults = searchTrackDataAccessObject.searchTrack(query);

            for (Track searchResult : searchResults) {
                final ArtistSimplified[] artists = searchResult.getArtists();
                String[] artistNames = new String[artists.length];

                for (int i = 0; i < artists.length; i++) {
                    artistNames[i] = artists[i].getName();
                }

                final Song song = new Song(searchResult.getName(), artistNames);
                searchSongOutputData.addSong(song);
            }

            searchTrackPresenter.searchSong(searchSongOutputData);
        }

    }

    @Override
    public void switchToPlaylistView() {
        searchTrackPresenter.switchToPlaylistView();
    }
}
