package data_access.spotify_api;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import entity.Song;
import org.apache.hc.core5.http.ParseException;

import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import use_case.playlist_user_story.search_song.SpotifyAuthorizationInterface;
import use_case.playlist_user_story.search_song.SpotifyDataAccessInterface;

/**
 * Class that implements SpotifyDataAccessInterface.
 */
public class SpotifyDataAccessObject implements SpotifyDataAccessInterface {
    private final SpotifyAuthorizationInterface spotifyAuthorization;
    private final SpotifyApi spotifyApi;

    public SpotifyDataAccessObject(SpotifyAuthorizationInterface spotifyAuthorization) {
        this.spotifyAuthorization = spotifyAuthorization;
        this.spotifyApi = this.spotifyAuthorization.getSpotifyApi();
    }

    @Override
    public List<Song> searchTrack(String query) {
        final SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(query)
                .market(CountryCode.NA).limit(10).offset(0).includeExternal("audio").build();
        try {
            final Paging<Track> trackPaging = searchTracksRequest.execute();

            final List<Song> searchResults = new ArrayList<>();

            for (Track track : trackPaging.getItems()) {
                final ArtistSimplified[] artists = track.getArtists();
                final String[] artistNames = new String[artists.length];

                for (int i = 0; i < artists.length; i++) {
                    artistNames[i] = artists[i].getName();
                }

                final Song song = new Song(track.getName(), artistNames);
                searchResults.add(song);
            }

            return searchResults;
        }
        catch (IOException | SpotifyWebApiException | ParseException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
        return new ArrayList<>();
    }

}
