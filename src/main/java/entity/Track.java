package entity;

/**
 * Representation of a Spotify Track in our program.
 */
public class Track {
    private String id;
    private String name;
    private String artist;

    public Track(String id, String name, String artist) {
        this.id = id;
        this.name = name;
        this.artist = artist;
    }

    public String getName() {
        return name;
    }

    public String getArtist() {
        return artist;
    }
}
