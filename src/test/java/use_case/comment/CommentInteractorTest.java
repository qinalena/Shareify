package use_case.comment;

import data_access.DataAccessException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;


public class CommentInteractorTest {

    private CommentInteractor commentInteractor;
    private CommentOutputBoundary commentOutputBoundary;
    private CommentDataAccessInterface commentDataAccessInterface;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        commentOutputBoundary = mock(CommentOutputBoundary.class);
        commentDataAccessInterface = mock(CommentDataAccessInterface.class);
        commentInteractor = new CommentInteractor(commentDataAccessInterface, commentOutputBoundary);
    }

    @Test
    public void testSaveCommentAndCallPresenter() {
        String comment = "Hello";
        List<String> comments = new ArrayList<>();

        commentInteractor.executeSaveComment(comment, "Person1", "Playlist1");

        verify(commentOutputBoundary, times(1)).prepareSuccessView(comments);
    }

    @Test
    public void testLoadCommentsAndCallPresenter() {
        List<String> comments = new ArrayList<>();

        commentInteractor.executeLoadComments("Person1", "Person2");

        verify(commentOutputBoundary, times(1)).prepareSuccessView(comments);
    }

    @Test
    public void testSwitchToFriendPlaylistView() {
        commentInteractor.switchToFriendPlaylistView();
        verify(commentOutputBoundary, times(1)).switchToFriendPlaylistView();
    }

    @Test
    public void testSaveCommentFailure() throws DataAccessException {
        when(commentDataAccessInterface
                .saveCommentFromUser("Playlist1", "Person1", "hello"))
                .thenThrow(new DataAccessException("friend not found"));

        commentInteractor.executeSaveComment("hello", "Person1", "Playlist1");

        verify(commentOutputBoundary, times(1)).prepareFailView("friend not found");
    }

    @Test
    public void testLoadCommentsFailure() throws DataAccessException {
        when(commentDataAccessInterface
                .loadCommentsFromUser("Playlist1", "Person1"))
                .thenThrow(new DataAccessException("friend not found"));

        commentInteractor.executeLoadComments("Person1", "Playlist1");

        verify(commentOutputBoundary, times(1)).prepareFailView("friend not found");
    }

}
