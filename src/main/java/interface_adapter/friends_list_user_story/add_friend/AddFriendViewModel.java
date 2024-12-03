package interface_adapter.friends_list_user_story.add_friend;

import interface_adapter.ViewModel;

/**
 * View model for AddFriend use case.
 */
public class AddFriendViewModel extends ViewModel<AddFriendState> {
    public AddFriendViewModel() {
        super("addFriend");
        setState(new AddFriendState());
    }
}

