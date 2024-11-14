package use_case.note.playlist_collection;

import data_access.DBPlaylistDataAccessObject;
import entity.Playlist;

import java.util.List;

/**
 * The "Use Case Interactor" for our playlist collection related use cases of creating
 * a playlist.
 */

public class PlaylistCollectionInteractor implements PlaylistCollectionInputBoundary {

    private DBPlaylistDataAccessObject playlistDataAccessObject;

    public PlaylistCollectionInteractor(DBPlaylistDataAccessObject playlistDataAccessObject) {
        this.playlistDataAccessObject = playlistDataAccessObject;
    }

    public Playlist createPlaylist(int userId, String name, List<Integer> songIds, String privacy) {
        Playlist playlist = new Playlist(userId, name, songIds, privacy);
        return playlistDataAccessObject.savePlaylist(playlist);
    }

    public List<Playlist> getAllPlaylists(int userId) {
        return playlistDataAccessObject.getAllPlaylistsForUser(userId);
    }

    public void deletePlaylist(int playlistId) {
        playlistDataAccessObject.deletePlaylist(playlistId);
    }
}
