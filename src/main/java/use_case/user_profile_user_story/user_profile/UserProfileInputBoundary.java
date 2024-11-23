package use_case.user_profile_user_story.user_profile;

/**
 * The Input Boundary for our User Profile use cases.
 */
public interface UserProfileInputBoundary {

    /**
     * Executes the UserProfile use cases of displaying the logged-in user's username and note.
     */
    void execute();

    /**
     * Switches to Note View.
     */
    void switchToNoteView();

    /**
     * Switches to Playlist Collection view.
     */
    void switchToPlaylistCollectionView();

    /**
     * Switches to Friends List view.
     */
    void switchToFriendsListView();
}
