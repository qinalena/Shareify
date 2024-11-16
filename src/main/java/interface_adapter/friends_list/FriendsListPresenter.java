package interface_adapter.friends_list;

import use_case.friends_list.FriendsListOutputBoundary;

public class FriendsListPresenter implements FriendsListOutputBoundary {
    private final FriendsListViewModel viewModel;

    public FriendsListPresenter(FriendsListViewModel viewModel) {
        this.viewModel = viewModel;
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
}
