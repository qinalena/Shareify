package use_case.user_profile_user_story.user_profile;

/**
 * Output Data for the Login Use Case.
 */
public class UserProfileOutputData {

    private final String username;
    private final boolean useCaseFailed;

    public UserProfileOutputData(String username, boolean useCaseFailed) {
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }

    public String getUsername() {
        return username;
    }

}
