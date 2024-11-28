package use_case.friends_list_user_story.friend_profile_friends_list;

public class FriendProfileFriendsListInteractor implements FriendProfileFriendsListInputBoundary {
    private final FriendProfileFriendsListOutputBoundary presenter;

    public FriendProfileFriendsListInteractor(FriendProfileFriendsListOutputBoundary presenter) {
        if (presenter == null) {
            throw new NullPointerException("Presenter cannot be null");
        }
        this.presenter = presenter;
    }

    @Override
    public void switchToFriendProfileView(String selectedFriendName, String password) {
        presenter.switchToFriendProfileView(selectedFriendName, password);
    }
}
