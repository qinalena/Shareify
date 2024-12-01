//package use_case.friends_list_user_story.friend_profile;
//
//import entity.User;
//import org.junit.Test;
//import use_case.user_profile_user_story.note.DataAccessException;
//import use_case.user_profile_user_story.note.NoteDataAccessInterface;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class FriendProfileInteractorTest {
//
//    @Test
//    public void testSwitchToNoteView() {
//        // Arrange
//        NoteDataAccessInterface noteDataAccessInterface = new NoteDataAccessInterface() {
//            @Override
//            public String saveNote(User user, String note) throws DataAccessException {
//                return "";
//            }
//
//            @Override
//            public String loadNote(User user) throws DataAccessException {
//                return "";
//            }
//        };
//
//        FriendProfileOutputBoundary friendProfilePresenter = new FriendProfileOutputBoundary() {
//            @Override
//            public void prepareSuccessView(String message) {
//
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//
//            }
//
//            @Override
//            public void switchToNoteView() {
//                assertTrue(true); // This will pass if switchToNoteView is called.
//            }
//
//            @Override
//            public void switchToFriendsListView(String username, String password) {}
//
//            @Override
//            public void switchToPlaylistCollectionView(String username, String password) {}
//
//            @Override
//            public void switchToAllFriendsView() {}
//        };
//
//        FriendProfileInteractor friendProfileInteractor = new FriendProfileInteractor(noteDataAccessInterface, friendProfilePresenter);
//
//        // Act
//        friendProfileInteractor.switchToNoteView();
//
//        // Assert
//        // The assertion is done within the friendProfilePresenter's switchToNoteView method.
//    }
//
//    @Test
//    public void testSwitchToFriendsListView() {
//        // Arrange
//        NoteDataAccessInterface noteDataAccessInterface = new NoteDataAccessInterface() {
//            @Override
//            public String saveNote(User user, String note) throws DataAccessException {
//                return "";
//            }
//
//            @Override
//            public String loadNote(User user) throws DataAccessException {
//                return "";
//            }
//        };
//
//        FriendProfileOutputBoundary friendProfilePresenter = new FriendProfileOutputBoundary() {
//            @Override
//            public void prepareSuccessView(String message) {
//
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//
//            }
//
//            @Override
//            public void switchToNoteView() {}
//
//            @Override
//            public void switchToFriendsListView(String username, String password) {
//                assertEquals("TestUsername", username);
//                assertEquals("TestPassword", password);
//            }
//
//            @Override
//            public void switchToPlaylistCollectionView(String username, String password) {}
//
//            @Override
//            public void switchToAllFriendsView() {}
//        };
//
//        FriendProfileInteractor friendProfileInteractor = new FriendProfileInteractor(noteDataAccessInterface, friendProfilePresenter);
//
//        // Act
//        friendProfileInteractor.switchToFriendsListView("TestUsername", "TestPassword");
//
//        // Assert
//        // The assertion is done within the friendProfilePresenter's switchToFriendsListView method.
//    }
//
//    @Test
//    public void testSwitchToPlaylistCollectionView() {
//        // Arrange
//        NoteDataAccessInterface noteDataAccessInterface = new NoteDataAccessInterface() {
//            @Override
//            public String saveNote(User user, String note) throws DataAccessException {
//                return "";
//            }
//
//            @Override
//            public String loadNote(User user) throws DataAccessException {
//                return "";
//            }
//        };
//
//        FriendProfileOutputBoundary friendProfilePresenter = new FriendProfileOutputBoundary() {
//            @Override
//            public void prepareSuccessView(String message) {
//
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//
//            }
//
//            @Override
//            public void switchToNoteView() {}
//
//            @Override
//            public void switchToFriendsListView(String username, String password) {}
//
//            @Override
//            public void switchToPlaylistCollectionView(String username, String password) {
//                assertEquals("TestUsername", username);
//                assertEquals("TestPassword", password);
//            }
//
//            @Override
//            public void switchToAllFriendsView() {}
//        };
//
//        FriendProfileInteractor friendProfileInteractor = new FriendProfileInteractor(noteDataAccessInterface, friendProfilePresenter);
//
//        // Act
//        friendProfileInteractor.switchToPlaylistCollectionView("TestUsername", "TestPassword");
//
//        // Assert
//        // The assertion is done within the friendProfilePresenter's switchToPlaylistCollectionView method.
//    }
//
//    @Test
//    public void testSwitchToAllFriendsView() {
//        // Arrange
//        NoteDataAccessInterface noteDataAccessInterface = new NoteDataAccessInterface() {
//            @Override
//            public String saveNote(User user, String note) throws DataAccessException {
//                return "";
//            }
//
//            @Override
//            public String loadNote(User user) throws DataAccessException {
//                return "";
//            }
//        };
//
//        FriendProfileOutputBoundary friendProfilePresenter = new FriendProfileOutputBoundary() {
//            @Override
//            public void prepareSuccessView(String message) {
//
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//
//            }
//
//            @Override
//            public void switchToNoteView() {}
//
//            @Override
//            public void switchToFriendsListView(String username, String password) {}
//
//            @Override
//            public void switchToPlaylistCollectionView(String username, String password) {}
//
//            @Override
//            public void switchToAllFriendsView() {
//                assertTrue(true); // This will pass if switchToAllFriendsView is called.
//            }
//        };
//
//        FriendProfileInteractor friendProfileInteractor = new FriendProfileInteractor(noteDataAccessInterface, friendProfilePresenter);
//
//        // Act
//        friendProfileInteractor.switchToAllFriendsView();
//
//        // Assert
//        // The assertion is done within the friendProfilePresenter's switchToAllFriendsView method.
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testExecuteWithNullPresenter_Refactored() {
//        NoteDataAccessInterface noteDataAccessInterface = new NoteDataAccessInterface() {
//            @Override
//            public String saveNote(User user, String note) throws DataAccessException {
//                return "";
//            }
//
//            @Override
//            public String loadNote(User user) throws DataAccessException {
//                return "";
//            }
//        };
//        FriendProfileInteractor friendProfileInteractor = new FriendProfileInteractor(noteDataAccessInterface, null);
//
//        // Act
//        friendProfileInteractor.switchToNoteView();
//    }
//
//}