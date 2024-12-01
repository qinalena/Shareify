package use_case.playlist_collection_user_story.playlist_collection;

import entity.Playlist;
import entity.Song;
import use_case.DataAccessException;

import java.util.List;

/**
 * The "Use Case Interactor" for our playlist collection related use cases of creating
 * a playlist.
 */

public class PlaylistCollectionInteractor implements PlaylistCollectionInputBoundary {
    private final PlaylistCollectionDataAccessInterface playlistCollectionDataAccessObject;
    private final PlaylistCollectionOutputBoundary playlistCollectionPresenter;

    public PlaylistCollectionInteractor(PlaylistCollectionDataAccessInterface playlistCollectionDataAccessObject,
                                        PlaylistCollectionOutputBoundary playlistCollectionPresenter) {
        this.playlistCollectionDataAccessObject = playlistCollectionDataAccessObject;
        this.playlistCollectionPresenter = playlistCollectionPresenter;
    }

    @Override
    public void addPlaylist(String playlistName) {
        if (playlistName == null || playlistName.isEmpty()) {
            playlistCollectionPresenter.prepareFailView("Playlist name cannot be empty.");
            return;
        }
        try {
            playlistCollectionDataAccessObject.addPlaylistToUser(playlistName);
            playlistCollectionPresenter.preparePlaylistAddedView(playlistName);
        }
        catch (DataAccessException e) {
            playlistCollectionPresenter.prepareFailView(e.getMessage());
        }
    }

    @Override
    public void removePlaylist(String playlistName) {
        playlistCollectionPresenter.preparePlaylistRemovedView(playlistName);
    }

    @Override
    public void switchToPlaylistView(String playlistName) {
        if (playlistName == null) {
            playlistCollectionPresenter.prepareFailView("Must select a playlist.");
        }
        else {
            // Actual code getting Playlist Collection from the DB (PlaylistCollectionOutputData might be redundant; pass playlist directly to presenter)
            try {
                List<Playlist> playlistCollection = playlistCollectionDataAccessObject.getPlaylistCollection();

                for (Playlist playlist : playlistCollection) {
                    if (playlist.getName().equals(playlistName)) {
                        PlaylistCollectionOutputData playlistCollectionOutputData = new PlaylistCollectionOutputData(playlist);
                        playlistCollectionPresenter.switchToPlaylistView(playlistCollectionOutputData, playlistName);
                    }
                }
            }
            catch (DataAccessException exception) {}

            // Hard coded playlist collection example
//            Playlist playlistTest = new Playlist(playlistName);
//            playlistTest.addSong(new Song("Starships", new String[]{"Nicki Minaj"}));
//
//            PlaylistCollectionOutputData playlistCollectionOutputData = new PlaylistCollectionOutputData(playlistTest);
//            playlistCollectionPresenter.switchToPlaylistView(playlistCollectionOutputData, playlistName);
        }
    }
}
