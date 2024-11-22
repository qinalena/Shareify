package interface_adapter.add_friend;

import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list.FriendsListViewModel;
import use_case.add_friend.AddFriendOutputBoundary;

import java.util.List;

public class AddFriendPresenter implements AddFriendOutputBoundary {
    private final AddFriendViewModel addFriendViewModel;
    private final ViewManagerModel viewManagerModel;
    private final FriendsListViewModel friendsListViewModel;

    public AddFriendPresenter(AddFriendViewModel addFriendViewModel, ViewManagerModel viewManagerModel, FriendsListViewModel friendsListViewModel) {
        this.addFriendViewModel = addFriendViewModel;
        this.viewManagerModel = viewManagerModel;
        this.friendsListViewModel = friendsListViewModel;
    }

    @Override
    public void prepareSuccessView(List<String> updatedFriendsList) {
        // Directly set the updated list of friends in the state
        AddFriendState state = new AddFriendState();
        state.setFriendsList(updatedFriendsList);
        state.setError(null);
        addFriendViewModel.setState(state);
        addFriendViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // Set the error message in the state
        AddFriendState state = new AddFriendState();
        state.setError(errorMessage);
        addFriendViewModel.setState(state);
        addFriendViewModel.firePropertyChanged();
    }

    @Override
    public void swtichToFriendsListView() {
        viewManagerModel.setState(friendsListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
