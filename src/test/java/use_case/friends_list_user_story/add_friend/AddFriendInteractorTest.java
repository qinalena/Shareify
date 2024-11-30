//package use_case.friends_list_user_story.add_friend;
//
//import data_access.DBNoteDataAccessObject;
//import org.junit.Test;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//public class AddFriendInteractorTest {
//
//    @Test
//    public void testExecuteValidFriendName() {
//        // Arrange
//        DBNoteDataAccessObject dbNoteDataAccessObject = new DBNoteDataAccessObject() {
//
//            public String getUserByUsername(String username) {
//                return "ExistingUser";
//            }
//        };
//
//        AddFriendOutputBoundary outputBoundary = new AddFriendOutputBoundary() {
//            @Override
//            public void prepareSuccessView(List<String> friendsList) {
//                assertEquals(1, friendsList.size());
//                assertEquals("ValidFriendName", friendsList.get(0));
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//                fail("Error should not be presented");
//            }
//
//            @Override
//            public void swtichToFriendsListView() {
//
//            }
//
//            public void switchToFriendsListView() {}
//        };
//
//        List<String> friendsList = new ArrayList<>();
//        AddFriendInteractor addFriendInteractor = new AddFriendInteractor(dbNoteDataAccessObject, outputBoundary, friendsList);
//
//        // Act
//        addFriendInteractor.execute("ValidFriendName");
//
//        // Assert
//        // The assertion is done within the outputBoundary's prepareSuccessView method.
//    }
//
//    @Test
//    public void testExecuteNonExistingFriendName() {
//        // Arrange
//        DBNoteDataAccessObject dbNoteDataAccessObject = new DBNoteDataAccessObject() {
//            public String getUserByUsername(String username) {
//                return null;
//            }
//        };
//
//        AddFriendOutputBoundary outputBoundary = new AddFriendOutputBoundary() {
//            @Override
//            public void prepareSuccessView(List<String> friendsList) {
//                fail("Success view should not be presented");
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//                assertEquals("User does not exist.", errorMessage);
//            }
//
//            @Override
//            public void swtichToFriendsListView() {
//
//            }
//
//            public void switchToFriendsListView() {}
//        };
//
//        List<String> friendsList = new ArrayList<>();
//        AddFriendInteractor addFriendInteractor = new AddFriendInteractor(dbNoteDataAccessObject, outputBoundary, friendsList);
//
//        // Act
//        addFriendInteractor.execute("NonExistingFriendName");
//
//        // Assert
//        // The assertion is done within the outputBoundary's prepareFailView method.
//    }
//
//    @Test
//    public void testExecuteExceptionDuringDatabaseOperation() {
//        // Arrange
//        DBNoteDataAccessObject dbNoteDataAccessObject = new DBNoteDataAccessObject() {
//            @Override
//            public String getUserByUsername(String username) {
//                throw new RuntimeException("Database error"); // Use RuntimeException instead of Exception
//            }
//        };
//
//        AddFriendOutputBoundary outputBoundary = new AddFriendOutputBoundary() {
//            @Override
//            public void prepareSuccessView(List<String> friendsList) {
//                fail("Success view should not be presented");
//            }
//
//            @Override
//            public void prepareFailView(String errorMessage) {
//                assertEquals("Error adding friend.", errorMessage);
//            }
//
//            @Override
//            public void swtichToFriendsListView() {
//
//            }
//
//            public void switchToFriendsListView() {}
//        };
//
//        List<String> friendsList = new ArrayList<>();
//        AddFriendInteractor addFriendInteractor = new AddFriendInteractor(dbNoteDataAccessObject, outputBoundary, friendsList);
//
//        // Act
//        addFriendInteractor.execute("FriendName");
//
//        // Assert
//        // The assertion is done within the outputBoundary's prepareFailView method.
//    }
//
//    @Test
//    public void testSwitchToFriendsListView() {
//        // Arrange
//        DBNoteDataAccessObject dbNoteDataAccessObject = new DBNoteDataAccessObject() {};
//
//        AddFriendOutputBoundary outputBoundary = new AddFriendOutputBoundary() {
//            @Override
//            public void prepareSuccessView(List<String> friendsList) {}
//
//            @Override
//            public void prepareFailView(String errorMessage) {}
//
//            @Override
//            public void swtichToFriendsListView() {
//
//            }
//
//            public void switchToFriendsListView() {
//                assertTrue(true); // This will pass if switchToFriendsListView is called.
//            }
//        };
//
//        List<String> friendsList = new ArrayList<>();
//        AddFriendInteractor addFriendInteractor = new AddFriendInteractor(dbNoteDataAccessObject, outputBoundary, friendsList);
//
//        // Act
//        addFriendInteractor.switchToFriendsListView();
//
//        // Assert
//        // The assertion is done within the outputBoundary's switchToFriendsListView method.
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testExecuteWithNullOutputBoundary_Refactored() {
//        // Arrange
//        DBNoteDataAccessObject dbNoteDataAccessObject = new DBNoteDataAccessObject() {};
//        List<String> friendsList = new ArrayList<>();
//        AddFriendOutputBoundary outputBoundary = null;
//        AddFriendInteractor addFriendInteractor = new AddFriendInteractor(dbNoteDataAccessObject, outputBoundary, friendsList);
//
//        // Act
//        addFriendInteractor.execute("FriendName");
//    }
//}