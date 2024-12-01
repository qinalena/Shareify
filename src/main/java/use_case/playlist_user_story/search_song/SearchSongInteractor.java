package use_case.playlist_user_story.search_song;

import entity.Song;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import data_access.DataAccessException;
import use_case.playlist_user_story.playlist.PlaylistDataAccessInterface;

/**
 * The Interactor for Search Song.
 */
public class SearchSongInteractor implements SearchSongInputBoundary {

    private final SpotifyConnectionInterface spotifyDAO;
    private final PlaylistDataAccessInterface playlistDAO;
    private final SearchSongOutputBoundary searchSongPresenter;

    public SearchSongInteractor(SpotifyConnectionInterface spotifyDAO,
                                PlaylistDataAccessInterface playlistDAO, SearchSongOutputBoundary searchSongPresenter) {
        this.spotifyDAO = spotifyDAO;
        this.playlistDAO = playlistDAO;
        this.searchSongPresenter = searchSongPresenter;
    }

    @Override
    public void searchSong(String query) {
        if (query != null) {
            final SearchSongOutputData searchSongOutputData = new SearchSongOutputData();
            final Track[] searchResults = spotifyDAO.searchTrack(query);

            for (Track searchResult : searchResults) {
                final ArtistSimplified[] artists = searchResult.getArtists();
                final String[] artistNames = new String[artists.length];

                for (int i = 0; i < artists.length; i++) {
                    artistNames[i] = artists[i].getName();
                }

                final Song song = new Song(searchResult.getName(), artistNames);
                searchSongOutputData.addSong(song);
            }

            searchSongPresenter.searchSong(searchSongOutputData);
        }

    }

    @Override
    public void switchToPlaylistView() {
        searchSongPresenter.switchToPlaylistView();
    }

    @Override
    public void addSong(SearchSongInputData searchSongInputData) {
        // TODO: Update playlist in DB with new song
        try {
            playlistDAO.addSongToPlaylist(searchSongInputData.getCurrentPlaylist(),
                    searchSongInputData.getSelectedSong());

            // Update Playlist View with new song
            searchSongPresenter.addSong(searchSongInputData.getSelectedSong());
        }
        catch (DataAccessException exception) {
            // Prepare some sort of failure message
            searchSongPresenter.prepareFailView(exception.getMessage());
        }
    }
}
