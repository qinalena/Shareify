package use_case.user_profile.note;
import data_access.DataAccessException;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.user_profile_user_story.note.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class NoteInteractorTest {
    private NoteInteractor noteInteractor;
    private NoteOutputBoundary notePresenter;
    private NoteDataAccessInterface noteDataAccess;

    @BeforeEach
    public void setUp(){
        noteDataAccess = mock(NoteDataAccessInterface.class);
        notePresenter = mock(NoteOutputBoundary.class);
        noteInteractor = new NoteInteractor(noteDataAccess, notePresenter);
    }

    @Test
    public void testSaveNote() throws DataAccessException {
        String username = "NewUser";
        String note = "Test note";
        String updateNote = "Test updateNote";
        NoteInputData inputData = new NoteInputData(username, updateNote);

        User mockUser = mock(User.class);
        when(noteDataAccess.getUser(username)).thenReturn(mockUser);
        when(mockUser.getNote()).thenReturn(note);

        noteInteractor.executeSave(inputData);

        verify(noteDataAccess).saveNote(mockUser, updateNote);
        verify(notePresenter).prepareSuccessView(any(NoteOutputData.class));
    }

    @Test
    public void testSwitchToUserprofileView(){
        noteInteractor.switchToUserProfileView();
        verify(notePresenter, times(1)).switchToUserProfileView();
    }

    @Test
    public void testGetUsername() {
        String username = "NewUser";
        String note = "Test note";
        NoteOutputData outputData = new NoteOutputData(username, note);

        String actualUsername = outputData.getUsername();

        assertEquals(username, actualUsername);
    }

    @Test
    public void testGetNote() {
        String username = "NewUser";
        String note = "Test note";
        NoteOutputData outputData = new NoteOutputData(username, note);

        String actualNote = outputData.getNote();

        assertEquals(note, actualNote);
    }

    @Test
    public void testSetNote() {
        String username = "NewUser";
        String note = "Test note";
        NoteOutputData outputData = new NoteOutputData(username, note);
        String expectedNote = "Updated note";

        outputData.setNote(expectedNote);

        assertEquals(expectedNote, outputData.getNote());
    }

    @Test
    public void testDataAccessException() throws DataAccessException {
        String username = "NewUser";
        String password = "123";
        String note = "Test note";

        NoteInputData inputData = new NoteInputData(username, note);

        User mockUser = new User(username, password);
        when(noteDataAccess.getUser(username)).thenReturn(mockUser);

        when(noteDataAccess.saveNote(mockUser, note)).thenThrow(new DataAccessException("message"));

        noteInteractor.executeSave(inputData);

        verify(notePresenter, times(1)).prepareFailView("message");

        verify(notePresenter, never()).prepareSuccessView(any(NoteOutputData.class));
    }
}
