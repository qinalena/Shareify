package interface_adapter.playlist;

/**
 * The View Model for the Playlist.
 */

public class PlaylistViewModel {
    
    public PlaylistViewModel() {
        super("Playlist");
        setState(new PlaylistState());
    }

    private void setState(PlaylistState playlistState) {
    }
}
