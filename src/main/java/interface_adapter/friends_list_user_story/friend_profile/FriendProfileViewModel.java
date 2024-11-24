package interface_adapter.friends_list_user_story.friend_profile;

import interface_adapter.ViewModel;

public class FriendProfileViewModel extends ViewModel<FriendProfileState> {
    public FriendProfileViewModel() {
        super("friendProfile");
        setState(new FriendProfileState());
    }
}
