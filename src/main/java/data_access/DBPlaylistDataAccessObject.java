package data_access;

import entity.Playlist;
import use_case.note.playlist_collection.PlaylistCollectionDataAccessInterface;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DBPlaylistDataAccessObject implements PlaylistCollectionDataAccessInterface {

    private Map<Integer, Playlist> playlists = new HashMap<>();
    private int nextId = 1;

    @Override
    public Playlist savePlaylist(Playlist playlist) {
        playlist.setId(nextId++);
        playlists.put(playlist.getId(), playlist);
        return playlist;
    }

    @Override
    public List<Playlist> getAllPlaylistsForUser(int userId) {
        return playlists.values().stream()
                .filter(p -> p.getId() == userId)
                .collect(Collectors.toList());
    }

    @Override
    public Playlist getPlaylistById(int playlistId) {
        return playlists.get(playlistId);
    }

    @Override
    public void deletePlaylist(int playlistId) {
        playlists.remove(playlistId);
    }
}
