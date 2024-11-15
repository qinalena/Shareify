package interface_adapter.playlist_collection;

/**
 * The state for Playlist Collection View Model.
 */

public class PlaylistCollectionState {
    private String addPlaylist;
    private String playlistError;

    public String getAddPlaylist() {
        return addPlaylist;
    }

    public String getPlaylistError() {
        return playlistError;
    }

    public void setAddPlaylist(String addPlaylist) {
        this.addPlaylist = addPlaylist;
    }

    public void setPlaylistError(String playlistError) {
        this.playlistError = playlistError;
    }
}
