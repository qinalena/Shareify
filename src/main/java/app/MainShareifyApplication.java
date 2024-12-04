package app;

import data_access.DBUserDataAccessObject;
import data_access.spotify_api.SpotifyClientCredAuth;
import data_access.spotify_api.SpotifyDataAccessObject;
import use_case.playlist_user_story.search_song.SpotifyAuthorizationInterface;
import use_case.playlist_user_story.search_song.SpotifyDataAccessInterface;
import use_case.user_profile_user_story.note.NoteDataAccessInterface;

/**
 * The Shareify Application which supports signup/login/logout, creating user profiles,
 * managing playlist collections, managing friends list, and chatting with friends.
 */
public class MainShareifyApplication {

    /**
     * The main entry point of the application.
     * @param args commandline arguments are ignored
     */
    public static void main(String[] args) {

        // create the data access and inject it into our builder!
        final NoteDataAccessInterface noteDAO = new DBUserDataAccessObject();

        // create Spotify data access and inject it into our builder!
        final SpotifyAuthorizationInterface spotifyAuthorization = new SpotifyClientCredAuth();
        final SpotifyDataAccessInterface spotifyDAO = new SpotifyDataAccessObject(spotifyAuthorization);

        // ADD ALL VIEWS FIRST BEFORE USE CASES!!!
        final ShareifyAppBuilder shareifyAppBuilder = new ShareifyAppBuilder();
        shareifyAppBuilder
                .addNoteDAO(noteDAO)
                .addSpotifyDAO(spotifyDAO)
                .addWelcomeView()
                .addSignupView()
                .addLoginView()
                .addUserProfileView()
                .addNoteView()
                .addChangePasswordView()
                .addPlaylistCollectionView()
                .addAddPlaylistView()
                .addFriendsListView()
                .addAddFriendView()
                .addFriendProfileView()
                .addPlaylistView()
                .addSearchTrackView()
                .addFriendProfilePlaylistView()
                .addfriendProfileFriendsListView()
                .addFriendPlaylistView()
                .addCommentView()
                .addChatView()
                .addSignupUseCase()
                .addLoginUseCase()
                .addUserProfileUseCase()
                .addNoteUseCase()
                .addChangePasswordUseCase()
                .addLogoutUseCase()
                .addPlaylistCollectionUseCase()
                .addAddPlaylistUseCase()
                .addFriendsListUseCase()
                .addAddFriendUseCase()
                .addPlaylistUseCase()
                .addSearchTrackUseCase()
                .addFriendProfileUseCase()
                .addFriendProfilePlaylistUseCase()
                .addFriendProfileFriendsListUseCase()
                .addFriendPlaylistUseCase()
                .addChatUseCase()
                .addCommentUseCase()
                .build().setVisible(true);
    }
}
