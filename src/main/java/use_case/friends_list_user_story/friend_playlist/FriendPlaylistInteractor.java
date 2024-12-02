package use_case.friends_list_user_story.friend_playlist;

public class FriendPlaylistInteractor implements FriendPlaylistInputBoundary {

    private final FriendPlaylistOutputBoundary friendPlaylistOutputBoundary;

    public FriendPlaylistInteractor(FriendPlaylistOutputBoundary friendPlaylistOutputBoundary) {
        this.friendPlaylistOutputBoundary = friendPlaylistOutputBoundary;
    }

    @Override
    public void switchToPlaylistCollectionView() {
        friendPlaylistOutputBoundary.switchToPlaylistCollectionView();
    }

    @Override
    public void switchToCommentView(String friendUsername, String playlistName) {
        friendPlaylistOutputBoundary.switchToCommentView(friendUsername, playlistName);
    }
}
