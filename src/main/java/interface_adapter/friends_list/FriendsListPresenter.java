package interface_adapter.friends_list;

import interface_adapter.friend_profile.FriendProfileViewModel;
import interface_adapter.note.NoteViewModel;
import use_case.friends_list.FriendsListOutputBoundary;
import interface_adapter.ViewManagerModel;

import javax.swing.text.View;

public class FriendsListPresenter implements FriendsListOutputBoundary {
    private final FriendsListViewModel viewModel;

    // THe view manager model is intantioed wrong
    private final ViewManagerModel viewManagerModel;
    private final NoteViewModel noteViewModel = new NoteViewModel();
    private final FriendsListViewModel friendsListViewModel = new FriendsListViewModel();
    private final FriendProfileViewModel friendProfileViewModel = new FriendProfileViewModel();

    public FriendsListPresenter(FriendsListViewModel viewModel, ViewManagerModel viewManagerModel) {
        this.viewModel = viewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void presentFriendAdded(String friendName) {
        viewModel.getState().addFriend(friendName);
        viewModel.firePropertyChanged();
    }

    @Override
    public void presentFriendDeleted(String friendName) {
        viewModel.getState().removeFriend(friendName);
        viewModel.firePropertyChanged();
    }

    @Override
    public void presentError(String errorMessage) {
        viewModel.getState().setError(errorMessage);
        viewModel.firePropertyChanged();
    }

    @Override
    public void switchToNoteView() {
        viewManagerModel.setState(noteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToFriendsListView() {
        viewManagerModel.setState(friendsListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    public void switchToFriendProfileView() {
        viewManagerModel.setState(friendProfileViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
