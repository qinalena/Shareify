package spotify_api;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

/**
 * Class that implements SpotifyConnectionInterface.
 */
public class SpotifyConnection implements SpotifyConnectionInterface {
    private final SpotifyAuthorization spotifyAuthorization = new SpotifyAuthorization();
    private final SpotifyApi spotifyApi = spotifyAuthorization.getSpotifyApi();

    public SpotifyConnection() {
    }

    @Override
    public String getSongName(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getName();
        }
        catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getSongArtist(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getArtists()[0].getName();
        }
        catch (IOException | SpotifyWebApiException | ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String getSongAlbum(String songName) {
        return "";
    }

    @Override
    public String getSongReleaseDate(String songName) {
        return "";
    }

    @Override
    public String getSongTags(String songName) {
        return "";
    }

    @Override
    public String getPreviewUrl(String songName) {
        return "";
    }
}
