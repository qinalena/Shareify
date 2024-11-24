package interface_adapter.friends_list_user_story.add_friend;

import use_case.friends_list_user_story.add_friend.AddFriendOutputBoundary;
import interface_adapter.ViewManagerModel;
import interface_adapter.friends_list_user_story.friends_list.FriendsListViewModel;
import use_case.friends_list_user_story.add_friend.AddFriendOutputBoundary;

import java.util.List;

/**
 * Presenter for add friend use case.
 */
public class AddFriendPresenter implements AddFriendOutputBoundary {
    private final AddFriendViewModel addFriendViewModel;
    private final ViewManagerModel viewManagerModel;
    private final FriendsListViewModel friendsListViewModel;

    public AddFriendPresenter(AddFriendViewModel addFriendViewModel, ViewManagerModel viewManagerModel, FriendsListViewModel friendsListViewModel) {
        this.addFriendViewModel = addFriendViewModel;
        this.viewManagerModel = viewManagerModel;
        this.friendsListViewModel = friendsListViewModel;
    }

    /**
     * Prepares the success view for add friend related Use Cases.
     *
     * @param updatedFriendsList the list of friends to be added to our friends list.
     */
    @Override
    public void prepareSuccessView(List<String> updatedFriendsList) {
        // Directly set the updated list of friends in the state
        AddFriendState state = new AddFriendState();
        state.setFriendsList(updatedFriendsList);
        state.setError(null);
        addFriendViewModel.setState(state);
        addFriendViewModel.firePropertyChanged();
    }

    /**
     * Prepares the fail view for add friend related Use Cases.
     *
     * @param errorMessage the error message.
     */
    @Override
    public void prepareFailView(String errorMessage) {
        // Set the error message in the state
        AddFriendState state = new AddFriendState();
        state.setError(errorMessage);
        addFriendViewModel.setState(state);
        addFriendViewModel.firePropertyChanged();
    }

    /**
     * Executes the switch back to friends list after adding the friend to the DB.
     */
    @Override
    public void swtichToFriendsListView() {
        viewManagerModel.setState(friendsListViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
