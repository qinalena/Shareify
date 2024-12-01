package use_case.friends_list_user_story.friend_profile_playlists;

import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsState;
import org.junit.Test;
import static org.junit.Assert.*;

import entity.Playlist;
import entity.Song;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsViewModel;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionOutputData;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class FriendProfilePlaylistsInteractorTest {

    private FriendProfilePlaylistsInteractor interactor;
    private FriendProfilePlaylistsOutputBoundary presenter;
    private FriendProfilePlaylistsViewModel viewModel;

    @Before
    public void setUp() {
        presenter = mock(FriendProfilePlaylistsOutputBoundary.class);
        viewModel = mock(FriendProfilePlaylistsViewModel.class);
        interactor = new FriendProfilePlaylistsInteractor(presenter, viewModel);
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
    public void testSwitchToPlaylistViewSuccess() {
        String playlistName = "Playlist1";
        String username = "User1";
        String password = "password123";

        interactor.switchToPlaylistView(playlistName, username, password);

        verify(presenter, times(1)).switchToPlaylistView(any(PlaylistCollectionOutputData.class), eq(playlistName), eq(username), eq(password));
    }

    @Test
    public void testSwitchToPlaylistViewFailure() {
        String playlistName = null;
        String username = "User1";
        String password = "password123";

        interactor.switchToPlaylistView(playlistName, username, password);

        verify(presenter, times(1)).prepareFailView("Must select a playlist.");
    }
}
