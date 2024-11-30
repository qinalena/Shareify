package use_case.user_profile_user_story.note;

/**
 * The output boundary for the Login Use Case.
 */
public interface NoteOutputBoundary {
    /**
     * Prepares the success view for the Note related Use Cases.
     * @param noteOutputData the output data
     */
    void prepareSuccessView(NoteOutputData noteOutputData);

    /**
     * Prepares the failure view for the Note related Use Cases.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Executes the switch to User Profile view use case.
     */
    void switchToUserProfileView();
}
