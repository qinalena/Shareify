package entity;

import java.util.StringJoiner;

/**
 * Representation of a Spotify Song in our program.
 */
public class Song {
    private String name;
    private String[] artists;

    public Song(String name, String[] artists) {
        this.name = name;
        this.artists = artists;
    }

    public String getName() {
        return name;
    }

    public String[] getArtists() {
        return artists;
    }

    /**
     * Convert artists to a singular string for display purposes.
     * @return string of artists
     */
    public String artistsToString() {
        StringJoiner joiner = new StringJoiner(", ");
        for (String artist : artists) {
            joiner.add(artist);
        }
        return joiner.toString();
    }
}
