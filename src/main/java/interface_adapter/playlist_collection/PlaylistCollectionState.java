package interface_adapter.playlist_collection;

/**
 * The state for Playlist Collection View Model.
 */

public class PlaylistCollectionState {
    private String addPlaylist = "";
    private String removePlaylist = "";
    private String playlistError;

    public String getAddPlaylist() {
        return addPlaylist;
    }

    public String getRemovePlaylist() {
        return removePlaylist;
    }

    public String getPlaylistError() {
        return playlistError;
    }

    public void setAddPlaylist(String addPlaylist) {
        this.addPlaylist = addPlaylist;
    }

    public void setRemovePlaylist(String removePlaylist) {
        this.removePlaylist = removePlaylist;
    }

    public void setPlaylistError(String playlistError) {
        this.playlistError = playlistError;
    }
}
