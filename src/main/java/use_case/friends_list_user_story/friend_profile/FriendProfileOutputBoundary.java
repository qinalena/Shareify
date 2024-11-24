package use_case.friends_list_user_story.friend_profile;

public interface FriendProfileOutputBoundary {

    /**
     * Prepares the success view for the user profile related Use Cases.
     * @param message the output data
     */
    void prepareSuccessView(String message);

    /**
     * Prepares the failure view for the user profile related Use Cases.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Note View.
     */
    void switchToNoteView();

    void switchToFriendsListView();

    void switchToPlaylistCollectionView(String username, String password);
}
