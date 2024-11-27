package interface_adapter.playlist_user_story.playlist;

import interface_adapter.ViewModel;

/**
 * The ViewModel for a Playlist.
 */
public class PlaylistViewModel extends ViewModel<PlaylistState> {
    public PlaylistViewModel() {
        super("playlist");
        setState(new PlaylistState());
    }
}
