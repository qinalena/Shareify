package interface_adapter.playlist_collection;

import interface_adapter.ViewModel;

/**
 * The View Model for the Playlist Collection view.
 */
public class PlaylistCollectionViewModel extends ViewModel<PlaylistCollectionState> {

    public PlaylistCollectionViewModel() {
        super("PlaylistCollection");
        setState(new PlaylistCollectionState());
    }
}
