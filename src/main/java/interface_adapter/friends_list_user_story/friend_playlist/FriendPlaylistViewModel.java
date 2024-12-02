package interface_adapter.friends_list_user_story.friend_playlist;

import interface_adapter.ViewModel;

public class FriendPlaylistViewModel extends ViewModel<FriendPlaylistState> {
    public FriendPlaylistViewModel() {
        super("friendPlaylist");
        setState(new FriendPlaylistState());
    }
}
