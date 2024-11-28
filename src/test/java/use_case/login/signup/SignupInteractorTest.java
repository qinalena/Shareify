package use_case.login.signup;

import entity.User;
import data_access.DBUserDataAccessObject;
import entity.UserFactory;
import interface_adapter.login_user_story.signup.SignupPresenter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login_user_story.signup.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class SignupInteractorTest {

    private SignupInteractor userSignupInteractor;
    private SignupUserDataAccessInterface userDataAccess;
    private SignupOutputBoundary userPresenter;
    private UserFactory userFactory;

    @BeforeEach
    public void setUp() {
        userFactory = new UserFactory();
        userDataAccess = mock(SignupUserDataAccessInterface.class);
        userPresenter = mock(SignupOutputBoundary.class);
        userSignupInteractor = new SignupInteractor(userDataAccess, userPresenter,userFactory);
    }

    @Test
    public void testSignup()  {
        SignupInputData inputData = new SignupInputData("NewUser", "123", "123");
        userSignupInteractor.execute(inputData);

        verify(userDataAccess, times(1)).createUser(any(User.class));
        verify(userPresenter, times(1)).prepareSuccessView(any(SignupOutputData.class));
    }

    @Test
    public void testPasswordsDoNotMatch()  {
        SignupInputData inputData = new SignupInputData("NewUser", "123", "1");
        userSignupInteractor.execute(inputData);

        verify(userDataAccess, never()).createUser(any(User.class));
        verify(userPresenter, times(1)).prepareFailView("Passwords don't match.");
    }

    @Test
    public void testUserAlreadyExist()  {
        SignupInputData inputData = new SignupInputData("NewUser", "123", "123");
        SignupInputData anotherInputData = new SignupInputData("NewUser", "1", "1");
        when(userDataAccess.existsByName("NewUser")).thenReturn(false).thenReturn(true);
        userSignupInteractor.execute(inputData);
        userSignupInteractor.execute(anotherInputData);
        verify(userDataAccess, times(1)).createUser(any(User.class));
        verify(userPresenter, times(1)).prepareFailView("User already exists.");
    }

    @Test
    public void testSwitchToLoginView(){
        userSignupInteractor.switchToLoginView();
        verify(userPresenter, times(1)).switchToLoginView();
    }

}