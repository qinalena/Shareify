package interface_adapter.friend_profile_playlists;

import interface_adapter.ViewModel;
import interface_adapter.playlist_collection.PlaylistCollectionState;

public class FriendProfilePlaylistsViewModel extends ViewModel<PlaylistCollectionState> {
    public FriendProfilePlaylistsViewModel() {
        super("friendProfilePlaylistCollection");
        setState(new PlaylistCollectionState());
    }
}