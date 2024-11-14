package interface_adapter.playlist_collection;

import interface_adapter.ViewModel;

/**
 * The View Model for the Playlist Collection view.
 */
public class PlaylistCollectionViewModel extends ViewModel<PlaylistCollectionState> {

    public PlaylistCollectionViewModel() {
        super("Playlist");
        setState(new PlaylistCollectionState());
    }

    public void createNewPlaylist() {
        // Handle the creation of a new playlist (to be implemented later)
        System.out.println("New playlist created!");
    }
}
