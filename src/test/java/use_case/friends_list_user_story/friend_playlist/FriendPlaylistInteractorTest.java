package use_case.friends_list_user_story.friend_playlist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class FriendPlaylistInteractorTest {

    private FriendPlaylistInteractor interactor;
    private FriendPlaylistOutputBoundary outputBoundary;

    @Before
    public void setUp() {
        outputBoundary = Mockito.mock(FriendPlaylistOutputBoundary.class);
        interactor = new FriendPlaylistInteractor(outputBoundary);
    }

    @Test
    public void testSwitchToPlaylistCollectionView() {
        interactor.switchToPlaylistCollectionView();

        Mockito.verify(outputBoundary, Mockito.times(1)).switchToPlaylistCollectionView();
    }

    @Test
    public void testSwitchToCommentView() {
        // Arrange
        String friendUsername = "testUser";
        String playlistName = "testPlaylist";

        // Act
        interactor.switchToCommentView(friendUsername, playlistName);

        // Assert
        Mockito.verify(outputBoundary, Mockito.times(1)).switchToCommentView(friendUsername, playlistName);
    }
}