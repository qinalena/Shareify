package use_case.note;

import entity.User;
import org.junit.Test;
import use_case.user_profile_user_story.note.NoteDataAccessInterface;
import use_case.user_profile_user_story.note.NoteInteractor;
import use_case.user_profile_user_story.note.NoteOutputBoundary;

import static org.junit.Assert.*;

public class NoteInteractorTest {

    @Test
    public void testExecuteRefreshSuccess() {

        NoteDataAccessInterface noteDAO = new NoteDataAccessInterface() {


            @Override
            public String saveNote(User user, String note) {
                return "";
            }


            @Override
            public String loadNote(User user) {
                return "test";
            }
        };

        NoteOutputBoundary noteOB = new NoteOutputBoundary() {
            @Override
            public void prepareSuccessView(String message) {
                assertEquals("test", message);
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail(errorMessage);
            }

            @Override
            public void switchToUserProfileView() {
            }
        };

        NoteInteractor noteInteractor = new NoteInteractor(noteDAO, noteOB);

        noteInteractor.executeRefresh();


    }
}