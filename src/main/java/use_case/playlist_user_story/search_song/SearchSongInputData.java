package use_case.playlist_user_story.search_song;

import entity.Song;

/**
 * The Input Data for Search Song.
 */
public class SearchSongInputData {
    private Song selectedSong;

    public SearchSongInputData(String selectedString) {
        final String[] splitString = selectedString.split(" - ");
        final String name = splitString[0];

        final String[] artists = splitString[1].split(", ");

        final Song song = new Song(name, artists);
        this.selectedSong = song;
    }
}
