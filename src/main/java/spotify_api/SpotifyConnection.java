package spotify_api;

import java.io.IOException;

import org.apache.hc.core5.http.ParseException;

import com.neovisionaries.i18n.CountryCode;
import se.michaelthelin.spotify.SpotifyApi;
import se.michaelthelin.spotify.exceptions.SpotifyWebApiException;
import se.michaelthelin.spotify.model_objects.specification.Paging;
import se.michaelthelin.spotify.model_objects.specification.Track;
import se.michaelthelin.spotify.requests.data.search.simplified.SearchTracksRequest;
import use_case.playlist_user_story.search_song.SearchSongDataAccessInterface;

/**
 * Class that implements SpotifyConnectionInterface.
 */
public class SpotifyConnection implements SearchSongDataAccessInterface {
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
    public Track[] searchTrack(String query) {
        SearchTracksRequest searchTracksRequest = spotifyApi.searchTracks(query)
                .market(CountryCode.NA).limit(10).offset(0).includeExternal("audio").build();
        try {
            final Paging<Track> trackPaging = searchTracksRequest.execute();

            return trackPaging.getItems();
        }
        catch (IOException | SpotifyWebApiException | ParseException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

}
