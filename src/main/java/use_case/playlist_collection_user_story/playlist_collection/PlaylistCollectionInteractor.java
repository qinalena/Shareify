package use_case.playlist_collection_user_story.playlist_collection;

import entity.Playlist;
import entity.Track;

import java.util.ArrayList;
import java.util.HashMap;
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
        playlistCollectionPresenter.preparePlaylistAddedView(playlistName);
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
            // Hard coded playlist collection example
            HashMap<String, Playlist> playlistCollectionTest = new HashMap<String, Playlist>();
            Playlist playlistTest = new Playlist("Playlist1");
            playlistTest.addTrack(new Track("testid", "Starships", "Nicki Minaj"));
            playlistCollectionTest.put(playlistTest.getName(), playlistTest);

            PlaylistCollectionOutputData playlistCollectionOutputData = new PlaylistCollectionOutputData(playlistCollectionTest);
            playlistCollectionPresenter.switchToPlaylistView(playlistCollectionOutputData, playlistName);
        }
    }
}