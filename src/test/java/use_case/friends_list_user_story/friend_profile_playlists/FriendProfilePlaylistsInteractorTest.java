package use_case.friends_list_user_story.friend_profile_playlists;

import data_access.DataAccessException;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsState;
import org.junit.Test;
import static org.junit.Assert.*;

import entity.Playlist;
import entity.Song;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.friends_list_user_story.friend_playlist.FriendPlaylistDataAccessInterface;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FriendProfilePlaylistsInteractorTest {

    private FriendProfilePlaylistsInteractor interactor;
    private FriendProfilePlaylistsOutputBoundary presenter;
    private FriendProfilePlaylistsViewModel viewModel;
    private FriendPlaylistDataAccessInterface playlistDAI;

    @Before
    public void setUp() {
        presenter = mock(FriendProfilePlaylistsOutputBoundary.class);
        viewModel = mock(FriendProfilePlaylistsViewModel.class);
        playlistDAI = mock(FriendPlaylistDataAccessInterface.class);
        interactor = new FriendProfilePlaylistsInteractor(presenter, viewModel, playlistDAI);
    }

    @Test
    public void testSwitchToFriendProfileView() {
        String friendName = "FriendUser";
        String password = "password123";

        interactor.switchToFriendProfileView(friendName, password);

        verify(presenter, times(1)).switchToFriendProfileView(friendName, password);
    }

    @Test
    public void testPrepareFailView() {
        // Mock the state object
        FriendProfilePlaylistsState mockState = mock(FriendProfilePlaylistsState.class);

        // Stub the view model to return the mock state
        when(viewModel.getState()).thenReturn(mockState);

        // Simulate the interactor's prepareFailView call
        String error = "Test error";
        interactor.prepareFailView(error);

        // Verify the mock state's methods were called
        verify(mockState, times(1)).setPlaylistError(error);

        // Verify that property change event was fired
        verify(viewModel, times(1)).firePropertyChanged();
    }

    @Test
    public void testSwitchToPlaylistViewSuccess() throws DataAccessException {
        String playlistName = "Playlist1";
        String username = "User1";
        String password = "password123";

        // Mock the playlist retrieval to return a valid playlist
        when(playlistDAI.getFriendPlaylist(username, playlistName)).thenReturn(new Playlist(playlistName));

        interactor.switchToPlaylistView(playlistName, username, password);

        // Use Mockito's argThat matcher to verify the Playlist object
        verify(presenter, times(1)).switchToPlaylistView(argThat(playlist -> playlist.getName().equals(playlistName)), eq(username), eq(password));
    }

    @Test
    public void testSwitchToPlaylistViewFailure() throws DataAccessException{
        String playlistName = "Playlist1";
        String username = "User1";
        String password = "password123";

        // Mock the playlist retrieval to throw a DataAccessException
        when(playlistDAI.getFriendPlaylist(username, playlistName)).thenThrow(new DataAccessException("Test error message"));

        interactor.switchToPlaylistView(playlistName, username, password);

        verify(presenter, times(1)).prepareFailView("Test error message");
    }

    @Test
    public void testSwitchToPlaylistViewNullPlaylistName() {
        String playlistName = null;
        String username = "User1";
        String password = "password123";

        interactor.switchToPlaylistView(playlistName, username, password);

        verify(presenter, times(1)).prepareFailView("Must select a playlist.");
    }
}