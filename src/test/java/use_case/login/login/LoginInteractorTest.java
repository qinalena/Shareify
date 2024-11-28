package use_case.login.login;

import data_access.LoggedInDataAccessObject;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login_user_story.login.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class LoginInteractorTest {
    private LoginInteractor loginInteractor;
    private LoginUserDataAccessInterface userDataAccess;
    private LoggedInDataAccessInterface loggedInDataAccess;
    private LoginOutputBoundary loginPresenter;
    private User user;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(LoginUserDataAccessInterface.class);
        loggedInDataAccess = new LoggedInDataAccessObject();
        loginPresenter = mock(LoginOutputBoundary.class);
        loginInteractor = new LoginInteractor(userDataAccess, loggedInDataAccess,loginPresenter);
        user = new User("NewUser","123");


    }

    @Test
    public void testLogin() {
        LoginInputData inputData = new LoginInputData("NewUser","123");
        when(userDataAccess.existsByName(inputData.getUsername())).thenReturn(true);
        when(userDataAccess.get(inputData.getUsername())).thenReturn(user);
        loginInteractor.execute(inputData);

        verify(loginPresenter, times(1)).prepareSuccessView(any(LoginOutputData.class));
        assertEquals(user.getName(),loggedInDataAccess.getLoggedInUser().getName());
    }

    @Test
    public void testAccountDoesNotExist() {
        LoginInputData inputData = new LoginInputData("NotExitedUser","123");
        when(userDataAccess.existsByName(inputData.getUsername())).thenReturn(false);
        loginInteractor.execute(inputData);
        verify(loginPresenter, times(1)).prepareFailView(inputData.getUsername() + ": Account does not exist.");
    }

    @Test
    public void testIncorrectPassword() {
        LoginInputData inputData = new LoginInputData("NewUser","123");
        LoginInputData inputData_wrongPwd = new LoginInputData("NewUser","123456");
        when(userDataAccess.existsByName(inputData.getUsername())).thenReturn(true);
        when(userDataAccess.get("NewUser")).thenReturn(user);

        loginInteractor.execute(inputData_wrongPwd);
        verify(loginPresenter, times(1)).prepareFailView("Incorrect password for \"" + inputData_wrongPwd.getUsername() + "\".");
    }


}
