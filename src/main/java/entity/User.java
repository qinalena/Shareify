package entity;

/**
 * The representation of a password-protected user for our program.
 */
public class User {

    private final String name;
    private final String password;
    private String note;

    public User(String name, String password) {
        this.name = name;
        this.password = password;
        this.note = "Hi! I'm new to Shareify :)";
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String newNote) {
        this.note = newNote;
    }

}
