package use_case.playlist_collection_user_story.playlist_collection;

import data_access.DBPlaylistDataAccessObject;
import entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.michaelthelin.spotify.model_objects.specification.Playlist;
import use_case.playlist_collection_user_story.playlist_collection.*;
import data_access.DataAccessException;
import use_case.playlist_user_story.playlist.PlaylistDataAccessInterface;
import use_case.playlist_user_story.playlist.PlaylistOutputData;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlaylistCollectionInteractorTest {
    private PlaylistCollectionInteractor playlistCollectionInteractor;
    private DBPlaylistDataAccessObject playlistCollectionDB;
    private PlaylistDataAccessInterface playlistDataAccessObject;
    private PlaylistCollectionOutputBoundary playlistCollectionPresenter;
    private PlaylistCollectionOutputData playlistCollectionOutputData;

    @BeforeEach
    public void setUp() {
        playlistCollectionDB = mock(DBPlaylistDataAccessObject.class);
        playlistCollectionPresenter = mock(PlaylistCollectionOutputBoundary.class);
        playlistCollectionInteractor = new PlaylistCollectionInteractor(playlistCollectionDB,
                playlistDataAccessObject, playlistCollectionPresenter);
    }

    @Test
    void testAddPlaylistSuccessCase() throws DataAccessException{
        // Arrange
        User user = mock(User.class);
        String playlistName = "My Happy Playlist";

        //Simulate successful database operation
        doNothing().when(playlistCollectionDB).addPlaylistinDB(user, playlistName);

        // Act
        playlistCollectionInteractor.addPlaylist(user, playlistName);

        // Assert
        verify(playlistCollectionDB, times(1)).addPlaylistinDB(user, playlistName);
        verify(playlistCollectionPresenter).preparePlaylistAddedView(playlistName);
        verifyNoMoreInteractions(playlistCollectionPresenter);

    }

    @Test
    void testAddPlaylistFailCase() throws DataAccessException{
        User user = mock(User.class);
        String playlistName = "My Happy Playlist";

        doThrow(new DataAccessException("Database Error!")).when(playlistCollectionDB).addPlaylistinDB(
                user, playlistName);

        playlistCollectionInteractor.addPlaylist(user, playlistName);

        verify(playlistCollectionDB, times(1)).addPlaylistinDB(user, playlistName);
        verify(playlistCollectionPresenter).prepareFailView("Failed to add playlist: Database Error!");
    }

    @Test
    void testAddPlaylistEmptyName() {
        User user = mock(User.class);
        String playlistName = "";

        playlistCollectionInteractor.addPlaylist(user, playlistName);

        verify(playlistCollectionPresenter).prepareFailView("Playlist name cannot be empty.");
        // DB should not be called
        verifyNoMoreInteractions(playlistCollectionDB);
    }

    @Test
    void testRemovePlaylistSuccessCase() throws DataAccessException {
        User user = mock(User.class);
        String playlistName = "My Happy Playlist";

        doNothing().when(playlistCollectionDB).removePlaylistinDB(user, playlistName);

        playlistCollectionInteractor.removePlaylist(user, playlistName);

        verify(playlistCollectionDB, times(1)).removePlaylistinDB(user, playlistName);
        verify(playlistCollectionPresenter).preparePlaylistRemovedView(playlistName);
    }

    @Test
    void testRemovePlaylistFailCase() throws DataAccessException {
        User user = mock(User.class);
        String playlistName = "My Happy Playlist";

        doThrow(new DataAccessException("Database error!")).when(
                playlistCollectionDB).removePlaylistinDB(user, playlistName);

        playlistCollectionInteractor.removePlaylist(user, playlistName);

        verify(playlistCollectionDB, times(1)).removePlaylistinDB(user, playlistName);
        verify(playlistCollectionPresenter).prepareFailView("Failed to remove playlist: Database error!");
    }

    @Test
    void testSwitchToPlaylistView() {
        playlistCollectionPresenter.switchToPlaylistView(playlistCollectionOutputData);
        verify(playlistCollectionPresenter, times(1)).switchToPlaylistView(
                playlistCollectionOutputData);
    }

    @Test
    void testSwitchToUserProfileView() {
        playlistCollectionInteractor.switchToUserProfileView();
        verify(playlistCollectionPresenter, times(1)).switchToUserProfileView();
    }

    @Test
    void testSwitchToAddPlaylistView() {
        playlistCollectionInteractor.switchToAddPlaylistView();
        verify(playlistCollectionPresenter, times(1)).switchToAddPlaylistView();
    }
}