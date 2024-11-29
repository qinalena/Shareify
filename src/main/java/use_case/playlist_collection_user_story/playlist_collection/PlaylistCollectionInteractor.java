package use_case.playlist_collection_user_story.playlist_collection;

import entity.Playlist;
import entity.Song;

/**
 * The "Use Case Interactor" for our playlist collection related use cases of creating
 * a playlist.
 */

public class PlaylistCollectionInteractor implements PlaylistCollectionInputBoundary {
    private final PlaylistCollectionOutputBoundary playlistCollectionPresenter;

    public PlaylistCollectionInteractor(PlaylistCollectionOutputBoundary playlistCollectionPresenter) {
        this.playlistCollectionPresenter = playlistCollectionPresenter;
    }

    @Override
    public void addPlaylist(String playlistName) {
        if (playlistName == null || playlistName.isEmpty()) {
            playlistCollectionPresenter.prepareFailView("Playlist name cannot be empty.");
        }
        else {
            playlistCollectionPresenter.preparePlaylistAddedView(playlistName);
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
            // Hard coded playlist collection example
            // Actual code should search the DB using the playlist name and then
            // populate a playlist in PlaylistCollectionOutputData (convert JSON string values into Song objects)
            Playlist playlistTest = new Playlist("Playlist1");
            playlistTest.addSong(new Song("Starships", new String[]{"Nicki Minaj"}));

            PlaylistCollectionOutputData playlistCollectionOutputData = new PlaylistCollectionOutputData(playlistTest);
            playlistCollectionPresenter.switchToPlaylistView(playlistCollectionOutputData, playlistName);
        }
    }

    @Override
    public void switchToUserProfileView() {
        playlistCollectionPresenter.switchToUserProfileView();
    }

    @Override
    public void switchToAddPlaylistView() {
        playlistCollectionPresenter.switchToAddPlaylistView();
    }
}
