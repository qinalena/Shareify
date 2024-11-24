package interface_adapter.friends_list_user_story.friend_profile_playlists;

import java.util.ArrayList;
import java.util.List;

public class FriendProfilePlaylistsState {

    private String playlistError;
    private List<String> playlistList = new ArrayList<>();
    private String username;
    private String password;

    public List<String> getPlaylist() {
        return new ArrayList<>(playlistList);
    }

    public void addPlaylist(String playlist) {
        playlistList.add(playlist);
    }

    public void removePlaylist(String playlist) {
        playlistList.remove(playlist);
    }

    public String getPlaylistError() {
        return playlistError;
    }

    public void setPlaylistError(String playlistError) {
        this.playlistError = playlistError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
