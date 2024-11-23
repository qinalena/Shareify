package interface_adapter.friend_profile_playlists;

import java.util.ArrayList;
import java.util.List;

public class FriendProfilePlaylistsState {

    private String playlistError;
    private List<String> playlistList = new ArrayList<>();

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
}
