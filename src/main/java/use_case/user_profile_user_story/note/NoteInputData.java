package use_case.user_profile_user_story.note;

public class NoteInputData {

    private final String username;

    public NoteInputData(String username) {
            this.username = username;
        }

        String getUsername() {
            return username;
        }

}
