package use_case.friends_list_user_story.friend_profile;

public interface FriendProfileInputBoundary {
    /**
     * Executes the switch to Note view use case.
     */
    void switchToNoteView();

    void switchToFriendsListView();

    void switchToPlaylistCollectionView(String username, String password);
}
