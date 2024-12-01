package spotify_api;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import use_case.playlist_user_story.search_song.SpotifyConnectionInterface;

/**
 * Class that implements SpotifyConnectionInterface.
 */
public class SpotifyConnection implements SpotifyConnectionInterface {
    private final SpotifyAuthorization spotifyAuthorization = new SpotifyAuthorization();
    private final SpotifyApi spotifyApi = spotifyAuthorization.getSpotifyApi();

    public SpotifyConnection() {
    }

    @Override
    public Track[] searchTrack(String query) {
        final SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(query)
                .market(CountryCode.NA).limit(10).offset(0).includeExternal("audio").build();
        try {
            final Paging<Track> trackPaging = searchTracksRequest.execute();

            return trackPaging.getItems();
        }
        catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return new Track[0];
    }

}
