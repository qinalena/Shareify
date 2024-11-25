package interface_adapter.friends_list_user_story.add_friend;

import use_case.friends_list_user_story.add_friend.AddFriendOutputBoundary;

import java.util.List;

public class AddFriendPresenter implements AddFriendOutputBoundary {
    private final AddFriendViewModel addFriendViewModel;

    public AddFriendPresenter(AddFriendViewModel addFriendViewModel) {
        this.addFriendViewModel = addFriendViewModel;
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
        addFriendViewModel.setState(state); // Update the view model with the new state
        addFriendViewModel.firePropertyChanged(); // Notify the view of the state change
    }
}
