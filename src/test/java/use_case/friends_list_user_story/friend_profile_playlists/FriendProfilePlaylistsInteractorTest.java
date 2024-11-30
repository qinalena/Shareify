//package use_case.friends_list_user_story.friend_profile_playlists;
//
//import org.junit.Test;
//import static org.junit.Assert.*;
//
//public class FriendProfilePlaylistsInteractorTest {
//
//    @Test
//    public void testAddPlaylistValidName() {
//        // Arrange
//        FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter = new FriendProfilePlaylistsOutputBoundary() {
//            @Override
//            public void prepareFailView(String errorMessage) {
//                fail("Error should not be presented");
//            }
//
//            @Override
//            public void preparePlaylistAddedView(String playlist) {
//                assertEquals("ValidPlaylist", playlist);
//            }
//
//            @Override
//            public void preparePlaylistRemovedView(String playlist) {}
//
//            @Override
//            public void switchToFriendProfileView(String selectedFriendName, String password) {}
//        };
//
//        FriendProfilePlaylistsInteractor friendProfilePlaylistsInteractor = new FriendProfilePlaylistsInteractor(friendProfilePlaylistsPresenter);
//
//        // Act
//        friendProfilePlaylistsInteractor.addPlaylist("ValidPlaylist");
//
//        // Assert
//        // The assertion is done within the friendProfilePlaylistsPresenter's preparePlaylistAddedView method.
//    }
//
//    @Test
//    public void testAddPlaylistEmptyName() {
//        // Arrange
//        FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter = new FriendProfilePlaylistsOutputBoundary() {
//            @Override
//            public void prepareFailView(String errorMessage) {
//                assertEquals("Error", errorMessage);
//            }
//
//            @Override
//            public void preparePlaylistAddedView(String playlist) {
//                fail("Playlist should not be added");
//            }
//
//            @Override
//            public void preparePlaylistRemovedView(String playlist) {}
//
//            @Override
//            public void switchToFriendProfileView(String selectedFriendName, String password) {}
//        };
//
//        FriendProfilePlaylistsInteractor friendProfilePlaylistsInteractor = new FriendProfilePlaylistsInteractor(friendProfilePlaylistsPresenter);
//
//        // Act
//        friendProfilePlaylistsInteractor.addPlaylist("");
//
//        // Assert
//        // The assertion is done within the friendProfilePlaylistsPresenter's prepareFailView method.
//    }
//
//    @Test
//    public void testAddPlaylistNullName() {
//        // Arrange
//        FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter = new FriendProfilePlaylistsOutputBoundary() {
//            @Override
//            public void prepareFailView(String errorMessage) {
//                assertEquals("Error", errorMessage);
//            }
//
//            @Override
//            public void preparePlaylistAddedView(String playlist) {
//                fail("Playlist should not be added");
//            }
//
//            @Override
//            public void preparePlaylistRemovedView(String playlist) {}
//
//            @Override
//            public void switchToFriendProfileView(String selectedFriendName, String password) {}
//        };
//
//        FriendProfilePlaylistsInteractor friendProfilePlaylistsInteractor = new FriendProfilePlaylistsInteractor(friendProfilePlaylistsPresenter);
//
//        // Act
//        friendProfilePlaylistsInteractor.addPlaylist(null);
//
//        // Assert
//        // The assertion is done within the friendProfilePlaylistsPresenter's prepareFailView method.
//    }
//
//    @Test
//    public void testRemovePlaylist() {
//        // Arrange
//        FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter = new FriendProfilePlaylistsOutputBoundary() {
//            @Override
//            public void prepareFailView(String errorMessage) {}
//
//            @Override
//            public void preparePlaylistAddedView(String playlist) {}
//
//            @Override
//            public void preparePlaylistRemovedView(String playlist) {
//                assertEquals("RemovedPlaylist", playlist);
//            }
//
//            @Override
//            public void switchToFriendProfileView(String selectedFriendName, String password) {}
//        };
//
//        FriendProfilePlaylistsInteractor friendProfilePlaylistsInteractor = new FriendProfilePlaylistsInteractor(friendProfilePlaylistsPresenter);
//
//        // Act
//        friendProfilePlaylistsInteractor.removePlaylist("RemovedPlaylist");
//
//        // Assert
//        // The assertion is done within the friendProfilePlaylistsPresenter's preparePlaylistRemovedView method.
//    }
//
//    @Test
//    public void testSwitchToFriendProfileView() {
//        // Arrange
//        FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter = new FriendProfilePlaylistsOutputBoundary() {
//            @Override
//            public void prepareFailView(String errorMessage) {}
//
//            @Override
//            public void preparePlaylistAddedView(String playlist) {}
//
//            @Override
//            public void preparePlaylistRemovedView(String playlist) {}
//
//            @Override
//            public void switchToFriendProfileView(String selectedFriendName, String password) {
//                assertEquals("TestSelectedFriend", selectedFriendName);
//                assertEquals("TestPassword", password);
//            }
//        };
//
//        FriendProfilePlaylistsInteractor friendProfilePlaylistsInteractor = new FriendProfilePlaylistsInteractor(friendProfilePlaylistsPresenter);
//
//        // Act
//        friendProfilePlaylistsInteractor.switchToFriendProfileView("TestSelectedFriend", "TestPassword");
//
//        // Assert
//        // The assertion is done within the friendProfilePlaylistsPresenter's switchToFriendProfileView method.
//    }
//
//    @Test(expected = NullPointerException.class)
//    public void testConstructorWithNullPresenter() {
//        new FriendProfilePlaylistsInteractor(null);
//    }
//}