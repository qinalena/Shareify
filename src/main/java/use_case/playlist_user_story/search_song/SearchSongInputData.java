package use_case.playlist_user_story.search_song;

/**
 * The Input Data for Search Song.
 */
public class SearchSongInputData {
    private String songName;
    private String[] artists;
    private String currentPlaylistName;

    public SearchSongInputData(String currentPlaylistName, String selectedString) {
        this.currentPlaylistName = currentPlaylistName;

        final String[] splitString = selectedString.split(" - ");
        this.songName = splitString[0];

        this.artists = splitString[1].split(", ");
    }

    public String getCurrentPlaylistName() {
        return currentPlaylistName;
    }

    public String getSongName() {
        return songName;
    }

    public String[] getArtists() {
        return artists;
    }
}
