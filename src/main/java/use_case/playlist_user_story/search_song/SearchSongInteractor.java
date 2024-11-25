package use_case.playlist_user_story.search_song;

import entity.Song;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import spotify_api.SpotifyConnectionInterface;

/**
 * The Interactor for SearchTrack.
 */
public class SearchSongInteractor implements SearchSongInputBoundary {

    private final SpotifyConnectionInterface spotifyDAO;
    private final SearchSongDataAccessInterface searchSongDAO;
    private final SearchSongOutputBoundary searchTrackPresenter;

    public SearchSongInteractor(SpotifyConnectionInterface spotifyDAO, SearchSongDataAccessInterface searchSongDAO, SearchSongOutputBoundary searchTrackPresenter) {
        this.spotifyDAO = spotifyDAO;
        this.searchSongDAO = searchSongDAO;
        this.searchTrackPresenter = searchTrackPresenter;
    }

    @Override
    public void searchSong(String query) {
        if (query != null) {
            SearchSongOutputData searchSongOutputData = new SearchSongOutputData();
            final Track[] searchResults = spotifyDAO.searchTrack(query);

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

    @Override
    public void addSong(SearchSongInputData selectedSong) {

    }
}
