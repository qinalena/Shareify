package use_case.chat;

import data_access.DataAccessException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.MockitoAnnotations;
import use_case.comment.CommentDataAccessInterface;
import use_case.comment.CommentInteractor;
import use_case.comment.CommentOutputBoundary;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class ChatInteractorTest {

    private ChatDataAccessInterface chatDataAccessInterface;
    private ChatOutputBoundary chatOutputBoundary;
    private ChatInteractor chatInteractor;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        chatOutputBoundary = mock(ChatOutputBoundary.class);
        chatDataAccessInterface = mock(ChatDataAccessInterface.class);
        chatInteractor = new ChatInteractor(chatDataAccessInterface, chatOutputBoundary);
    }

    @Test
    public void testSaveMessageAndCallPresenter() {

        List<String> messages = new ArrayList<>();

        chatInteractor.executeSaveMessage("message", "Person1", "Person2");

        verify(chatOutputBoundary, times(1)).prepareSuccessView(messages);
    }

    @Test
    public void testLoadMessagesAndCallPresenter() {
        List<String> comments = new ArrayList<>();

        chatInteractor.executeLoadMessages("Person1", "Person2");

        verify(chatOutputBoundary, times(1)).prepareSuccessView(comments);
    }

    @Test
    public void testSwitchToFriendView() {
        chatInteractor.switchToFriendView();
        verify(chatOutputBoundary, times(1)).switchToFriendView();
    }

    @Test
    public void testSaveMessageFailure() throws DataAccessException {
        when(chatDataAccessInterface
                .saveChatBetweenUsers("Hello", "Person1", "Person2"))
                .thenThrow(new DataAccessException("reciever not found"));

        chatInteractor.executeSaveMessage("Hello", "Person1", "Person2");

        verify(chatOutputBoundary, times(1)).prepareFailView("reciever not found");
    }

    @Test
    public void testLoadMessagesFailure() throws DataAccessException {
        when(chatDataAccessInterface
                .loadChatBetweenUsers("Person1", "Person2"))
                .thenThrow(new DataAccessException("sender not found"));

        chatInteractor.executeLoadMessages("Person1", "Person2");

        verify(chatOutputBoundary, times(1)).prepareFailView("sender not found");
    }
}
