package use_case.playlist_collection_user_story.add_playlist;

import data_access.DBPlaylistDataAccessObject;
import data_access.DBUserDataAccessObject;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class AddPlaylistInteractorTest {
    private AddPlaylistInteractor addPlaylistInteractor;
    private AddPlaylistOutputBoundary addPlaylistOutputBoundary;
    private DBPlaylistDataAccessObject playlistDataAccessObject;
    private DBUserDataAccessObject userDataAccessObject;
    private List<String> playlistCollectionNames;
    private User mockUser;

    @BeforeEach
    void setUp() throws IllegalAccessException, NoSuchFieldException {
        MockitoAnnotations.openMocks(this);

        // Mock dependencies
        playlistDataAccessObject = mock(DBPlaylistDataAccessObject.class);
        userDataAccessObject = mock(DBUserDataAccessObject.class);
        addPlaylistOutputBoundary = mock(AddPlaylistOutputBoundary.class);

        // Mock user
        mockUser = mock(User.class);
        playlistCollectionNames = new ArrayList<>();
        when(mockUser.getInfo()).thenReturn(playlistCollectionNames);

        // Initialize interactor
        addPlaylistInteractor = new AddPlaylistInteractor(
                addPlaylistOutputBoundary
        );

        // Use reflection to inject the mock user into the private user field
        java.lang.reflect.Field userField = AddPlaylistInteractor.class.getDeclaredField("user");
        userField.setAccessible(true);
        userField.set(addPlaylistInteractor, mockUser);
    }

    @Test
    void testExecuteCreatePlaylistSuccessful() {
        // Arrange
        String newPlaylist = "Those Jams";

        // Act
        addPlaylistInteractor.executeCreatePlaylist(newPlaylist);

        // Assert
        assertTrue(playlistCollectionNames.contains(newPlaylist), "Playlist should be added to the collection.");
        verify(addPlaylistOutputBoundary, times(1)).prepareSuccessView(playlistCollectionNames);
        verify(addPlaylistOutputBoundary, never()).prepareFailureView(anyString());
    }

    @Test
    void testExecuteCreatePlaylist_duplicate() {
        // Arrange
        String existingPlaylist = "Chill Vibes";
        playlistCollectionNames.add(existingPlaylist);

        // Act
        addPlaylistInteractor.executeCreatePlaylist(existingPlaylist);

        // Assert
        verify(addPlaylistOutputBoundary, times(1)).prepareFailureView("Playlist already exists!");
        verify(addPlaylistOutputBoundary, never()).prepareSuccessView(playlistCollectionNames);
    }

    @Test
    void testSwitchToPlaylistCollectionView() {
        // Act
        addPlaylistInteractor.switchToPlaylistCollectionView();

        // Assert
        verify(addPlaylistOutputBoundary, times(1)).switchToPlaylistCollectionView();
    }
}