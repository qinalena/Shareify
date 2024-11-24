package interface_adapter.add_playlist;

import interface_adapter.ViewModel;

public class AddPlaylistViewModel extends ViewModel<AddPlaylistState> {

    private String newPlaylist;

    public AddPlaylistViewModel() {
        super("addPlaylist");
        setState(new AddPlaylistState());
    }

    public String getNewPlaylist() {
        return newPlaylist;
    }

    public void setNewPlaylist(String newPlaylist) {
        this.newPlaylist = newPlaylist;
    }
}