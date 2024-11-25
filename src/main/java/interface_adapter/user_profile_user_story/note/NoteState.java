package interface_adapter.user_profile_user_story.note;

/**
 * The State for a note.
 * <p>For this example, a note is simply a string.</p>
 */
public class NoteState {
    private String note = "Hi! I'm new to Shareify :)";
    private String error;
    private String username;
    private String password;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public void setError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getError() {
        return error;
    }
}
