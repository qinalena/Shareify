package use_case.user_profile_user_story.note;

public class NoteInputData {

    private final String username;
    private String note;

    public NoteInputData(String username, String note) {
            this.username = username;
            this.note = note;
        }

        String getUsername() {
            return username;
        }
        String getNote() {
        return note;
        }

}
