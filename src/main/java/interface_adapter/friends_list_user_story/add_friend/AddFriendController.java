package interface_adapter.friends_list_user_story.add_friend;

import use_case.friends_list_user_story.add_friend.AddFriendInputBoundary;

public class AddFriendController {
    private final AddFriendInputBoundary interactor;

    public AddFriendController(AddFriendInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void addFriend(String friendName) {
        interactor.execute(friendName);
    }
}

