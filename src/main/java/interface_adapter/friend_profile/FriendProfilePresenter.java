package interface_adapter.friend_profile;

import interface_adapter.ViewManagerModel;
import interface_adapter.note.NoteViewModel;
import interface_adapter.friends_list.FriendsListViewModel;
import interface_adapter.playlist_collection.PlaylistCollectionViewModel;
import use_case.friend_profile.FriendProfileOutputBoundary;

public class FriendProfilePresenter implements FriendProfileOutputBoundary {
    private final ViewManagerModel viewManagerModel;
    private final FriendProfileViewModel friendProfileViewModel;
    private final NoteViewModel noteViewModel;
    private final FriendsListViewModel friendsListViewModel = new FriendsListViewModel();
    private final PlaylistCollectionViewModel playlistCollectionViewModel = new PlaylistCollectionViewModel();

    public FriendProfilePresenter(FriendProfileViewModel friendProfileViewModel, ViewManagerModel viewManagerModel, NoteViewModel noteViewModel) {
        this.friendProfileViewModel = friendProfileViewModel;
        this.viewManagerModel = viewManagerModel;
        this.noteViewModel = noteViewModel;
    }

    @Override
    public void prepareSuccessView(String message) {
        friendProfileViewModel.getState().setError(null);
        friendProfileViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        friendProfileViewModel.getState().setError(errorMessage);
        friendProfileViewModel.firePropertyChanged();
    }

    @Override
    public void switchToNoteView() {
        viewManagerModel.setState(noteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToFriendsListView() {
        viewManagerModel.setState(friendsListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToPlaylistCollectionView() {
        viewManagerModel.setState(playlistCollectionViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
