package interface_adapter.playlist;

import interface_adapter.ViewModel;

/**
 * The View Model for the Playlist view.
 */
public class PlaylistViewModel extends ViewModel<PlaylistState> {

    public PlaylistViewModel() {
        super("Playlist");
        setState(new PlaylistState());
    }
}
