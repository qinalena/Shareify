package use_case.friends_list_user_story.friend_profile_playlists;

import data_access.DataAccessException;
import entity.Playlist;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsViewModel;
import use_case.friends_list_user_story.friend_playlist.FriendPlaylistDataAccessInterface;

public class FriendProfilePlaylistsInteractor implements FriendProfilePlaylistsInputBoundary {
    private final FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter;
    private final FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel;
    private final FriendPlaylistDataAccessInterface playlistDataAccessObject;

    public FriendProfilePlaylistsInteractor(FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter,
                                            FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel,
                                            FriendPlaylistDataAccessInterface playlistDataAccessObject) {
        this.friendProfilePlaylistsViewModel = friendProfilePlaylistsViewModel;
        this.friendProfilePlaylistsPresenter = friendProfilePlaylistsPresenter;
        this.playlistDataAccessObject = playlistDataAccessObject;
    }

    @Override
    public void switchToFriendProfileView(String selectedFriendName, String password) {
        friendProfilePlaylistsPresenter.switchToFriendProfileView(selectedFriendName, password);
    }

    @Override
    public void prepareFailView(String error) {
        friendProfilePlaylistsViewModel.getState().setPlaylistError(error);
        friendProfilePlaylistsViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPlaylistView(String playlistName, String username, String password) {
        if (playlistName == null) {
            friendProfilePlaylistsPresenter.prepareFailView("Must select a playlist.");
        }
        else {
            try {
                final Playlist playlist = playlistDataAccessObject.getFriendPlaylist(username, playlistName);

                friendProfilePlaylistsPresenter.switchToPlaylistView(playlist, username, password);
            } catch (DataAccessException exception) {
                friendProfilePlaylistsPresenter.prepareFailView(exception.getMessage());
            }
        }
    }
}
