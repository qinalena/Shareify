package use_case.friends_list_user_story.friend_profile;

import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class FriendProfileInteractorTest {

    private FriendProfileInteractor friendProfileInteractor;
    private FriendProfileOutputBoundary friendProfilePresenter;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        friendProfilePresenter = mock(FriendProfileOutputBoundary.class);
        friendProfileInteractor = new FriendProfileInteractor(friendProfilePresenter);
    }

    @Test
    public void testSwitchToNoteView() {
        friendProfileInteractor.switchToNoteView();

        verify(friendProfilePresenter, times(1)).switchToNoteView();
    }

    @Test
    public void testSwitchToFriendsListView() {
        String username = "User1";
        String password = "Password123";

        friendProfileInteractor.switchToFriendsListView(username, password);

        verify(friendProfilePresenter, times(1)).switchToFriendsListView(username, password);
    }

    @Test
    public void testSwitchToPlaylistCollectionView() {
        String username = "User1";
        String password = "Password123";

        friendProfileInteractor.switchToPlaylistCollectionView(username, password);

        verify(friendProfilePresenter, times(1)).switchToPlaylistCollectionView(username, password);
    }

    @Test
    public void testSwitchToChatView() {
        String friendUsername = "FriendUser";

        friendProfileInteractor.switchToChatView(friendUsername);

        verify(friendProfilePresenter, times(1)).switchToChatView(friendUsername);
    }

    @Test
    public void testSwitchToAllFriendsView() {
        friendProfileInteractor.switchToAllFriendsView();

        verify(friendProfilePresenter, times(1)).switchToAllFriendsView();
    }
}
