package use_case.playlist_user_story.search_song;

import se.michaelthelin.spotify.SpotifyApi;

/**
 * Interface for Spotify Authorization. Implementation details may differ depending on the type of Authentication method
 * For now, our application uses a Client Credential Flow.
 */
public interface SpotifyAuthorizationInterface {

    /**
     * Return a SpotifyApi object to use in API endpoint calls.
     * @return a SpotifyAPI object.
     */
    SpotifyApi getSpotifyApi();
}
