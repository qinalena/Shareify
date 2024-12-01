package use_case.login_user_story.login;

/**
 * Output Data for the Login Use Case.
 */
public class LoginOutputData {

    private final String username;
    private final String password;
    private final String note;

    public LoginOutputData(String username, String password, String note) {
        this.username = username;
        this.note = note;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getNote() {
        return note;
    }

}
