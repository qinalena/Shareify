package entity;

/**
 * The representation of a playlist for our program.
 */

public class Playlist {

    private int id;
    private String name;
    private String privacy;
    // need to add a list for songs stored later

    // Constructor for creating a new playlist with a name
    public Playlist(int id, String name, String privacy) {
        this.id = id;
        this.name = name;
        this.privacy = "";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPrivacy() {
        return privacy;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrivacy(String privacy) {
        this.privacy = privacy;
    }

    // String rep of the playlist
    @Override
    public String toString() {
        return name;
    }
}
