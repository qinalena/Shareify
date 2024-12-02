package use_case.user_profile.user_profile;

import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.user_profile_user_story.user_profile.UserProfileInteractor;
import use_case.user_profile_user_story.user_profile.UserProfileOutputBoundary;

import static org.mockito.Mockito.*;

public class UserProfileInteractorTest {
    private UserProfileInteractor interactor;
    private UserProfileOutputBoundary presenter;
    User mockUser = mock(User.class);

    @BeforeEach
    public void setUp() {
        presenter = mock(UserProfileOutputBoundary.class);
        interactor = new UserProfileInteractor(presenter);
    }

    @Test
    public void testSwitchToNoteView(){
        interactor.switchToNoteView();
        verify(presenter, times(1)).switchToNoteView();
    }

    @Test
    public void testSwitchToPlayListCollectionView(){
        interactor.switchToPlaylistCollectionView();
        verify(presenter, times(1)).switchToPlaylistCollectionView();
    }

    @Test
    public void testSwitchFriendsView(){
        interactor.switchToFriendsListView(mockUser.getUsername(), mockUser.getUsername());
        verify(presenter, times(1)).switchToFriendsListView(mockUser.getUsername(), mockUser.getUsername());
    }

    @Test
    public void testSwitchChangePasswordView(){
        interactor.switchToChangePasswordView();
        verify(presenter, times(1)).switchToChangePasswordView();
    }
}

