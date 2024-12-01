package use_case.friends_list_user_story.friends_list;

import data_access.DataAccessException;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;

public class FriendsListInteractorTest {

    private FriendsListInteractor friendsListInteractor;
    private FriendsListOutputBoundary presenter;
    private FriendsListDataAccessInterface friendsListDataAccessInterface;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        presenter = mock(FriendsListOutputBoundary.class);
        friendsListDataAccessInterface = mock(FriendsListDataAccessInterface.class);
        friendsListInteractor = new FriendsListInteractor(presenter, friendsListDataAccessInterface);
    }

    @Test
    public void testSwitchToNoteView() {
        friendsListInteractor.switchToNoteView();
        verify(presenter, times(1)).switchToNoteView();
    }

    @Test
    public void testSwitchToFriendsListView() {
        friendsListInteractor.switchToFriendsListView();
        verify(presenter, times(1)).switchToFriendsListView();
    }

    @Test
    public void testSwitchToFriendProfileView() {
        String friendName = "JohnDoe";
        String password = "secretPassword";

        friendsListInteractor.switchToFriendProfileView(friendName, password);

        verify(presenter, times(1)).switchToFriendProfileView(friendName, password);
    }

    @Test
    public void testSwitchToPlaylistCollectionView() {
        friendsListInteractor.switchToPlaylistCollectionView();
        verify(presenter, times(1)).switchToPlaylistCollectionView();
    }

    @Test
    public void testSwitchToAddFriendView() {
        friendsListInteractor.switchToAddFriendView();
        verify(presenter, times(1)).switchToAddFriendView();
    }

    @Test
    public void testSwitchToUserProfileView() {
        friendsListInteractor.switchToUserProfileView();
        verify(presenter, times(1)).switchToUserProfileView();
    }

    @Test
    public void testExecuteGetFriendsSuccess() throws DataAccessException {
        String username = "TestUser";
        List<String> mockFriendsList = Arrays.asList("Friend1", "Friend2");
        when(friendsListDataAccessInterface.getFriends(username)).thenReturn(mockFriendsList);

        friendsListInteractor.executeGetFriends(username);

        verify(presenter, times(1)).prepareGetFriendsSuccessView(mockFriendsList);
    }

    @Test
    public void testExecuteGetFriendsFailure() throws DataAccessException {
        String username = "TestUser";
        when(friendsListDataAccessInterface.getFriends(username))
                .thenThrow(new DataAccessException("Failed to retrieve friends"));

        friendsListInteractor.executeGetFriends(username);

        verify(presenter, times(1)).prepareFailView("Failed to retrieve friends");
    }

    @Test
    public void testExecuteRemoveFriendInDBSuccess() throws DataAccessException {
        User mockUser = new User("username", "password");
        int index = 0;

        friendsListInteractor.executeRemoveFriendInDB(mockUser, index);

        verify(friendsListDataAccessInterface, times(1)).removeFriendinDB(mockUser, index);
    }

    @Test
    public void testExecuteRemoveFriendInDBFailure() throws DataAccessException {
        User mockUser = new User("username", "password");
        int index = 0;
        doThrow(new DataAccessException("Failed to remove friend"))
                .when(friendsListDataAccessInterface).removeFriendinDB(mockUser, index);

        friendsListInteractor.executeRemoveFriendInDB(mockUser, index);

        verify(presenter, times(1)).prepareFailView("Failed to remove friend");
    }

    @Test
    public void testExecuteGetPasswordByUserNameSuccess() throws DataAccessException {
        String username = "TestUser";
        String password = "password";
        when(friendsListDataAccessInterface.getPasswordByUserName(username)).thenReturn(password);

        friendsListInteractor.executeGetPasswordByUserName(username);

        verify(presenter, times(1)).prepareGetFriendPasswordbyUserNameSuccessView(password);
    }

    @Test
    public void testExecuteGetPasswordByUserNameFailure() throws DataAccessException {
        String username = "TestUser";
        when(friendsListDataAccessInterface.getPasswordByUserName(username))
                .thenThrow(new DataAccessException("Error message"));

        friendsListInteractor.executeGetPasswordByUserName(username);

        verify(presenter, times(1)).prepareFailView("Error message");
    }

}