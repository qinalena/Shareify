package interface_adapter.friends_list_user_story.friend_playlist;

import use_case.friends_list_user_story.friend_playlist.FriendPlaylistInputBoundary;

/**
 * Controller for Friend Playlist.
 */
public class FriendPlaylistController {
    private final FriendPlaylistInputBoundary friendPlaylistInputBoundary;

    public FriendPlaylistController(FriendPlaylistInputBoundary friendPlaylistInputBoundary) {
        this.friendPlaylistInputBoundary = friendPlaylistInputBoundary;
    }

    /**
     * Switches to Playlist Collection View.
     */
    public void switchToPlaylistCollectionView() {
        friendPlaylistInputBoundary.switchToPlaylistCollectionView();
    }

    /**
     * Switches to comment view.
     * @param friendUsername the username of the friend.
     * @param playlistName the name of the playlist you want to comment on.
     */
    public void switchToCommentView(String friendUsername, String playlistName) {
        friendPlaylistInputBoundary.switchToCommentView(friendUsername, playlistName);
    }

}
