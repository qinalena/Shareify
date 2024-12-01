package use_case.user_profile.logout;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login_user_story.signup.SignupOutputData;
import use_case.user_profile_user_story.change_password.ChangePasswordOutputData;
import use_case.user_profile_user_story.logout.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class LogoutInteractorTest {

    private LogoutInteractor logoutInteractor;
    private LogoutUserDataAccessInterface userDataAccess;
    private LogoutOutputBoundary logoutPresenter;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(LogoutUserDataAccessInterface.class);
        logoutPresenter = mock(LogoutOutputBoundary.class);
        logoutInteractor = new LogoutInteractor(userDataAccess, logoutPresenter);
    }

    @Test
    public void testLogoutSuccess() {
        String userName = "NewUser";
        LogoutInputData inputData = new LogoutInputData(userName);

        doNothing().when(userDataAccess).setCurrentUser(null);

        logoutInteractor.execute(inputData);

        verify(userDataAccess, times(1)).setCurrentUser(null);
        verify(logoutPresenter, times(1)).prepareSuccessView(any(LogoutOutputData.class));
    }

    @Test
    public void testGetUsername() {
        String username = "NewUser";
        LogoutOutputData outputData = new LogoutOutputData(username);

        String actualUsername = outputData.getUsername();

        assertEquals(username, actualUsername);
    }
}
