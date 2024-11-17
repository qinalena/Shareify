package interface_adapter.friends_list;

import interface_adapter.ViewModel;

public class FriendsListViewModel extends ViewModel<FriendsListState> {
    public FriendsListViewModel() {
        super("friendsList");
        setState(new FriendsListState());
    }
}

