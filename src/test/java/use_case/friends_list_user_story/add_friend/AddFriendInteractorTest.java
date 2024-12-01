package use_case.friends_list_user_story.add_friend;

import data_access.DataAccessException;
import entity.User;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class AddFriendInteractorTest {

    private AddFriendInteractor addFriendInteractor;
    private AddFriendOutputBoundary outputBoundary;
    private AddFriendDataAccessInterface addFriendDataAccessInterface;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        outputBoundary = mock(AddFriendOutputBoundary.class);
        addFriendDataAccessInterface = mock(AddFriendDataAccessInterface.class);
        addFriendInteractor = new AddFriendInteractor(addFriendDataAccessInterface, outputBoundary);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorThrowsNullPointerExceptionWhenOutputBoundaryIsNull() {
        new AddFriendInteractor(addFriendDataAccessInterface, null);
    }

    @Test
    public void testExecuteAddsFriendToListAndCallsPresenter() {
        String friendName = "JohnDoe";
        List<String> expectedFriendsList = new ArrayList<>();
        expectedFriendsList.add(friendName);

        addFriendInteractor.execute(friendName);

        verify(outputBoundary, times(1))
                .prepareSuccessView(expectedFriendsList, friendName);
    }

    @Test
    public void testSwitchToFriendsListView() {
        addFriendInteractor.switchToFriendsListView();

        verify(outputBoundary, times(1)).swtichToFriendsListView();
    }

    @Test
    public void testExecuteGetUserByUserNameSuccess() throws DataAccessException {
        String userName = "JohnDoe";
        String returnedUserName = "JohnDoe";
        when(addFriendDataAccessInterface.getUserByUsername(userName)).thenReturn(returnedUserName);

        addFriendInteractor.executeGetUserByUserName(userName);

        verify(outputBoundary, times(1))
                .prepareGetUserByUserNameSuccessView(returnedUserName);
    }

    @Test
    public void testExecuteGetUserByUserNameFailure() throws DataAccessException {
        String userName = "JohnDoe";
        when(addFriendDataAccessInterface.getUserByUsername(userName))
                .thenThrow(new DataAccessException("User not found"));

        addFriendInteractor.executeGetUserByUserName(userName);

        verify(outputBoundary, times(1))
                .prepareFailView("User not found");
    }

    @Test
    public void testExecuteAddFriendInDBSuccess() throws DataAccessException {
        User user = new User("User1", "password");
        String friendName = "Friend1";

        addFriendInteractor.executeAddFriendInDB(user, friendName);

        verify(addFriendDataAccessInterface, times(1))
                .addFriendinDB(user, friendName);
    }

    @Test
    public void testExecuteAddFriendInDBFailure() throws DataAccessException {
        User user = new User("User1", "password");
        String friendName = "Friend1";
        doThrow(new DataAccessException("Failed to add friend"))
                .when(addFriendDataAccessInterface).addFriendinDB(user, friendName);

        addFriendInteractor.executeAddFriendInDB(user, friendName);

        verify(outputBoundary, times(1))
                .prepareFailView("Failed to add friend");
    }
}
