package use_case.friends_list_user_story.friend_playlist;

/**
 * Output boundary for friend playlist.
 */
public interface FriendPlaylistOutputBoundary {

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
