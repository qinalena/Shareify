package interface_adapter.friend_profile;

import interface_adapter.ViewModel;

public class FriendProfileViewModel extends ViewModel<FriendProfileState> {
    public FriendProfileViewModel() {
        super("friendProfile");
        setState(new FriendProfileState());
    }
}
