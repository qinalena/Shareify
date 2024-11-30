package use_case.user_profile.change_password;

import entity.User;
import entity.UserFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.login_user_story.signup.SignupInteractor;
import use_case.login_user_story.signup.SignupOutputBoundary;
import use_case.login_user_story.signup.SignupOutputData;
import use_case.login_user_story.signup.SignupUserDataAccessInterface;
import use_case.user_profile_user_story.change_password.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ChangePasswordInteractorTest {
    private ChangePasswordInteractor changePasswordInteractor;
    private ChangePasswordUserDataAccessInterface userDataAccess;
    private ChangePasswordOutputBoundary changePwdPresenter;

    @BeforeEach
    public void setUp() {
        userDataAccess = mock(ChangePasswordUserDataAccessInterface.class);
        changePwdPresenter = mock(ChangePasswordOutputBoundary.class);
        changePasswordInteractor = new ChangePasswordInteractor(userDataAccess, changePwdPresenter);
    }

    @Test
    public void testChangePassword() {
        String userName = "TestUser";
        String oldPassword = "OldPassword";
        String newPassword = "NewPassword";

        User mockUser = mock(User.class);
        when(mockUser.getPassword()).thenReturn(oldPassword);

        ChangePasswordInputData inputData = new ChangePasswordInputData(userName, newPassword);
        when(userDataAccess.existsByName(userName)).thenReturn(true);
        changePasswordInteractor.execute(inputData);
        verify(changePwdPresenter, times(1)).prepareSuccessView(any(ChangePasswordOutputData.class));
    }

    @Test
    public void testswitchToUserProfileView(){
        changePasswordInteractor.switchToUserProfileView();
        verify(changePwdPresenter, times(1)).switchToUserProfileView();
    }

    @Test
    public void testGetUsername() {
        String username = "NewUser";
        ChangePasswordOutputData outputData = new ChangePasswordOutputData(username);

        String actualUsername = outputData.getUsername();

        assertEquals(username, actualUsername);
    }
}
