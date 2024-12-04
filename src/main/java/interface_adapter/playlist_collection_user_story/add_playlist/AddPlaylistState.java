package interface_adapter.playlist_collection_user_story.add_playlist;

import java.util.List;

/**
 * State for addPlaylist View Model.
 */
public class AddPlaylistState {
    private String errorMessage;
    private List<String> playlists;
    private String username;
    private String password;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<String> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<String> playlists) {
        this.playlists = playlists;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
