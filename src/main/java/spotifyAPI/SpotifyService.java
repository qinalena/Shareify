package spotifyAPI;
import com.neovisionaries.i18n.CountryCode;
import org.apache.hc.core5.http.ParseException;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.model_objects.specification.Artist;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;
import se.michaelthelin.spotify.requests.data.artists.GetArtistRequest;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;

import java.io.IOException;

public class SpotifyService implements SpotifyServiceInterface {
    private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId("32417f0d214a4603a18504410ce1ad12")
            .setClientSecret("08679a815a3e4fd3803f1fd744e5a988")
            .build();

    private final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

    /**
     * create instance of spotify access
     */
    public SpotifyService() {
        System.out.println("Client ID: " + "c2cd5d9b2e994bfcae5dbea6d8df3c5b");
        System.out.println("Client Secret: " + "888ed62c53024f8abfd7d8fd49854e17");
        setClientCredentials();
    }

    private void setClientCredentials() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        } catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    @Override
    public String getSongName(String songName) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(songName).market(CountryCode.NA).build();
        try {
            Track track = searchTracksRequest.execute().getItems()[0];
            return track.getName();
        } catch (IOException | SpotifyWebApiException | ParseException e) {
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
        } catch (IOException | SpotifyWebApiException | ParseException e) {
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
