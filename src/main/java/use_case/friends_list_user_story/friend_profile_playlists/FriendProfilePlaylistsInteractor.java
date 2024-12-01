package use_case.friends_list_user_story.friend_profile_playlists;

import entity.Playlist;
import entity.Song;
import interface_adapter.friends_list_user_story.friend_profile_playlists.FriendProfilePlaylistsViewModel;
import use_case.playlist_collection_user_story.playlist_collection.PlaylistCollectionOutputData;

public class FriendProfilePlaylistsInteractor implements FriendProfilePlaylistsInputBoundary {
    private final FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter;
    private final FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel;

    public FriendProfilePlaylistsInteractor(FriendProfilePlaylistsOutputBoundary friendProfilePlaylistsPresenter, FriendProfilePlaylistsViewModel friendProfilePlaylistsViewModel) {
        this.friendProfilePlaylistsViewModel = friendProfilePlaylistsViewModel;
        this.friendProfilePlaylistsPresenter = friendProfilePlaylistsPresenter;
    }

    @Override
    public void switchToFriendProfileView(String selectedFriendName, String password) {
        friendProfilePlaylistsPresenter.switchToFriendProfileView(selectedFriendName, password);
    }

    @Override
    public void prepareFailView(String error) {
        friendProfilePlaylistsViewModel.getState().setPlaylistError(error);
        friendProfilePlaylistsViewModel.firePropertyChanged();
    }

    @Override
    public void switchToPlaylistView(String playlistName, String username, String password) {
        if (playlistName == null) {
            friendProfilePlaylistsPresenter.prepareFailView("Must select a playlist.");
        }
        else {
            // Hard coded playlist collection example
            // Actual code should search the DB using the playlist name and then
            // populate a playlist in PlaylistCollectionOutputData (convert JSON string values into Song objects)
            Playlist playlistTest = new Playlist("Playlist1");
            playlistTest.addSong(new Song("Starships", new String[]{"Nicki Minaj"}));

            PlaylistCollectionOutputData playlistCollectionOutputData = new PlaylistCollectionOutputData(playlistTest);
            friendProfilePlaylistsPresenter.switchToPlaylistView(playlistCollectionOutputData, playlistName, username, password);
        }
    }

}
