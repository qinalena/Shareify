package use_case.user_profile_user_story.note;

/**
 * Output Data for the Note Use Case.
 */
public class NoteOutputData {

    private final String username;
    private String note;

    public NoteOutputData(String username, String note) {
        this.username = username;
        this.note = note;}

    public String getUsername() {
        return username;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}