package use_case.login_user_story.signup;

public class SignupOutputData {
    private final String username;
    private final boolean useCaseFailed;

    public SignupOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() { return username; }

    public boolean getUseCaseFailed() {return useCaseFailed;}


}
