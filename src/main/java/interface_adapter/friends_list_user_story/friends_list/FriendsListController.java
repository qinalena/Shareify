package interface_adapter.friends_list_user_story.friends_list;

import use_case.friends_list_user_story.friends_list.FriendsListInputBoundary;

public class FriendsListController {
    private final FriendsListInputBoundary interactor;

    public FriendsListController(FriendsListInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void addFriend(String friendName) {
        interactor.addFriend(friendName);
    }

    public void deleteFriend(String friendName) {
        interactor.deleteFriend(friendName);
    }
}
