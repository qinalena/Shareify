package entity;

/**
 * The representation of a playlist for our program.
 */

public class Playlist {

    private int id;
    private String name;
    private String privacy;

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
}
