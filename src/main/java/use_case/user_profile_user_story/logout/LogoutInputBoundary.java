package use_case.user_profile_user_story.logout;

/**
 * Input Boundary for actions which are related to logging in.
 */
public interface LogoutInputBoundary {

    /**
     * Executes the Logout use case.
     * @param LogoutInputData the input data
     */
    void execute(LogoutInputData LogoutInputData);
}
