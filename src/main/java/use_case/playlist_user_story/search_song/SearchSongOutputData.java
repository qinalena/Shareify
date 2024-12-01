package use_case.playlist_user_story.search_song;
import se.michaelthelin.spotify.model_objects.specification.ArtistSimplified;
import se.michaelthelin.spotify.model_objects.specification.Track;
import entity.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * The output data for Search Track.
 */
public class SearchSongOutputData {
    private List<Song> searchResults = new ArrayList<Song>();

    public SearchSongOutputData(Track[] tracks) {
        for (Track track : tracks) {
            final ArtistSimplified[] artists = track.getArtists();
            final String[] artistNames = new String[artists.length];

            for (int i = 0; i < artists.length; i++) {
                artistNames[i] = artists[i].getName();
            }

            final Song song = new Song(track.getName(), artistNames);
            searchResults.add(song);
        }
    }

    public List<Song> getSearchResults() {
        return searchResults;
    }
}
