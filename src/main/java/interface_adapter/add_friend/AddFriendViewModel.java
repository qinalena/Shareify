package interface_adapter.add_friend;

import interface_adapter.ViewModel;

public class AddFriendViewModel extends ViewModel<AddFriendState> {
    private String newFriend;

    public AddFriendViewModel() {
        super("addFriend");
        setState(new AddFriendState());
    }

    public String getNewFriend() {
        return newFriend;
    }

    public void setNewFriend(String newFriend) {
        this.newFriend = newFriend;
    }
}

