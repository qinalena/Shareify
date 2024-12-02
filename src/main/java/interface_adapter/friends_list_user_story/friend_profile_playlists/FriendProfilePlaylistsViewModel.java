package interface_adapter.friends_list_user_story.friend_profile_playlists;

import interface_adapter.ViewModel;

/**
 * View model for FriendProfilePlaylists.
 */
public class FriendProfilePlaylistsViewModel extends ViewModel<FriendProfilePlaylistsState> {
    public FriendProfilePlaylistsViewModel() {
        super("friendProfilePlaylistCollection");
        setState(new FriendProfilePlaylistsState());
    }
}
