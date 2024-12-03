package use_case.friends_list_user_story.friends_list;

import java.util.List;

/**
 * The output boundary interface for the Friends List use case.
 * It defines methods for presenting the results of actions
 * related to managing a user's friends list and handling transitions between views.
 */
public interface FriendsListOutputBoundary {

    /**
     * Presents the view after a friend has been successfully added.
     *
     * @param friendName The name of the friend that was added.
     */
    void presentFriendAdded(String friendName);

    /**
     * Presents the view after a friend has been successfully deleted.
     *
     * @param friendName The name of the friend that was deleted.
     */
    void presentFriendDeleted(String friendName);

    /**
     * Presents an error message to the user.
     *
     * @param errorMessage The error message to be displayed.
     */
    void presentError(String errorMessage);

    /**
     * Switches to the Note View, where the user can view or edit notes.
     */
    void switchToNoteView();

    /**
     * Switches to the Friends List View, where the user can view their friends.
     */
    void switchToFriendsListView();

    /**
     * Switches to the Friend Profile View for a selected friend.
     *
     * @param selectedFriendName The name of the selected friend.
     * @param password The password of the selected friend.
     */
    void switchToFriendProfileView(String selectedFriendName, String password);

    /**
     * Switches to the Playlist Collection View, where the user can manage their playlists.
     */
    void switchToPlaylistCollectionView();

    /**
     * Switches to the Add Friend View, where the user can add a new friend.
     */
    void switchToAddFriendView();

    /**
     * Switches to the User Profile View, where the user can manage their own profile.
     */
    void switchToUserProfileView();

    /**
     * Prepares the success view when the list of friends is successfully retrieved.
     *
     * @param friends The list of friends to be displayed.
     */
    void prepareGetFriendsSuccessView(List<String> friends);

    /**
     * Prepares the failure view with an error message.
     *
     * @param error The error message to be displayed.
     */
    void prepareFailView(String error);

    /**
     * Prepares the success view when a friend's password is successfully retrieved.
     *
     * @param password The password of the friend.
     */
    void prepareGetFriendPasswordbyUserNameSuccessView(String password);
}
