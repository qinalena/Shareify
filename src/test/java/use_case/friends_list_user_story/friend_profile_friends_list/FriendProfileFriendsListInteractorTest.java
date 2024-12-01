package use_case.friends_list_user_story.friend_profile_friends_list;

import data_access.DataAccessException;
import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

public class FriendProfileFriendsListInteractorTest {

    private FriendProfileFriendsListInteractor interactor;
    private FriendProfileFriendsListOutputBoundary mockPresenter;
    private FriendProfileFriendsListDataAccessInterface mockDataAccess;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockPresenter = mock(FriendProfileFriendsListOutputBoundary.class);
        mockDataAccess = mock(FriendProfileFriendsListDataAccessInterface.class);
        interactor = new FriendProfileFriendsListInteractor(mockPresenter, mockDataAccess);
    }

    @Test
    public void testSwitchToFriendProfileView() {
        // Arrange
        String selectedFriendName = "friend1";
        String password = "password123";

        // Act
        interactor.switchToFriendProfileView(selectedFriendName, password);

        // Assert
        verify(mockPresenter, times(1)).switchToFriendProfileView(selectedFriendName, password);
    }

    @Test
    public void testExecuteGetFriendsSuccess() throws DataAccessException {
        // Arrange
        String username = "user1";
        List<String> mockFriendsList = Arrays.asList("friend1", "friend2", "friend3");
        when(mockDataAccess.getFriends(username)).thenReturn(mockFriendsList);

        // Act
        interactor.executeGetFriends(username);

        // Assert
        verify(mockDataAccess, times(1)).getFriends(username);
        verify(mockPresenter, times(1)).prepareGetFriendsSuccessView(mockFriendsList);
    }

    @Test
    public void testExecuteGetFriendsFailure() throws DataAccessException {
        // Arrange
        String username = "user1";
        DataAccessException mockException = new DataAccessException("Database error");
        when(mockDataAccess.getFriends(username)).thenThrow(mockException);

        // Act
        interactor.executeGetFriends(username);

        // Assert
        verify(mockDataAccess, times(1)).getFriends(username);
        verify(mockPresenter, times(1)).prepareFailView("Database error");
    }
}
