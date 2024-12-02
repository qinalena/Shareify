package use_case.friends_list_user_story.friend_playlist;

public interface FriendPlaylistInputBoundary {

    /**
     * Switches to Playlist Collection View.
     */
    void switchToPlaylistCollectionView();

    /**
     * Switch to comment view.
     * @param friendUsername name
     * @param playlistName name
     */
    void switchToCommentView(String friendUsername, String playlistName);
}
