package use_case.user_profile_user_story.change_password;

/**
 * Output Data for the Change Password Use Case.
 */
public class ChangePasswordOutputData {

    private final String username;

    private final boolean useCaseFailed;

    public ChangePasswordOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}