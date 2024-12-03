package interface_adapter.friends_list_user_story.friend_profile_friends_list;

import interface_adapter.ViewModel;

/**
 * View model for friend profile friends list.
 */
public class FriendProfileFriendsListViewModel extends ViewModel<FriendProfileFriendsListState> {
    public FriendProfileFriendsListViewModel() {
        super("friendProfileFriendsList");
        setState(new FriendProfileFriendsListState());
    }
}
