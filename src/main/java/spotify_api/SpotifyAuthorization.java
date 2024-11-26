package spotify_api;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.credentials.ClientCredentials;
import se.michaelthelin.spotify.requests.authorization.client_credentials.ClientCredentialsRequest;

/**
 * Class for authorizing Spotify.
 */
public class SpotifyAuthorization {
    private static final String CLIENT_ID = "c2cd5d9b2e994bfcae5dbea6d8df3c5b";
    private static final String CLIENT_SECRET = System.getenv("ShareifySecret");

    private final SpotifyApi spotifyApi = new SpotifyApi.Builder()
            .setClientId(CLIENT_ID)
            .setClientSecret(CLIENT_SECRET)
            .build();

    private final ClientCredentialsRequest clientCredentialsRequest = spotifyApi.clientCredentials().build();

    /**
     * Create instance of spotify access.
     */
    public SpotifyAuthorization() {
        setClientCredentials();
    }

    private void setClientCredentials() {
        try {
            final ClientCredentials clientCredentials = clientCredentialsRequest.execute();

            // Set access token for further "spotifyApi" object usage
            spotifyApi.setAccessToken(clientCredentials.getAccessToken());

            System.out.println("Expires in: " + clientCredentials.getExpiresIn());
        }
        catch (IOException | SpotifyWebApiException | ParseException exception) {
            System.out.println("Error: " + exception.getMessage());
        }
    }

    public SpotifyApi getSpotifyApi() {
        return spotifyApi;
    }
}
